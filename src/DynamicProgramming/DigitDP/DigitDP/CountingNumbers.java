package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/2220
// Solution: https://www.youtube.com/watch?v=lD_irvBkeOk&list=PLb3g_Z8nEv1hB69JL9K7KfEyK8iQNj9nX&index=5

public class CountingNumbers {

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        long a = in.nextLong() - 1;
        long b = in.nextLong();

        long answerForZeroToB = find(b);
        long answer = answerForZeroToB;

        if (a >= 0) {
            long answerForZeroToA = find(a);
            answer -= answerForZeroToA;
        }

        out.println(answer);
        out.close();
    }

    static long find(long range) {

        String lastNumber = range + "";

        long[][][][] dp = new long[lastNumber.length() + 1][11][2][2];

        for (long[][][] eee : dp) {
            for (long[][] ee : eee) {
                for (long[] e : ee) {
                    Arrays.fill(e, -1);
                }
            }
        }

        return findNumbers(lastNumber.length(), 10, 1, 1, dp, lastNumber);
    }

    static long findNumbers(int digitsToPlace, int lastDigitPlaced, int isLeadingZero, int isTight, long[][][][] dp, String lastNumber) {
        if (digitsToPlace == 0)
            return 1;

        if (dp[digitsToPlace][lastDigitPlaced][isLeadingZero][isTight] != -1)
            return dp[digitsToPlace][lastDigitPlaced][isLeadingZero][isTight];

        int upperBound = isTight == 1 ? getDigitAtIndex(lastNumber.length() - digitsToPlace, lastNumber) : 9;
        long answer = 0;

        for (int digit = 0; digit <= upperBound; digit++) {
            if (digit == lastDigitPlaced && isLeadingZero == 0)
                continue;

            int nextIsTight = ((isTight == 1) && (digit == upperBound)) ? 1 : 0;
            int nextIsLeadingZero = ((isLeadingZero == 1) && (digit == 0)) ? 1 : 0;

            if (digit < upperBound) nextIsTight = 0;

            answer += findNumbers(digitsToPlace - 1, digit, nextIsLeadingZero, nextIsTight, dp, lastNumber);
        }

        dp[digitsToPlace][lastDigitPlaced][isLeadingZero][isTight] = answer;
        return answer;
    }

    static int getDigitAtIndex(int index, String s) {
        return s.charAt(index) - '0';
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
