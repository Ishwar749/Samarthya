package DynamicProgramming.DPonTrees;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/dp/tasks/dp_p

public class IndependentSet {

    static long mod = (long) (1e9 + 7);

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

        long white = dfs(0, -1, 0, dp, adj);
        long black = dfs(0, -1, 1, dp, adj);

        long answer = modAdd(white, black);
        out.println(answer);
        out.close();
    }

    static long dfs(int cur, int par, int color, long[][] dp, List<List<Integer>> adj) {
        if (dp[cur][color] != -1) return dp[cur][color];

        long answer = 1;

        if (color == 0) {
            for (int nei : adj.get(cur)) {
                if (nei == par) continue;
                long paintWhite = dfs(nei, cur, 0, dp, adj);
                long paintBlack = dfs(nei, cur, 1, dp, adj);
                answer = modMul(answer, modAdd(paintWhite, paintBlack));
            }
        } else {
            for (int nei : adj.get(cur)) {
                if (nei == par) continue;
                long paintWhite = dfs(nei, cur, 0, dp, adj);
                answer = modMul(answer, paintWhite);
            }
        }
        return dp[cur][color] = answer;
    }

    static long modAdd(long a, long b) {
        return ((a % mod) + (b % mod)) % mod;
    }

    static long modMul(long a, long b) {
        return ((a % mod) * (b % mod)) % mod;
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
