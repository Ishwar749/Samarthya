package DynamicProgramming.BitmaskDP;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/dp/tasks/dp_o

public class Matching {
    static long[][] dp = new long[22][1 << 22];
    static long MOD = (long) (1e9 + 7);

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }

        for (long[] e : dp) {
            Arrays.fill(e, -1);
        }

        long answer = findCompatibles(0, 0, a);
        out.println(answer);
        out.close();
    }

    static long findCompatibles(int ind, int bitmask, int[][] a) {
        if (ind == a.length)
            return 1;

        if (dp[ind][bitmask] != -1)
            return dp[ind][bitmask];

        long ways = 0;

        for (int j = 0; j < a.length; j++) {
            if (a[ind][j] == 1 && !isSet(bitmask, j)) {
                ways = modAdd(ways, findCompatibles(ind + 1, set(bitmask, j), a));
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
