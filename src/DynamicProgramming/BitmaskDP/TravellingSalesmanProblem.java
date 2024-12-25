package DynamicProgramming.BitmaskDP;

import java.io.*;
import java.util.*;

// Problem: https://www.spoj.com/problems/PESADA04/en/

public class TravellingSalesmanProblem {
    static long[][] dp;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();
        while (tests-- > 0) {
            int n = in.nextInt() + 1;
            int[][] adj = new int[n][n];
            dp = new long[n][1 << n];

            for (long e[] : dp) Arrays.fill(e, -1);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) j++;
                    if (j < n) adj[i][j] = in.nextInt();
                }
            }

            long answer = findMinCost(0, 0, adj);
            out.println(answer);
        }
        out.close();
    }

    static long findMinCost(int cur, int bitmask, int[][] adj) {
        if (cur == 0 && bitmask != 0) {
            if (bitmask == ((1 << adj.length) - 1)) return 0;
            else return 25000;
        }

        if (dp[cur][bitmask] != -1) return dp[cur][bitmask];

        long minCost = Long.MAX_VALUE;

        for (int nei = 0; nei < adj.length; nei++) {
            if (nei != cur && !isSet(bitmask, nei)) {
                minCost = Math.min(minCost, findMinCost(nei, set(bitmask, nei), adj) + adj[cur][nei]);
            }
        }

        dp[cur][bitmask] = minCost;
        return minCost;
    }

    static boolean isSet(int mask, int pos) {
        if (((mask >> pos) & 1) == 1) return true;
        return false;
    }

    static int set(int mask, int pos) {
        return (mask | (1 << pos));
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
