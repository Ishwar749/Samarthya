package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/919/B

public class PerfectNumber {

    static int[][] dp;

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int k = in.nextInt();
        dp = new int[20][11];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        long low = 0;
        long high = (long) 1e18;

        while (low < high) {
            long mid = low + (high - low) / 2;

            StringBuilder cur = new StringBuilder(mid + "");
            int digitsToPlace = cur.length();

            int countOfPerfects = findNumbers(digitsToPlace, 10, true, cur.reverse().toString());

            if (countOfPerfects >= k) high = mid;
            else low = mid + 1;
        }

        out.println(high);
        out.close();
    }

    static int findNumbers(int digitsToPlace, int sumToMake, boolean isTight, String lastNumber) {
        if (digitsToPlace == 0) {
            if (sumToMake == 0) return 1;
            else return 0;
        }

        if (!isTight && dp[digitsToPlace][sumToMake] != -1)
            return dp[digitsToPlace][sumToMake];

        int upperBound = isTight ? getDigitAtIndex(digitsToPlace - 1, lastNumber) : 9;
        int answer = 0;

        for (int digit = 0; digit <= upperBound; digit++) {
            if (sumToMake - digit >= 0) {
                boolean nextIsTight = isTight;
                if (digit < upperBound) nextIsTight = false;

                answer += findNumbers(digitsToPlace - 1, sumToMake - digit, nextIsTight, lastNumber);
            }
        }

        if (!isTight) dp[digitsToPlace][sumToMake] = answer;
        return answer;
    }

    static int getDigitAtIndex(int index, String s) {
        return s.charAt(index) - '0';
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