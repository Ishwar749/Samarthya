package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/dp/tasks/dp_n

public class Slimes {

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();

        long sum[][] = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = i; k <= j; k++) {
                    sum[i][j] += a[k];
                }
            }
        }

        long dp[][] = new long[n][n];
        for (long e[] : dp) Arrays.fill(e, Long.MAX_VALUE);

        for (int l = n - 1; l >= 0; l--) {
            for (int r = l; r < n; r++) {
                if (l == r) dp[l][r] = 0;
                else {
                    for (int x = l; x < r; x++) {
                        dp[l][r] = Math.min(dp[l][r], dp[l][x] + dp[x + 1][r] + sum[l][r]);
                    }
                }
            }
        }

        out.println(dp[0][n - 1]);
        out.close();

    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
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
