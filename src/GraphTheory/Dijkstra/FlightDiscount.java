package GraphTheory.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1195

public class FlightDiscount {

    static class Flight {
        int destination;
        long length;

        public Flight(int destination, long length) {
            this.destination = destination;
            this.length = length;
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

        long answer = getShortestRoutes(0, cityCount, graph);
        out.println(answer);
        out.close();

    }

    static class State implements Comparable<State> {
        long distance;
        int city;
        int isDiscountApplied;

        public State(long distance, int city, int isDiscountApplied) {
            this.distance = distance;
            this.city = city;
            this.isDiscountApplied = isDiscountApplied;
        }

        public int compareTo(State otherState) {
            return Long.compare(this.distance, otherState.distance);
        }
    }

    static long getShortestRoutes(int source, int cityCount, Map<Integer, List<Flight>> graph) {

        boolean[][] isMarked = new boolean[cityCount][2];
        long[][] shortestDistance = new long[cityCount][2];
        for (long[] row : shortestDistance) Arrays.fill(row, Long.MAX_VALUE);

        Queue<State> dijkstraQueue = new PriorityQueue<>();
        State startingPoint = new State(0, 0, 0);

        dijkstraQueue.add(startingPoint);

        while (!dijkstraQueue.isEmpty()) {

            State current = dijkstraQueue.poll();
            long distanceTillNow = current.distance;
            int currentCity = current.city;
            int isDiscountApplied = current.isDiscountApplied;

            if (isMarked[currentCity][isDiscountApplied]) continue;
            isMarked[currentCity][isDiscountApplied] = true;

            if (currentCity == cityCount - 1) return shortestDistance[cityCount - 1][1];

            for (Flight flight : graph.get(currentCity)) {
                int neiCity = flight.destination;
                long neiLength = flight.length;
                long offeredDistance = distanceTillNow + neiLength;

                if (!isMarked[neiCity][isDiscountApplied] && offeredDistance < shortestDistance[neiCity][isDiscountApplied]) {
                    shortestDistance[neiCity][isDiscountApplied] = offeredDistance;
                    State toAdd = new State(offeredDistance, neiCity, isDiscountApplied);
                    dijkstraQueue.add(toAdd);
                }

                if (isDiscountApplied == 0) {
                    offeredDistance = distanceTillNow + (neiLength / 2);
                    // isDiscountApplied = 1; DONT DO THIS,
                    // Doing this sets the discountApplied as 1 for the other cities connected to currentCity
                    // which are yet to be tranversed in this loop.
                    if (!isMarked[neiCity][1] && offeredDistance < shortestDistance[neiCity][1]) {
                        shortestDistance[neiCity][1] = offeredDistance;
                        State toAdd = new State(offeredDistance, neiCity, 1);
                        dijkstraQueue.add(toAdd);
                    }
                }
            }
        }

        return shortestDistance[cityCount - 1][1];

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
