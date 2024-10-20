package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

// Problem:  https://atcoder.jp/contests/dp/tasks/dp_e

public class Knapsack2 {

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int W = in.nextInt();

        int w[] = new int[N];
        int v[] = new int[N];

        int sum = 0;

        for (int i = 0; i < N; i++) {
            w[i] = in.nextInt();
            v[i] = in.nextInt();
            sum += v[i];
        }

        long dp[][] = new long[N][sum + 1];
        for (long e[] : dp) Arrays.fill(e, Integer.MAX_VALUE);

        dp[0][0] = 0;
        dp[0][v[0]] = w[0];

        for (int i = 1; i < N; i++) {
            dp[i][0] = 0;
            for (int j = 1; j <= sum; j++) {
                // Dont Take
                dp[i][j] = dp[i - 1][j];

                // Take
                if (j - v[i] >= 0) dp[i][j] = Math.min(dp[i][j], w[i] + dp[i - 1][j - v[i]]);
            }
        }

        int answer = 0;

        for (int i = sum; i >= 0; i--) {
            if (dp[N - 1][i] <= W) {
                answer = i;
                break;
            }
        }

        out.println(answer);
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
