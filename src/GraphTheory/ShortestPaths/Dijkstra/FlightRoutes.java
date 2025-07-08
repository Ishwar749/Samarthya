package GraphTheory.ShortestPaths.Dijkstra;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1196/

public class FlightRoutes {
    static class Edge implements Comparable<Edge> {
        int city;
        long price;

        public Edge(int city, long price) {
            this.city = city;
            this.price = price;
        }

        public int compareTo(Edge otherEdge) {
            return Long.compare(this.price, otherEdge.price);
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int cityCount = in.nextInt();
        int edgeCount = in.nextInt();
        int k = in.nextInt();
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < cityCount; i++) graph.put(i, new ArrayList<>());

        for (int i = 0; i < edgeCount; i++) {
            int start = in.nextInt() - 1;
            int end = in.nextInt() - 1;
            long price = in.nextLong();
            graph.get(start).add(new Edge(end, price));
        }

        long[] answer = findKShortestRoutes(cityCount, k, graph);

        for (long distance : answer) out.print(distance + " ");
        out.close();
    }

    static long[] findKShortestRoutes(int cityCount, int k, Map<Integer, List<Edge>> graph) {
        long[][] distance = new long[cityCount][k];
        for (long[] row : distance) Arrays.fill(row, Long.MAX_VALUE);

        Queue<Edge> queue = new PriorityQueue<>();

        Edge startingPoint = new Edge(0, 0);
        distance[0][0] = 0;

        queue.add(startingPoint);

        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            int currentCity = current.city;
            long distanceTillNow = current.price;

            if (distance[currentCity][k - 1] < distanceTillNow) continue;

            for (Edge nei : graph.get(currentCity)) {
                int neiCity = nei.city;
                long offeredDistance = distanceTillNow + nei.price;

                if (offeredDistance < distance[neiCity][k - 1]) {
                    distance[neiCity][k - 1] = offeredDistance;
                    Arrays.sort(distance[neiCity]);

                    Edge toAdd = new Edge(neiCity, offeredDistance);
                    queue.add(toAdd);
                }
            }
        }

        return distance[cityCount - 1];
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
