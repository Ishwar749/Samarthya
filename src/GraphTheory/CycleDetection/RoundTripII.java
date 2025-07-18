package GraphTheory.CycleDetection;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1678/

public class RoundTripII {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int cities = in.nextInt();
        int flights = in.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= cities; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < flights; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            graph.get(from).add(to);
        }

        int[] parent = new int[cities + 1];
        Arrays.fill(parent, -1);
        int[] color = new int[cities + 1];
        int[] cycleStart = {-1};

        for (int i = 1; i <= cities; i++) {
            if (parent[i] == -1) {
                parent[i] = 0;
                dfs(i, parent, color, cycleStart, graph);
                if (cycleStart[0] != -1) break;
            }
        }

        if (cycleStart[0] == -1) {
            out.println("IMPOSSIBLE");
        } else {
            List<Integer> cycleCities = constructCycle(cycleStart, parent);
            out.println(cycleCities.size());
            for (int city : cycleCities) out.print(city + " ");
        }

        out.close();
    }

    static List<Integer> constructCycle(int[] cycleStart, int[] parent) {

        List<Integer> result = new ArrayList<>();

        for (int cur = cycleStart[0]; ; ) {
            result.add(cur);
            if (cur == cycleStart[0] && result.size() > 1) break;
            cur = parent[cur];
        }

        Collections.reverse(result);
        return result;
    }

    static void dfs(int currentCity, int[] parent, int[] color, int[] cycleStart, List<List<Integer>> graph) {

        if (cycleStart[0] != -1) return;
        color[currentCity] = 1;

        for (int nei : graph.get(currentCity)) {
            if (parent[nei] == -1) {
                parent[nei] = currentCity;
                dfs(nei, parent, color, cycleStart, graph);
            } else if (color[nei] == 1) {
                parent[nei] = currentCity;
                cycleStart[0] = currentCity;
                return;
            }
        }

        color[currentCity] = 2;
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
