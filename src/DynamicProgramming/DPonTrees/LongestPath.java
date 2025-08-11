package DynamicProgramming.DPonTrees;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/dp/tasks/dp_g

public class LongestPath {
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

        long[] dp = new long[n];
        Arrays.fill(dp, -1);
        long answer = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == -1) {
                dfs(i, -1, graph, dp);
            }
            answer = Math.max(answer, dp[i]);
        }

        if (answer > 0) answer--;
        out.println(answer);
        out.close();
    }

    static long dfs(int cur, int par, List<List<Integer>> graph, long[] dp) {
        if (dp[cur] != -1) return dp[cur];

        long answer = 0;

        for (int nei : graph.get(cur)) {
            if (nei == par) continue;
            answer = Math.max(answer, dfs(nei, cur, graph, dp));
        }

        return dp[cur] = answer + 1;
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
