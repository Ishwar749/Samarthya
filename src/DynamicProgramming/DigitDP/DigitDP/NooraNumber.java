package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

// Problem: https://toph.co/p/noora-number

public class NooraNumber {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();
        int[][][] dp = new int[20][1025][10];
        for (int[][] aaa : dp) {
            for (int[] aa : aaa) {
                Arrays.fill(aa, -1);
            }
        }

        while (tests-- > 0) {
            long n = in.nextLong();
            StringBuilder lastNumber = new StringBuilder(n + "");

            int answer = findNumbers(lastNumber.length(), 0, true, 0, true, dp, lastNumber.reverse().toString()) - 1;
            out.println(answer);
        }
        out.close();
    }

    static int findNumbers(int digitsToPlace, int bitMask, boolean isLeadingZero, int maxDigitTillNow, boolean isTight, int[][][] dp, String lastNumber) {
        if (digitsToPlace == 0) {
            if (Integer.bitCount(bitMask) == maxDigitTillNow) return 1;
            return 0;
        }

        if (!isTight && !isLeadingZero && dp[digitsToPlace][bitMask][maxDigitTillNow] != -1)
            return dp[digitsToPlace][bitMask][maxDigitTillNow];

        int upperBound = isTight ? getDigitAtIndex(digitsToPlace - 1, lastNumber) : 9;
        int answer = 0;

        for (int digit = 0; digit <= upperBound; digit++) {
            boolean nextIsLeadingZero = false;
            if (isLeadingZero && digit == 0) nextIsLeadingZero = true;

            boolean nextIsTight = isTight;
            if (digit < upperBound) nextIsTight = false;

            int nextBitMask = bitMask;
            if (!nextIsLeadingZero) nextBitMask = (bitMask | (1 << digit));

            int nextMaxDigit = Math.max(maxDigitTillNow, digit);

            answer += findNumbers(digitsToPlace - 1, nextBitMask, nextIsLeadingZero, nextMaxDigit, nextIsTight, dp, lastNumber);
        }

        if (!isTight && !isLeadingZero) dp[digitsToPlace][bitMask][maxDigitTillNow] = answer;
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
