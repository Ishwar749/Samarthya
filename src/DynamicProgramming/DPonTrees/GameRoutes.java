package DynamicProgramming.DPonTrees;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1681

public class GameRoutes {
    static int n;
    static long mod = (long) ((1e9) + 7);

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            adj.get(u).add(v);
        }

        long[] dp = new long[n];
        Arrays.fill(dp, -1);

        long answer = dfs(0, -1, dp, adj);
        out.println(answer);
        out.close();
    }

    static long dfs(int cur, int par, long[] dp, List<List<Integer>> adj) {
        if (cur == n - 1) return 1;
        if (dp[cur] != -1) return dp[cur];

        long answer = 0;
        for (int nei : adj.get(cur)) {
            if (nei == par) continue;
            answer = modAdd(answer, dfs(nei, cur, dp, adj));
        }

        return dp[cur] = answer;
    }

    static long modAdd(long a, long b) {
        return ((a % mod) + (b % mod)) % mod;
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
