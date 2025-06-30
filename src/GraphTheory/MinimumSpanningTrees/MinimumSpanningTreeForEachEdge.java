package GraphTheory.MinimumSpanningTrees;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/609/problem/E

public class MinimumSpanningTreeForEachEdge {
    static class Node {
        int node;
        int len;

        public Node(int node, int len) {
            this.node = node;
            this.len = len;
        }
    }

    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;
        int i;

        public Edge(int u, int v, int w, int i) {
            this.u = u;
            this.v = v;
            this.w = w;
            this.i = i;
        }

        public int compareTo(Edge that) {
            return Integer.compare(this.w, that.w);
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        if (m == 0) return;

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            edges[i] = new Edge(u, v, w, i);
        }

        Arrays.sort(edges);
        long[] mstWeight = new long[m];
        UnionFind unionFind = new UnionFind(n);
        long minWeight = 0L;
        Map<Integer, List<Node>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) tree.put(i, new ArrayList<>());

        for (Edge edge : edges) {
            if (unionFind.union(edge.u, edge.v)) {
                minWeight += edge.w;
                tree.get(edge.u).add(new Node(edge.v, edge.w));
                tree.get(edge.v).add(new Node(edge.u, edge.w));
            } else {
                mstWeight[edge.i] = -1;
            }
        }

        BinaryLiftingAndLCA query = new BinaryLiftingAndLCA(n, 0, tree);
        for (Edge edge : edges) {
            if (mstWeight[edge.i] == -1) {
                long[] ans = new long[2];
                query.getMinAndMaxLengths(edge.u, edge.v, ans);
                mstWeight[edge.i] = (minWeight - ans[1]) + edge.w;
            } else {
                mstWeight[edge.i] = minWeight;
            }
        }

        for (int i = 0; i < m; i++) {
            out.println(mstWeight[i]);
        }
        out.close();
    }

    static class BinaryLiftingAndLCA {
        int nodeCount, root, LOG, timer;
        Map<Integer, List<Node>> tree;
        int[] depth, tIn, tOut;
        int[][] up, min, max;

        public BinaryLiftingAndLCA(int nodeCount, int root, Map<Integer, List<Node>> tree) {
            this.nodeCount = nodeCount;
            this.root = root;
            this.tree = tree;
            LOG = 0;
            while ((1 << LOG) < nodeCount) LOG++;
            depth = new int[nodeCount];
            tIn = new int[nodeCount];
            tOut = new int[nodeCount];
            up = new int[nodeCount][LOG];
            min = new int[nodeCount][LOG];
            max = new int[nodeCount][LOG];
            timer = 0;

            dfs(root, root, 0);
        }

        private void dfs(int node, int par, int len) {
            depth[node] = depth[par] + 1;
            tIn[node] = timer++;
            up[node][0] = par;
            min[node][0] = len;
            max[node][0] = len;

            for (int i = 1; i < LOG; i++) {
                up[node][i] = up[up[node][i - 1]][i - 1];
                min[node][i] = Math.min(min[node][i - 1], min[up[node][i - 1]][i - 1]);
                max[node][i] = Math.max(max[node][i - 1], max[up[node][i - 1]][i - 1]);
            }

            for (Node neiNode : tree.get(node)) {
                if (neiNode.node != par) dfs(neiNode.node, node, neiNode.len);
            }

            tOut[node] = timer++;
        }

        private boolean isAncestor(int u, int v) {
            return tIn[u] <= tIn[v] && tOut[u] >= tOut[v];
        }

        private void getKthParent(int node, int k, int[] ans) {
            for (int i = 0; i < LOG; i++) {
                if (((1 << i) & k) > 0) {
                    ans[0] = Math.min(ans[0], min[node][i]);
                    ans[1] = Math.max(ans[1], max[node][i]);
                    node = up[node][i];
                }
            }
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

        private void getMinAndMaxLengths(int a, int b, long[] ans) {
            int lca = getLCA(a, b);
            int kForA = depth[a] - depth[lca];
            int kForB = depth[b] - depth[lca];
            int[] ans1 = {Integer.MAX_VALUE, Integer.MIN_VALUE};
            int[] ans2 = {Integer.MAX_VALUE, Integer.MIN_VALUE};

            getKthParent(a, kForA, ans1);
            getKthParent(b, kForB, ans2);
            ans[0] = Math.min(ans1[0], ans2[0]);
            ans[1] = Math.max(ans1[1], ans2[1]);
        }
    }

    static class UnionFind {
        private int nodeCount;
        private int[] parent;
        private int[] rank;

        public UnionFind(int nodeCount) {
            this.nodeCount = nodeCount;
            parent = new int[nodeCount];
            rank = new int[nodeCount];
            for (int i = 0; i < nodeCount; i++) parent[i] = i;
        }

        public int findParent(int a) {
            if (a == parent[a]) return a;
            return parent[a] = findParent(parent[a]);
        }

        public boolean union(int a, int b) {
            a = findParent(a);
            b = findParent(b);
            if (a == b) return false;

            if (rank[a] == rank[b]) {
                parent[b] = a;
                rank[a]++;
            } else if (rank[a] > rank[b]) parent[b] = a;
            else parent[a] = b;

            return true;
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
