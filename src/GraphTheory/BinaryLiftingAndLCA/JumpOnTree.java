package GraphTheory.BinaryLiftingAndLCA;

import java.io.*;
import java.util.*;

public class JumpOnTree {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) tree.put(i, new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        BinaryLiftingAndLCA queryInstance = new BinaryLiftingAndLCA(n, 0, tree);

        for (int i = 0; i < q; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int v = in.nextInt();
            out.println(getKthNodeInPath(a, b, v, queryInstance));
        }

        out.close();
    }

    static int getKthNodeInPath(int a, int b, int v, BinaryLiftingAndLCA queryInstance) {
        int lca = queryInstance.getLCA(a, b);
        int depthA = queryInstance.depth[a];
        int depthB = queryInstance.depth[b];
        int depthLCA = queryInstance.depth[lca];
        int aToLCALength = (depthA - depthLCA) + 1;
        int bToLCALength = (depthB - depthLCA) + 1;
        int totalLength = (aToLCALength + bToLCALength) - 1;

        if (v >= totalLength) return -1;
        if (v < aToLCALength) return queryInstance.getKthParent(a, v);
        else return queryInstance.getKthParent(b, totalLength - v - 1);
    }

    static class BinaryLiftingAndLCA {
        int nodeCount, root, LOG, timer;
        Map<Integer, List<Integer>> tree;
        int[] depth, tIn, tOut;
        int[][] up;

        public BinaryLiftingAndLCA(int nodeCount, int root, Map<Integer, List<Integer>> tree) {
            this.nodeCount = nodeCount;
            this.root = root;
            this.tree = tree;
            LOG = 0;
            while ((1 << LOG) < nodeCount) LOG++;
            depth = new int[nodeCount];
            tIn = new int[nodeCount];
            tOut = new int[nodeCount];
            up = new int[nodeCount][LOG];
            timer = 0;

            dfs(root, root);
        }

        private void dfs(int node, int par) {
            depth[node] = depth[par] + 1;
            tIn[node] = timer++;
            up[node][0] = par;

            for (int i = 1; i < LOG; i++) {
                up[node][i] = up[up[node][i - 1]][i - 1];
            }
            for (int nei : tree.get(node)) {
                if (nei != par) dfs(nei, node);
            }

            tOut[node] = timer++;
        }

        private boolean isAncestor(int u, int v) {
            return tIn[u] <= tIn[v] && tOut[u] >= tOut[v];
        }

        private int getKthParent(int node, int k) {
            for (int i = 0; i < LOG; i++) {
                if (((1 << i) & k) > 0) node = up[node][i];
            }
            return node;
        }

        private int getLCA(int u, int v) {
            if (isAncestor(u, v)) return u;
            if (isAncestor(v, u)) return v;

            for (int i = LOG - 1; i >= 0; i--) {
                if (!isAncestor(up[u][i], v)) {
                    u = up[u][i];
                }
            }
            return up[u][0];
        }
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
