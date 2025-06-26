package GraphTheory.MinimumSpanningTrees;

import java.io.*;
import java.util.*;

public class RoadReparation {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int nodeCount = in.nextInt();
        int edgeCount = in.nextInt();
        Edge[] edges = new Edge[edgeCount];

        for (int i = 0; i < edgeCount; i++) {
            int source = in.nextInt() - 1;
            int destination = in.nextInt() - 1;
            int weight = in.nextInt();
            edges[i] = new Edge(source, destination, weight);
        }

        long minimumSpanningTreeWeight = findMinimumSpanningTreeWeight(nodeCount, edgeCount, edges);

        if (minimumSpanningTreeWeight == 0L) out.println("IMPOSSIBLE");
        else out.println(minimumSpanningTreeWeight);
        out.close();
    }

    static long findMinimumSpanningTreeWeight(int nodeCount, int edgeCount, Edge[] edges) {

        UnionFind unionFind = new UnionFind(nodeCount);
        Arrays.sort(edges);
        long totalWeight = 0L;
        int edgesAdded = 0;

        for (Edge edge : edges) {
            if (unionFind.union(edge.source, edge.destination)) {
                totalWeight += edge.weight;
                edgesAdded++;
            }
            if (edgesAdded == nodeCount - 1) break;
        }

        if (edgesAdded != nodeCount - 1) return 0L;
        return totalWeight;
    }

    static class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public int compareTo(Edge otherEdge) {
            return Integer.compare(this.weight, otherEdge.weight);
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
}

