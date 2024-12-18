package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/1073/problem/E

public class SegmentSum {
    static long L;
    static long R;
    static int K;
    static long MOD = 998244353;
    static long[][][][] dp;
    static long[] powTen;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        L = in.nextLong() - 1;
        R = in.nextLong();
        K = in.nextInt();

        powTen = new long[20];
        fillPowTen();
        dp = new long[20][1025][2][];

        String RR = R + "";
        long[] ansR = findSum(RR.length(), 0, 1, true, RR);

        String LL = L + "";
        long[] ansL = findSum(LL.length(), 0, 1, true, LL);

        long answer = (ansR[1] - ansL[1] + MOD) % MOD;

        out.println(answer);
        out.close();
    }

    // Returns {Count, Sum}
    static long[] findSum(int digitsToPlace, int bitMask, int nonZeroDigits, boolean isTight, String lastNumber) {
        if (Integer.bitCount(bitMask) > K) return new long[]{0, 0};
        if (digitsToPlace == 0) return new long[]{1, 0};
        if (!isTight && dp[digitsToPlace][bitMask][nonZeroDigits] != null)
            return dp[digitsToPlace][bitMask][nonZeroDigits];

        int upperBound = isTight ? getDigitAtIndex(lastNumber.length() - digitsToPlace, lastNumber) : 9;
        long curCount = 0;
        long curSum = 0;

        for (int digit = 0; digit <= upperBound; digit++) {
            int nextNonZero = nonZeroDigits;
            if (digit > 0) nextNonZero = 0;

            int nextBitMask = bitMask;
            if (nextNonZero == 0) nextBitMask = (bitMask | (1 << digit));

            boolean nextIsTight = isTight;
            if (digit < upperBound) nextIsTight = false;

            long[] tempAnswer = findSum(digitsToPlace - 1, nextBitMask, nextNonZero, nextIsTight, lastNumber);
            curCount = modAdd(curCount, tempAnswer[0]);
            curSum = modAdd(curSum, modAdd(tempAnswer[1], modMul(tempAnswer[0], modMul(digit, powTen[digitsToPlace - 1]))));
        }

        long[] answer = {curCount, curSum};
        if (!isTight) dp[digitsToPlace][bitMask][nonZeroDigits] = answer;
        return answer;
    }

    static long modMul(long a, long b) {
        return (a % MOD * b % MOD) % MOD;
    }

    static long modAdd(long a, long b) {
        return (a % MOD + b % MOD) % MOD;
    }

    static int getDigitAtIndex(int index, String s) {
        return s.charAt(index) - '0';
    }

    static void fillPowTen() {
        powTen[0] = 1;
        for (int i = 1; i < 20; i++) {
            powTen[i] = modMul(powTen[i - 1], 10L);
        }
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
