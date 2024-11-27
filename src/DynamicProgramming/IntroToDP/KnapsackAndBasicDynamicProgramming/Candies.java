package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

// Problem:  https://atcoder.jp/contests/dp/tasks/dp_m
public class Candies {

    static long mod = (long) (1e9 + 7);

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int K = in.nextInt();
        int a[] = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }

        long dp[][] = new long[N][K + 1];
        for (int i = 0; i <= a[0]; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < N; i++) {
            long pre[] = new long[K + 1];
            pre[0] = dp[i - 1][0];
            for (int j = 1; j <= K; j++) {
                pre[j] = (pre[j - 1] + dp[i - 1][j]) % mod;
            }

            for (int k = 0; k <= K; k++) {
                if (k > a[i]) {
                    dp[i][k] = (pre[k] + mod - pre[(k - a[i]) - 1]) % mod;
                } else {
                    dp[i][k] = pre[k];
                }
            }
        }

        long ans = dp[N - 1][K];
        out.println(ans);
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
