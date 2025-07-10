package GraphTheory.ShortestPaths.BellmanFord;

import java.io.*;
import java.util.*;

// Problem: https://open.kattis.com/problems/shortestpath3

public class SingleSourceShortestPathNegativeWeights {
    static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            int n = in.nextInt();
            if (n == 0) break;
            int m = in.nextInt();
            int q = in.nextInt();
            int s = in.nextInt();

            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();
                edges.add(new Edge(u, v, w));
            }

            int[] distance = shortestPath(s, n, edges);

            for (int i = 0; i < q; i++) {
                int v = in.nextInt();
                if (distance[v] == -1) {
                    out.println("Impossible");
                } else if (distance[v] == -2) {
                    out.println("-Infinity");
                } else {
                    out.println(distance[v]);
                }
            }
        }

        out.close();
    }

    static int[] shortestPath(int s, int n, List<Edge> edges) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.u] < Integer.MAX_VALUE) {
                    distance[edge.v] = Math.min(distance[edge.v], distance[edge.u] + edge.w);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                if (distance[edge.u] != Integer.MAX_VALUE) {
                    if (distance[edge.u] == -2) distance[edge.v] = -2;
                    else if (distance[edge.v] != -2 && distance[edge.v] > distance[edge.u] + edge.w) {
                        distance[edge.v] = -2;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++)
            if (distance[i] == Integer.MAX_VALUE)
                distance[i] = -1;

        return distance;
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
