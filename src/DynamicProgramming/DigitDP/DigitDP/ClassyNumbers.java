package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/1036/problem/C

public class ClassyNumbers {
    static int[][] dp;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();
        dp = new int[20][4];
        for (int e[] : dp) {
            Arrays.fill(e, -1);
        }

        while (tests-- > 0) {
            long L = in.nextLong() - 1;
            long R = in.nextLong();

            StringBuilder RR = new StringBuilder(R + "");
            int answer = findNumbers(RR.length(), 3, true, RR.reverse().toString());

            StringBuilder LL = new StringBuilder(L + "");
            answer -= findNumbers(LL.length(), 3, true, LL.reverse().toString());

            out.println(answer);
        }
        out.close();
    }

    static int findNumbers(int digitsToPlace, int nonZeroDigits, boolean isTight, String lastNumber) {
        if (digitsToPlace == 0) return 1;

        if (!isTight && dp[digitsToPlace][nonZeroDigits] != -1)
            return dp[digitsToPlace][nonZeroDigits];

        int upperBound = isTight ? getDigitAtIndex(digitsToPlace - 1, lastNumber) : 9;
        int answer = 0;

        for (int digit = 0; digit <= upperBound; digit++) {
            if (digit == 0) {
                boolean nextIsTight = isTight;
                if (digit < upperBound) nextIsTight = false;

                answer += findNumbers(digitsToPlace - 1, nonZeroDigits, nextIsTight, lastNumber);
            } else if (digit > 0 && nonZeroDigits - 1 >= 0) {
                boolean nextIsTight = isTight;
                if (digit < upperBound) nextIsTight = false;

                answer += findNumbers(digitsToPlace - 1, nonZeroDigits - 1, nextIsTight, lastNumber);
            }
        }

        if (!isTight) dp[digitsToPlace][nonZeroDigits] = answer;
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
