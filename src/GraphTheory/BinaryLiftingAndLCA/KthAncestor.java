package GraphTheory.BinaryLiftingAndLCA;

import java.io.*;
import java.util.*;

public class KthAncestor {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int t = in.nextInt();
        int maxNodes = 100002;

        while (t-- > 0) {
            int nodeCount = in.nextInt();
            Map<Integer, List<Integer>> tree = new HashMap<>();

            int rootOfTree = 0;
            for (int i = 0; i < maxNodes; i++) tree.put(i, new ArrayList<>());

            for (int i = 0; i < nodeCount; i++) {
                int node = in.nextInt();
                int par = in.nextInt();

                if (par == 0) {
                    rootOfTree = node;
                }
                tree.get(par).add(node);
            }

            BinaryLiftingAndLCA queryInstance = new BinaryLiftingAndLCA(maxNodes, rootOfTree, tree);

            int queries = in.nextInt();

            for (int i = 0; i < queries; i++) {
                int queryType = in.nextInt();

                if (queryType == 0) {
                    int par = in.nextInt();
                    int node = in.nextInt();
                    queryInstance.addNode(node, par);
                } else if (queryType == 1) {
                    int node = in.nextInt();
                    queryInstance.removeNode(node);
                } else {
                    int node = in.nextInt();
                    int k = in.nextInt();
                    int answer = queryInstance.getKthParent(node, k);
                    out.println(answer);
                }
            }
        }
        out.close();
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

            dfs(root, 0);
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

        private void addNode(int node, int par) {
            up[node][0] = par;

            for (int i = 1; i < LOG; i++) {
                up[node][i] = up[up[node][i - 1]][i - 1];
            }
        }

        private void removeNode(int node) {
            for (int i = 0; i < LOG; i++) {
                up[node][i] = 0;
            }
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
