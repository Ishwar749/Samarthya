//package GraphTheory.EulerianPathAndCycle;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1693/

public class TeleportersPath {
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
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            graph.get(u).add(new Edge(v, i));
        }

        List<Integer> result = getEulerianPath(n, m, graph);

        if (result.get(0) == -1) out.println("IMPOSSIBLE");
        else {
            for (int num : result) out.print((num + 1) + " ");
        }

        out.close();
    }

    static List<Integer> getEulerianPath(int n, int m, List<List<Edge>> graph) {
        int[] in = new int[n];
        int[] out = new int[n];
        findDegrees(n, graph, in, out);

        if (!hasEulerianPath(n, m, in, out, graph)) {
            List<Integer> result = new ArrayList<>();
            result.add(-1);
            return result;
        }

        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[m];
        dfs(0, visited, graph, result);

        if ((result.size() != m + 1) || (result.get(0) != n - 1)) { // Check index 0 because list is not yet reversed.
            result = new ArrayList<>();
            result.add(-1);
        } else {
            Collections.reverse(result);
        }

        return result;
    }

    static void dfs(int node, boolean[] visited, List<List<Edge>> graph, List<Integer> result) {

        for (Edge nei : graph.get(node)) {
            if (nei.i != -1 && !visited[nei.i]) {
                visited[nei.i] = true;
                dfs(nei.v, visited, graph, result);
            }
        }

        result.add(node);
    }

    static void findDegrees(int n, List<List<Edge>> graph, int[] in, int[] out) {
        for (int i = 0; i < n; i++) {
            for (Edge e : graph.get(i)) {
                in[e.v]++;
                out[i]++;
            }
        }
    }

    static boolean hasEulerianPath(int n, int m, int[] in, int[] out, List<List<Edge>> graph) {
        int startNodes = 0;
        int endNodes = 0;

        for (int i = 0; i < n; i++) {
            if ((out[i] - in[i] > 1) || (in[i] - out[i] > 1)) {
                return false;
            }
            if (out[i] - in[i] == 1) startNodes++;
            if (in[i] - out[i] == 1) endNodes++;
        }

        if (startNodes == 0 && endNodes == 0) {
            graph.get(0).add(new Edge(n - 1, -1));
            return true;
        } else if (startNodes == 1 && endNodes == 1) {
            if (out[0] - in[0] != 1) return false;
            else if (in[n - 1] - out[n - 1] != 1) return false;
            else return true;
        } else {
            return false;
        }
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
