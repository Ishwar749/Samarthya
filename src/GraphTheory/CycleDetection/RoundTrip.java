package GraphTheory.CycleDetection;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1669

public class RoundTrip {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int cityCount = in.nextInt();
        int roadCount = in.nextInt();

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i <= cityCount; i++) graph.put(i, new ArrayList<>());

        for (int i = 0; i < roadCount; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        int[] parent = new int[cityCount + 1];
        Arrays.fill(parent, -1);
        int[] cycleStartEnd = {-1, -1};

        for (int i = 1; i <= cityCount; i++) {
            if (parent[i] == -1) {
                parent[i] = 0;
                dfs(i, graph, parent, cycleStartEnd);
                if (cycleStartEnd[0] != -1) break;
            }
        }

        if (cycleStartEnd[0] == -1) {
            out.print("IMPOSSIBLE");
        } else {
            List<Integer> result = constructCycle(cycleStartEnd, parent);
            out.println(result.size());
            for (int city : result) out.print(city + " ");
        }
        out.close();
    }

    static List<Integer> constructCycle(int[] cycleStartEnd, int[] parent) {

        List<Integer> result = new ArrayList<>();
        result.add(cycleStartEnd[0]);

        for (int cur = cycleStartEnd[1]; ; ) {
            result.add(cur);
            if (cur == cycleStartEnd[0]) break;
            cur = parent[cur];
        }


        return result;
    }

    static void dfs(int city, Map<Integer, List<Integer>> graph, int[] parent, int[] cycleStartEnd) {

        if (cycleStartEnd[0] != -1) return;

        for (int nei : graph.get(city)) {
            if (parent[nei] == -1) {
                parent[nei] = city;
                dfs(nei, graph, parent, cycleStartEnd);
            } else if (nei != parent[city]) {
                cycleStartEnd[0] = city;
                cycleStartEnd[1] = nei;
                return;
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
