package GraphTheory.BinaryLiftingAndLCA;

import java.io.*;
import java.util.*;

//Problem: https://codeforces.com/contest/1702/problem/G2

public class PassablePaths {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) tree.put(i, new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        BinaryLiftingAndLCA queryInstance = new BinaryLiftingAndLCA(n, 0, tree);

        int q = in.nextInt();
        while (q-- > 0) {
            int k = in.nextInt();
            int[] set = new int[k];
            for (int i = 0; i < k; i++) {
                set[i] = in.nextInt() - 1;
            }

            if (isPassable(set, k, queryInstance)) out.println("YES");
            else out.println("NO");
        }
        out.close();
    }

    static boolean isPassable(int[] set, int k, BinaryLiftingAndLCA queryInstance) {
        int[][] nodes = new int[k][3];
        for (int i = 0; i < k; i++) {
            nodes[i][0] = set[i];
            nodes[i][1] = queryInstance.depth[set[i]];
            nodes[i][2] = i;
        }
        Arrays.sort(nodes, (int[] a, int[] b) -> Integer.compare(b[1], a[1]));

        boolean[] marked = new boolean[k];
        int deepest = nodes[0][0];

        for (int i = 0; i < k; i++) {
            int[] e = nodes[i];
            if (queryInstance.getLCA(e[0], deepest) == e[0]) marked[e[2]] = true;
        }

        int secondDeepest = -1;
        for (int i = 0; i < k; i++) {
            int[] e = nodes[i];
            if (!marked[e[2]]) {
                secondDeepest = e[0];
                break;
            }
        }

        if (secondDeepest == -1) return true;

        for (int i = 0; i < k; i++) {
            int[] e = nodes[i];
            if (!marked[e[2]] && queryInstance.getLCA(e[0], secondDeepest) == e[0]) {
                marked[e[2]] = true;
            }
        }

        for (int i = 0; i < k; i++) if (!marked[i]) return false;

        int upmostNode = nodes[k - 1][0];
        int pathLCA = queryInstance.getLCA(deepest, secondDeepest);
        return queryInstance.depth[pathLCA] <= queryInstance.depth[upmostNode];
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
