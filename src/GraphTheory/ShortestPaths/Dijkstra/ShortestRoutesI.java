package GraphTheory.ShortestPaths.Dijkstra;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1671

public class ShortestRoutesI {

    static class Flight implements Comparable<Flight> {
        int destination;
        long length;

        public Flight(int destination, long length) {
            this.destination = destination;
            this.length = length;
        }

        public int compareTo(Flight otherFlight) {
            return Long.compare(this.length, otherFlight.length);
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int cityCount = in.nextInt();
        int flightCount = in.nextInt();

        Map<Integer, List<Flight>> graph = new HashMap<>();
        for (int i = 0; i < cityCount; i++) graph.put(i, new ArrayList<>());

        for (int i = 0; i < flightCount; i++) {
            int source = in.nextInt() - 1;
            int destination = in.nextInt() - 1;
            long length = in.nextLong();
            graph.get(source).add(new Flight(destination, length));
        }

        long[] shortestDistanceFromRootNode = getShortestRoutes(0, cityCount, graph);
        for (long distance : shortestDistanceFromRootNode) {
            out.print(distance + " ");
        }
        out.close();
    }

    static long[] getShortestRoutes(int source, int cityCount, Map<Integer, List<Flight>> graph) {

        boolean[] isMarked = new boolean[cityCount];
        long[] shortestDistance = new long[cityCount];
        Arrays.fill(shortestDistance, Long.MAX_VALUE);

        Queue<Flight> dijkstraQueue = new PriorityQueue<>();

        Flight startingFlight = new Flight(source, 0);
        dijkstraQueue.add(startingFlight);

        while (!dijkstraQueue.isEmpty()) {

            Flight currentFlight = dijkstraQueue.poll();

            if (isMarked[currentFlight.destination]) continue;

            isMarked[currentFlight.destination] = true;
            shortestDistance[currentFlight.destination] = currentFlight.length;

            for (Flight flight : graph.get(currentFlight.destination)) {
                int city = flight.destination;
                long length = shortestDistance[currentFlight.destination] + flight.length;

                if (!isMarked[city] && length < shortestDistance[city]) {
                    shortestDistance[city] = length;
                    Flight toAdd = new Flight(city, length);
                    dijkstraQueue.add(toAdd);
                }
            }
        }

        return shortestDistance;
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
