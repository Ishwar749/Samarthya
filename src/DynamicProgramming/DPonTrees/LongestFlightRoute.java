package DynamicProgramming.DPonTrees;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1680

public class LongestFlightRoute {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            graph.get(u).add(v);
        }

        int[] parent = new int[n];
        int[] depths = new int[n];
        Arrays.fill(depths, -1);

        dfs(0, -1, 1, parent, depths, graph);

        if (depths[n - 1] == -1) {
            out.println("IMPOSSIBLE");
            out.close();
            return;
        }

        int cur = n - 1;
        List<Integer> answer = new ArrayList<>();

        while (cur != -1) {
            answer.add(cur);
            cur = parent[cur];
        }

        out.println(answer.size());
        Collections.reverse(answer);

        for (int node : answer) out.print((node + 1) + " ");
        out.close();
    }

    static void dfs(int cur, int par, int depth, int[] parent, int[] depths, List<List<Integer>> graph) {
        if (depths[cur] >= depth) return;

        depths[cur] = depth;
        parent[cur] = par;

        for (int nei : graph.get(cur)) {
            if (nei != par) {
                dfs(nei, cur, depths[cur] + 1, parent, depths, graph);
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