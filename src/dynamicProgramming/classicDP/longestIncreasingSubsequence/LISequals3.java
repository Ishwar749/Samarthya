package dynamicProgramming.classicDP.longestIncreasingSubsequence;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/abc237/tasks/abc237_f
// Editorial: https://atcoder.jp/contests/abc237/tasks/abc237_f/editorial
// CF Editorial: https://codeforces.com/blog/entry/99506

public class LISequals3 {

    static long MOD = 998244353;

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int M = in.nextInt();

        long[][][][] dp = new long[N + 1][M + 2][M + 2][M + 2];
        dp[0][M + 1][M + 1][M + 1] = 1;

        for (int i = 0; i <= N - 1; i++) {
            for (int a1 = 1; a1 <= M+1; a1++) {
                for (int a2 = 1; a2 <= M+1; a2++) {
                    for (int a3 = 1; a3 <= M+1; a3++) {
                        for (int x = 1; x <= M; x++) {
                            if (x <= a1)
                                dp[i + 1][x][a2][a3] = modAdd(dp[i + 1][x][a2][a3], dp[i][a1][a2][a3]);
                            else if (x <= a2)
                                dp[i + 1][a1][x][a3] = modAdd(dp[i + 1][a1][x][a3], dp[i][a1][a2][a3]);
                            else if (x <= a3)
                                dp[i + 1][a1][a2][x] = modAdd(dp[i + 1][a1][a2][x], dp[i][a1][a2][a3]);
                        }
                    }
                }
            }
        }

        long answer = 0;

        for (int a1 = 1; a1 <= M; a1++) {
            for (int a2 = a1 + 1; a2 <= M; a2++) {
                for (int a3 = a2 + 1; a3 <= M; a3++) {
                    answer = modAdd(answer, dp[N][a1][a2][a3]);
                }
            }
        }

        out.println(answer);
        out.close();
    }

    static long modAdd(long a, long b) {
        return (a % MOD + b % MOD) % MOD;
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
