package GraphTheory.EulerianPathAndCycle;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/508/problem/D

public class TanyaAndPassword {
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
        String[] s = new String[n];

        Map<String, Integer> stringToNode = new HashMap<>();
        Map<Integer, String> nodeToString = new HashMap<>();
        int index = 0;

        Map<Integer, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            s[i] = in.next();

            String a = s[i].substring(0, 2);
            String b = s[i].substring(1, 3);

            if (!stringToNode.containsKey(a)) {
                stringToNode.put(a, index);
                nodeToString.put(index, a);
                index++;
            }
            if (!stringToNode.containsKey(b)) {
                stringToNode.put(b, index);
                nodeToString.put(index, b);
                index++;
            }

            int nodeA = stringToNode.get(a);
            int nodeB = stringToNode.get(b);
            graph.putIfAbsent(nodeA, new ArrayList<>());
            graph.putIfAbsent(nodeB, new ArrayList<>());

            graph.get(nodeA).add(new Edge(nodeB, i));
        }

        solve(n, nodeToString, graph, out);
        out.close();
    }

    static void solve(int noOfStrings, Map<Integer, String> nodeToString, Map<Integer, List<Edge>> graph, PrintWriter output) {
        int nodes = nodeToString.keySet().size();
        int[] in = new int[nodes];
        int[] out = new int[nodes];
        calculateDegrees(in, out, nodes, graph);

        if (!hasEulerianPath(nodes, in, out)) {
            output.println("NO");
            return;
        } else {
            boolean[] visited = new boolean[noOfStrings]; // noOfStrings == noOfEdges
            List<Integer> pathNodes = new ArrayList<>();
            dfs(getStartNode(nodes, in, out), visited, graph, pathNodes);

            if (pathNodes.size() != noOfStrings + 1) {
                output.println("NO");
                return;
            }

            Collections.reverse(pathNodes);

            StringBuilder result = new StringBuilder();
            result.append(nodeToString.get(pathNodes.get(0)));

            for (int i = 1; i < pathNodes.size(); i++) {
                char toAdd = nodeToString.get(pathNodes.get(i)).charAt(1);
                result.append(toAdd);
            }

            output.println("YES");
            output.println(result);
        }
    }

    static void dfs(int node, boolean[] visited, Map<Integer, List<Edge>> graph, List<Integer> pathNodes) {

        for (Edge nei : graph.get(node)) {
            if (!visited[nei.i]) {
                visited[nei.i] = true;
                dfs(nei.v, visited, graph, pathNodes);
            }
        }
        pathNodes.add(node);
    }

    static int getStartNode(int nodes, int[] in, int[] out) {
        int startNode = 0;

        for (int i = 0; i < nodes; i++) {
            if (out[i] - in[i] == 1) return i;
            if (out[i] > 0) startNode = i;
        }

        return startNode;
    }

    static boolean hasEulerianPath(int nodes, int[] in, int[] out) {
        int startNodes = 0;
        int endNodes = 0;

        for (int i = 0; i < nodes; i++) {
            if (in[i] - out[i] > 1 || out[i] - in[i] > 1) return false;
            if (out[i] - in[i] == 1) startNodes++;
            if (in[i] - out[i] == 1) endNodes++;
        }

        return (startNodes == 0 && endNodes == 0) || (startNodes == 1 && endNodes == 1);
    }

    static void calculateDegrees(int[] in, int[] out, int nodes, Map<Integer, List<Edge>> graph) {
        for (int i = 0; i < nodes; i++) {
            for (Edge nei : graph.get(i)) {
                in[nei.v]++;
                out[i]++;
            }
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
