package GraphTheory.MinimumSpanningTrees;

import java.io.*;
import java.util.*;

public class OilBusiness {

    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int i;
        long w;

        public Edge(int u, int v, int i, long w) {
            this.u = u;
            this.v = v;
            this.i = i;
            this.w = w;
        }

        public int compareTo(Edge that) {
            return Long.compare(that.w, this.w);
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        long s = in.nextLong();

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            long w = in.nextLong();
            edges[i] = new Edge(u, v, i, w);
        }

        Arrays.sort(edges);

        List<Integer> result = solve(n, m, s, edges);
        out.println(result.size());
        for (int e : result) out.print(e + " ");
        out.close();
    }

    static List<Integer> solve(int n, int m, long s, Edge[] edges) {

        UnionFind unionFind = new UnionFind(n);
        boolean[] taken = new boolean[m];
        long deletedSum = 0L;

        for (Edge edge : edges) {
            if (unionFind.union(edge.u, edge.v)) {
                taken[edge.i] = true;
            } else {
                deletedSum += edge.w;
            }
        }

        for (Edge edge : edges) {
            if (deletedSum > s && !taken[edge.i]) {
                deletedSum -= edge.w;
                taken[edge.i] = true;
            }
            if (deletedSum <= s) break;
        }

        List<Integer> result = new ArrayList<>();
        for (Edge edge : edges) {
            if (!taken[edge.i]) result.add(edge.i + 1);
        }

        Collections.sort(result);
        return result;
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
