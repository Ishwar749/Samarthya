package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

public class DigitCount {

    static boolean[] allowedDigits;

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        for (int test = 1; test <= tests; test++) {
            int m = in.nextInt();
            int n = in.nextInt();

            allowedDigits = new boolean[10];
            for (int i = 0; i < m; i++) {
                int digit = in.nextInt();
                allowedDigits[digit] = true;
            }

            int[][] dp = new int[n + 1][10];
            for (int[] e : dp) {
                Arrays.fill(e, -1);
            }

            int answer = 0;

            for (int i = 0; i < 10; i++) {
                if (allowedDigits[i]) answer += findNumbers(n - 1, i, dp);
            }

            out.println("Case " + test + ": " + answer);
        }
        out.close();
    }

    static int findNumbers(int n, int lastDigitPlaced, int[][] dp) {
        if (n == 0) return 1;

        if (dp[n][lastDigitPlaced] != -1) return dp[n][lastDigitPlaced];

        int answer = 0;

        for (int i = -2; i <= 2; i++) {
            if (isAllowedDigit(lastDigitPlaced + i)) answer += findNumbers(n - 1, lastDigitPlaced + i, dp);
        }

        dp[n][lastDigitPlaced] = answer;
        return answer;
    }

    static boolean isAllowedDigit(int x) {
        return (x < 10 && x >= 0) && (allowedDigits[x]);
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
