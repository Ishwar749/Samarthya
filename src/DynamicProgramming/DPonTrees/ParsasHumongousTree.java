package DynamicProgramming.DPonTrees;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1528/A

public class ParsasHumongousTree {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int[][] range = new int[n][2];
            List<List<Integer>> tree = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                tree.add(new ArrayList<>());
                range[i][0] = in.nextInt();
                range[i][1] = in.nextInt();
            }

            for (int i = 0; i < n - 1; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                tree.get(u).add(v);
                tree.get(v).add(u);
            }

            long[][] dp = new long[n][2];
            for (long[] row : dp) Arrays.fill(row, -1);

            long answer = Math.max(dfs(0, -1, 0, range, dp, tree), dfs(0, -1, 1, range, dp, tree));
            out.println(answer);
        }

        out.close();
    }

    static long dfs(int cur, int par, int ind, int[][] range, long[][] dp, List<List<Integer>> tree) {

        if (dp[cur][ind] != -1) return dp[cur][ind];

        long answer = 0;

        for (int nei : tree.get(cur)) {
            if (nei == par) continue;

            long lower = (long) Math.abs(range[cur][ind] - range[nei][0]) + dfs(nei, cur, 0, range, dp, tree);
            long higher = (long) Math.abs(range[cur][ind] - range[nei][1]) + dfs(nei, cur, 1, range, dp, tree);
            answer = answer + Math.max(lower, higher);
        }

        return dp[cur][ind] = answer;

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
