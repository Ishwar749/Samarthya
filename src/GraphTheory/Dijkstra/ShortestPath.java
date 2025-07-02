package GraphTheory.Dijkstra;

import java.io.*;
import java.util.*;

// Problem: https://judge.yosupo.jp/problem/shortest_path

public class ShortestPath {

    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int nodeCount = in.nextInt();
        int edgeCount = in.nextInt();
        int start = in.nextInt();
        int target = in.nextInt();

        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < nodeCount; i++) graph.put(i, new ArrayList<>());

        for (int i = 0; i < edgeCount; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int weight = in.nextInt();
            graph.get(from).add(new Edge(to, weight));
        }

        Edge[] result = findShortestPath(start, target, nodeCount, graph);

        if (result[target] == null) {
            out.println(-1);
        } else {
            long totalDistance = 0L;
            Map<Integer, Edge> shortestPath = new HashMap<>();

            int current = target;
            while (current != start) {
                Edge edge = result[current];
                totalDistance += edge.weight;
                shortestPath.put(edge.destination, new Edge(current, edge.weight));
                current = edge.destination;
            }

            out.println(totalDistance + " " + shortestPath.keySet().size());
            current = start;

            while (current != target) {
                out.println(current + " " + shortestPath.get(current).destination);
                current = shortestPath.get(current).destination;
            }
        }
        out.close();
    }

    static class Tuple implements Comparable<Tuple> {
        int node;
        int parentNode;
        int connectingEdgeDistance;
        long totalDistance;

        public Tuple(int node, int parentNode, int connectingEdgeDistance, long totalDistance) {
            this.node = node;
            this.parentNode = parentNode;
            this.connectingEdgeDistance = connectingEdgeDistance;
            this.totalDistance = totalDistance;
        }

        public int compareTo(Tuple otherTuple) {
            return Long.compare(this.totalDistance, otherTuple.totalDistance);
        }
    }

    static Edge[] findShortestPath(int startingNode, int targetNode, int nodeCount, Map<Integer, List<Edge>> graph) {

        boolean[] visited = new boolean[nodeCount];
        long[] distances = new long[nodeCount];
        Arrays.fill(distances, Long.MAX_VALUE);
        Edge[] results = new Edge[nodeCount];

        Queue<Tuple> queue = new PriorityQueue<>();
        Tuple startingPoint = new Tuple(startingNode, -1, 0, 0L);
        queue.add(startingPoint);

        while (!queue.isEmpty()) {
            Tuple polledTuple = queue.poll();
            int currentNode = polledTuple.node;
            int parentNode = polledTuple.parentNode;
            int connectingEdgeWeight = polledTuple.connectingEdgeDistance;
            long totalDistance = polledTuple.totalDistance;

            if (visited[currentNode]) continue;
            visited[currentNode] = true;
            results[currentNode] = new Edge(parentNode, connectingEdgeWeight);
            distances[currentNode] = totalDistance;
            if (currentNode == targetNode) break;

            for (Edge edge : graph.get(currentNode)) {
                int vertex = edge.destination;
                long totalDistanceTillVertex = totalDistance + edge.weight;

                if (!visited[edge.destination] && totalDistanceTillVertex < distances[vertex]) {
                    distances[vertex] = totalDistanceTillVertex;
                    Tuple toAdd = new Tuple(vertex, currentNode, edge.weight, totalDistanceTillVertex);
                    queue.add(toAdd);
                }
            }
        }

        return results;
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
