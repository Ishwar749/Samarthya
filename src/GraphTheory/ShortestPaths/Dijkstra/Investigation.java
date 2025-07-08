package GraphTheory.ShortestPaths.Dijkstra;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1202

public class Investigation {
    static long MOD = (long) (1e9 + 7);

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

        int nodeCount = in.nextInt();
        int edgeCount = in.nextInt();

        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < nodeCount; i++) graph.put(i, new ArrayList<>());

        for (int i = 0; i < edgeCount; i++) {
            int start = in.nextInt() - 1;
            int end = in.nextInt() - 1;
            int price = in.nextInt();
            graph.get(start).add(new Edge(end, price));
        }

        long[] answer = findMinimumRouteDetails(nodeCount, graph);
        for (long value : answer) out.print(value + " ");
        out.close();
    }

    static long[] findMinimumRouteDetails(int nodeCount, Map<Integer, List<Edge>> graph) {
        long[] price = new long[nodeCount];
        long[] count = new long[nodeCount];
        long[] minFlights = new long[nodeCount];
        long[] maxFlights = new long[nodeCount];

        Arrays.fill(price, Long.MAX_VALUE);
        Arrays.fill(minFlights, Long.MAX_VALUE);
        Arrays.fill(maxFlights, Long.MIN_VALUE);

        Queue<Edge> queue = new PriorityQueue<>();

        Edge startingPoint = new Edge(0, 0);
        queue.add(startingPoint);
        price[0] = 0;
        count[0] = 1;
        minFlights[0] = 0;
        maxFlights[0] = 0;

        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            int currentCity = current.city;
            long currentPrice = current.price;

            if(currentPrice > price[currentCity]) continue;

            for (Edge nei : graph.get(currentCity)) {
                int neiCity = nei.city;
                long neiPrice = nei.price;
                long proposedPrice = neiPrice + currentPrice;

                if (proposedPrice > price[neiCity]) continue;
                else if (proposedPrice == price[neiCity]) {
                    count[neiCity] = modAdd(count[neiCity], count[currentCity]);
                    minFlights[neiCity] = Math.min(minFlights[neiCity], minFlights[currentCity] + 1);
                    maxFlights[neiCity] = Math.max(maxFlights[neiCity], maxFlights[currentCity] + 1);
                } else {
                    price[neiCity] = proposedPrice;
                    count[neiCity] = count[currentCity];
                    minFlights[neiCity] = minFlights[currentCity] + 1;
                    maxFlights[neiCity] = maxFlights[currentCity] + 1;

                    Edge toAdd = new Edge(neiCity, proposedPrice);
                    queue.add(toAdd);
                }
            }
        }

        long[] answer = {price[nodeCount - 1], count[nodeCount - 1], minFlights[nodeCount - 1], maxFlights[nodeCount - 1]};
        return answer;
    }

    static long modAdd(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
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
