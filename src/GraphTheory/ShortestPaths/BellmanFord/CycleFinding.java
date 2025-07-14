//package GraphTheory.ShortestPaths.BellmanFord;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1197/

public class CycleFinding {
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
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            long w = in.nextLong();
            edges.add(new Edge(u, v, w));
        }

        bellman(n, edges);
    }

    static void bellman(int n, List<Edge> edges) {
        long[] distance = new long[n];
        Arrays.fill(distance, 0);

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.u] != Long.MAX_VALUE) {
                    if (distance[edge.v] > distance[edge.u] + edge.w) {
                        distance[edge.v] = distance[edge.u] + edge.w;
                        parent[edge.v] = edge.u;
                    }
                }
            }
        }

        int start = -1;
        for (Edge edge : edges) {
            if (distance[edge.u] != Long.MAX_VALUE) {
                if (distance[edge.v] > distance[edge.u] + edge.w) {
                    start = edge.u;
                    break;
                }
            }
        }

        if (start == -1) System.out.println("NO");
        else {
            System.out.println("YES");
            // Just in case start pointed to a vertex which was impacted by a -ve cycle,
            // We have to go back, enough number of steps to be at a node that is part of a -ve cycle
            for (int i = 0; i < n; i++) start = parent[start];

            List<Integer> cycleNodes = new ArrayList<>();

            for (int cur = start; ; cur = parent[cur]) {
                cycleNodes.add((cur + 1));
                if (cur == start && cycleNodes.size() > 1) break;
            }

            for (int i = cycleNodes.size() - 1; i >= 0; i--) {
                System.out.print(cycleNodes.get(i) + " ");
            }
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
