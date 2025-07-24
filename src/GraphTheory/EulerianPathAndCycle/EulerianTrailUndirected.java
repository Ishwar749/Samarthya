package GraphTheory.EulerianPathAndCycle;

import java.io.*;
import java.util.*;
// Problem: https://judge.yosupo.jp/problem/eulerian_trail_undirected

public class EulerianTrailUndirected {
    static class Edge {
        int v;
        int i;

        public Edge(int v, int i) {
            this.v = v;
            this.i = i;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();

            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                graph.get(u).add(new Edge(v, i));
                graph.get(v).add(new Edge(u, i));
            }

            findEulerianPath(n, m, graph, out);
        }
        out.close();
    }

    static void findEulerianPath(int n, int m, List<List<Edge>> graph, PrintWriter output) {
        int[] degree = new int[n];
        findDegree(n, graph, degree);

        if (!findHasEulerianPath(n, degree)) {
            output.println("No");
            return;
        }

        Stack<Integer> eulerianNodes = new Stack<>();
        Stack<Integer> eulerianEdges = new Stack<>();
        boolean[] visited = new boolean[m];

        dfs(findStartNode(n, degree), -1, eulerianNodes, eulerianEdges, visited, graph);

        if (eulerianNodes.size() == m + 1) {
            output.println("Yes");
            while (!eulerianNodes.isEmpty()) {
                output.print(eulerianNodes.pop() + " ");
            }
            output.println();

            eulerianEdges.pop();
            while (!eulerianEdges.isEmpty()) {
                output.print(eulerianEdges.pop() + " ");
            }
            output.println();
        } else {
            output.println("No");
        }
    }

    static void dfs(int node, int edgeNo, Stack<Integer> eulerianNodes, Stack<Integer> eulerianEdges, boolean[] visited, List<List<Edge>> graph) {

        for (Edge nei : graph.get(node)) {
            if (!visited[nei.i]) {
                visited[nei.i] = true;
                dfs(nei.v, nei.i, eulerianNodes, eulerianEdges, visited, graph);
            }
        }

        eulerianNodes.push(node);
        eulerianEdges.push(edgeNo);
    }

    static void findDegree(int n, List<List<Edge>> graph, int[] degree) {
        for (int i = 0; i < n; i++) {
            for (Edge e : graph.get(i)) {
                degree[e.v]++;
            }
        }
    }

    static boolean findHasEulerianPath(int n, int[] degree) {
        int pathStartEndNodes = 0;
        for (int i = 0; i < n; i++) {
            if (degree[i] % 2 == 1) pathStartEndNodes++;
        }

        if (pathStartEndNodes == 0 || pathStartEndNodes == 2) return true;
        return false;
    }

    static int findStartNode(int n, int[] degree) {
        int start = 0;

        for (int i = 0; i < n; i++) {
            if (degree[i] % 2 == 1) return i;
            if (degree[i] > 0) start = i;
        }

        return start;
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
