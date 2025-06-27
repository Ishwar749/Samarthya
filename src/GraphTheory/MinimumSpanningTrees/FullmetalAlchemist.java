package GraphTheory.MinimumSpanningTrees;

import java.io.*;
import java.util.*;

class FullmetalAlchemist {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int rows = in.nextInt();
        int cols = in.nextInt();
        int[][] grid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            String currentRow = in.next();
            for (int j = 0; j < cols; j++) {
                grid[i][j] = currentRow.charAt(j) - 'a';
            }
        }

        int answer = findMinimalCost(rows, cols, grid);
        out.println(answer);
        out.close();
    }

    static class Edge implements Comparable<Edge> {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int compareTo(Edge otherEdge) {
            return Integer.compare(this.weight, otherEdge.weight);
        }
    }

    static int findMinimalCost(int rows, int cols, int[][] grid) {
        Map<Integer, List<Edge>> tree = new HashMap<>();
        for (int i = 0; i < rows; i++) tree.put(i, new ArrayList<>());

        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < rows; j++) {
                int distance = findDistance(grid[i], grid[j]);
                tree.get(i).add(new Edge(j, distance));
                tree.get(j).add(new Edge(i, distance));
            }
        }

        Edge firstMarkedEdge = new Edge(0, 0);
        int minimumCost = findMaximumMarkingCost(firstMarkedEdge, tree);
        return minimumCost;
    }

    static int findMaximumMarkingCost(Edge firstMarkedEdge, Map<Integer, List<Edge>> tree) {
        int maxCost = 0;
        int nodeCount = tree.keySet().size();
        boolean[] isMarked = new boolean[nodeCount];
        int[] markingCost = new int[nodeCount];
        Arrays.fill(markingCost, Integer.MAX_VALUE);

        Queue<Edge> primsAlgorithmQueue = new PriorityQueue();
        primsAlgorithmQueue.add(firstMarkedEdge);

        while (!primsAlgorithmQueue.isEmpty()) {
            Edge currentEdge = primsAlgorithmQueue.poll();
            if (isMarked[currentEdge.destination]) continue;

            int currentNode = currentEdge.destination;
            isMarked[currentNode] = true;
            markingCost[currentNode] = currentEdge.weight;
            maxCost = Math.max(maxCost, markingCost[currentNode]);

            for (Edge neighBourEdge : tree.get(currentNode)) {
                if (!isMarked[neighBourEdge.destination] && neighBourEdge.weight < markingCost[neighBourEdge.destination]) {
                    markingCost[neighBourEdge.destination] = neighBourEdge.weight;
                    primsAlgorithmQueue.add(neighBourEdge);
                }
            }
        }

        return maxCost;
    }

    static int findDistance(int[] a, int[] b) {
        int diff = 0;
        for (int i = 0; i < a.length; i++) {
            diff = Math.max(diff, Math.abs(a[i] - b[i]));
        }
        return diff;
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
