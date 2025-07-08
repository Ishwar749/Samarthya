package GraphTheory.ShortestPaths.Dijkstra;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/1915/problem/G
public class Bicycles {

    static class Edge {
        int city;
        int weight;

        public Edge(int destination, int weight) {
            this.city = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();
        while (tests-- > 0) {
            int nodeCount = in.nextInt();
            int edgeCount = in.nextInt();
            Map<Integer, List<Edge>> graph = new HashMap<>();
            for (int i = 0; i < nodeCount; i++) graph.put(i, new ArrayList<>());

            for (int i = 0; i < edgeCount; i++) {
                int start = in.nextInt() - 1;
                int end = in.nextInt() - 1;
                int weight = in.nextInt();
                graph.get(start).add(new Edge(end, weight));
                graph.get(end).add(new Edge(start, weight));
            }

            int[] bikes = new int[nodeCount];
            for (int i = 0; i < nodeCount; i++) {
                bikes[i] = in.nextInt();
            }

            long answer = findShortestTime(nodeCount, bikes, graph);
            out.println(answer);
        }
        out.close();
    }

    static class State implements Comparable<State> {
        long distance;
        int city;
        int bike;

        public State(long distance, int city, int bike) {
            this.distance = distance;
            this.city = city;
            this.bike = bike;
        }

        public int compareTo(State otherState) {
            return Long.compare(this.distance, otherState.distance);
        }
    }

    static long findShortestTime(int nodeCount, int[] bikes, Map<Integer, List<Edge>> graph) {
        long[][] distance = new long[nodeCount][1001];
        boolean[][] visited = new boolean[nodeCount][1001];

        for (long[] row : distance) Arrays.fill(row, Long.MAX_VALUE);

        Queue<State> queue = new PriorityQueue<>();
        State startingPoint = new State(0, 0, bikes[0]);
        queue.add(startingPoint);

        while (!queue.isEmpty()) {
            State current = queue.poll();
            long distanceTillNow = current.distance;
            int currentCity = current.city;
            int currentBike = current.bike;

            if (visited[currentCity][currentBike]) continue;
            visited[currentCity][currentBike] = true;

            for (Edge nei : graph.get(currentCity)) {
                int neiCity = nei.city;
                int neiWeight = nei.weight;
                int neiBike = Math.min(currentBike, bikes[neiCity]);
                long proposedDistance = distanceTillNow + (long) (neiWeight * currentBike);

                if (!visited[neiCity][neiBike] && proposedDistance < distance[neiCity][neiBike]) {
                    distance[neiCity][neiBike] = proposedDistance;
                    State toAdd = new State(proposedDistance, neiCity, neiBike);
                    queue.add(toAdd);
                }
            }
        }

        long answer = Long.MAX_VALUE;
        for (long value : distance[nodeCount - 1]) {
            answer = Math.min(answer, value);
        }
        return answer;
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
