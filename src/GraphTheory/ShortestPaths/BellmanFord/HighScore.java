//package GraphTheory.ShortestPaths.BellmanFord;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1673

public class HighScore {
    static class Edge {
        int u;
        int v;
        long w;

        public Edge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        List<Edge> edges = new ArrayList<>();
        List<Edge> reverseEdges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            long w = in.nextInt() * -1;
            edges.add(new Edge(u, v, w));
            reverseEdges.add(new Edge(v, u, w));
        }

        long answer = findShortestPath(n, edges, reverseEdges);
        out.println(answer);
        out.close();
    }


    static long findShortestPath(int n, List<Edge> edges, List<Edge> reverseEdges) {
        long[] distance = new long[n];
        boolean[] isPartOfCycle = new boolean[n];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[0] = 0;

        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                if (distance[edge.u] != Long.MAX_VALUE) {
                    if (distance[edge.v] > distance[edge.u] + edge.w) {
                        distance[edge.v] = distance[edge.u] + edge.w;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                if (distance[edge.u] != Long.MAX_VALUE) {
                    if (distance[edge.v] > distance[edge.u] + edge.w) {
                        distance[edge.v] = distance[edge.u] + edge.w;
                        isPartOfCycle[edge.v] = true;
                    }
                }
            }
        }

        if (isPartOfCycle[n - 1]) return -1;

        // From here, we check if n-1 is reachable from a cycle

        long[] reverseDistance = new long[n];
        Arrays.fill(reverseDistance, Long.MAX_VALUE);
        reverseDistance[n - 1] = 0;

        for (int i = 0; i < n; i++) {
            for (Edge edge : reverseEdges) {
                if (reverseDistance[edge.u] != Long.MAX_VALUE) {
                    if (reverseDistance[edge.v] > reverseDistance[edge.u] + edge.w) {
                        reverseDistance[edge.v] = reverseDistance[edge.u] + edge.w;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (reverseDistance[i] < Long.MAX_VALUE && isPartOfCycle[i]) return -1;
        }

        return distance[n - 1] * -1L;
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
