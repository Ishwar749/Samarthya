package GraphTheory.EulerianPathAndCycle;

import java.io.*;
import java.util.*;

// Problem: https://judge.yosupo.jp/problem/eulerian_trail_directed

public class EulerianTrailDirected {
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
            }

            findEulerianPath(n, m, graph, out);
            out.close();
        }
    }

    static void findEulerianPath(int n, int m, List<List<Edge>> graph, PrintWriter output) {
        int[] in = new int[n];
        int[] out = new int[n];
        countInAndOutDegrees(n, graph, in, out);

        if (!graphHasEulerianPath(n, in, out)) {
            output.println("No");
            return;
        }

        Stack<Integer> eulerianNodes = new Stack<>();
        Stack<Integer> eulerianEdges = new Stack<>();
        boolean[] visited = new boolean[m];

        dfs(findStartNode(n, in, out), -1, eulerianNodes, eulerianEdges, visited, graph);

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

    static void countInAndOutDegrees(int n, List<List<Edge>> graph, int[] in, int[] out) {
        for (int i = 0; i < n; i++) {
            for (Edge e : graph.get(i)) {
                out[i]++;
                in[e.v]++;
            }
        }
    }

    static boolean graphHasEulerianPath(int n, int[] in, int[] out) {
        int startNodes = 0;
        int endNodes = 0;

        for (int i = 0; i < n; i++) {
            if ((out[i] - in[i] > 1) || (in[i] - out[i] > 1)) return false;
            else if (out[i] - in[i] == 1) startNodes++;
            else if (in[i] - out[i] == 1) endNodes++;
        }

        return (endNodes == 0 && startNodes == 0) || (endNodes == 1 && startNodes == 1);
    }

    static int findStartNode(int n, int[] in, int[] out) {
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (out[i] - in[i] == 1) return i;
            if (out[i] > 0) start = i; // This avoids setting the start to a node that has in and out both as 0;
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
