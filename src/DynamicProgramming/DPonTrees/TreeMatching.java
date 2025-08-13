package DynamicProgramming.DPonTrees;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1130

public class TreeMatching {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        long[][] dp = new long[n][2];
        for (long[] row : dp) Arrays.fill(row, -1);

        long answer = Math.max(dfs(0, -1, 0, dp, adj), dfs(0, -1, 1, dp, adj));
        out.println(answer);
        out.close();
    }

    static long dfs(int cur, int par, int take, long[][] dp, List<List<Integer>> adj) {

        if (dp[cur][take] != -1) return dp[cur][take];

        long answer = 0;

        for (int nei : adj.get(cur)) {
            if (nei == par) continue;
            answer += dfs(nei, cur, 1, dp, adj);
        }


        if (take == 1) {
            long max = 0;
            for (int nei : adj.get(cur)) {
                if (nei == par) continue;
                long temp = dfs(nei, cur, 0, dp, adj) + 1;
                long tempAnswer = (answer - dfs(nei, cur, 1, dp, adj)) + temp;
                max = Math.max(max, tempAnswer);
            }
            dp[cur][take] = max;
            return max;
        } else {
            dp[cur][take] = answer;
            return answer;
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
