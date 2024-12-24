package DynamicProgramming.BitmaskDP;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1690
// The Solution below gave TLE:

public class HamiltonianFlights {
    static long MOD = (long) (1e9 + 7);
    static long[][] dp;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        dp = new long[n][1 << n];
        for (long[] e : dp) Arrays.fill(e, -1);

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            adj.get(u).add(v);
        }

        long answer = findWays(0, 1, adj);
        out.println(answer);
        out.close();
    }

    static long findWays(int ind, int bitmask, List<List<Integer>> adj) {
        if (ind == adj.size() - 1 && Integer.bitCount(bitmask) == adj.size()) return 1;
        else if (ind == adj.size() - 1) return 0;
        else if (Integer.bitCount(bitmask) == adj.size()) return 0;

        if (dp[ind][bitmask] != -1)
            return dp[ind][bitmask];

        long ways = 0;

        for (int nei : adj.get(ind)) {
            if (nei != ind && !isSet(bitmask, nei)) {
                ways = modAdd(ways, findWays(nei, set(bitmask, nei), adj));
            }
        }

        dp[ind][bitmask] = ways;
        return ways;
    }

    static boolean isSet(int mask, int pos) {
        if (((mask >> pos) & 1) == 1) return true;
        return false;
    }

    static int set(int mask, int pos) {
        return (mask | (1 << pos));
    }

    static long modAdd(long a, long b) {
        return (a % MOD + b % MOD) % MOD;
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
