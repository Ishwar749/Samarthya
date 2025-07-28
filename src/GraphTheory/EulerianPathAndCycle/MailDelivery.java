package GraphTheory.EulerianPathAndCycle;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1691/

public class MailDelivery {

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

        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            graph.get(u).add(new Edge(v, i));
            graph.get(v).add(new Edge(u, i));
        }

        findEulerianCircuit(n, m, graph, out);
        out.close();
    }

    static void findEulerianCircuit(int n, int m, List<List<Edge>> graph, PrintWriter out) {
        int[] degree = new int[n];
        calculateDegrees(n, degree, graph);

        if (!hasEulerianCircuit(degree)) {
            out.print("IMPOSSIBLE");
            return;
        }

        boolean[] visited = new boolean[m];
        List<Integer> circuit = new ArrayList<>();

        dfs(0, visited, graph, circuit);
        Collections.reverse(circuit);

        if (circuit.size() != m + 1) {
            out.print("IMPOSSIBLE");
            return;
        }
        for (int e : circuit) out.print((e + 1) + " ");
    }

    static void dfs(int cur, boolean[] visited, List<List<Edge>> graph, List<Integer> circuit) {
        for (Edge e : graph.get(cur)) {
            if (!visited[e.i]) {
                visited[e.i] = true;
                dfs(e.v, visited, graph, circuit);
            }
        }
        circuit.add(cur);
    }

    static void calculateDegrees(int n, int[] degree, List<List<Edge>> graph) {
        for (int i = 0; i < n; i++) {
            for (Edge e : graph.get(i)) degree[e.v]++;
        }
    }

    static boolean hasEulerianCircuit(int[] degree) {
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] % 2 != 0) return false;
        }

        return true;
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
