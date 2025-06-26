package GraphTheory.MinimumSpanningTrees;

import java.io.*;
import java.util.*;

public class SpanningTree {

    static class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);


        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            edges[i] = new Edge(u, v, w);
        }

        Arrays.sort(edges);
        UnionFind unionFind = new UnionFind(n);
        long mstWeight = 0L;
        int edgesAdded = 0;

        for (Edge edge : edges) {
            if (unionFind.union(edge.source, edge.destination)) {
                mstWeight += edge.weight;
                edgesAdded++;
            }
            if (edgesAdded == n - 1) break;
        }

        out.println(mstWeight);
        out.close();
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

class UnionFind {
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
