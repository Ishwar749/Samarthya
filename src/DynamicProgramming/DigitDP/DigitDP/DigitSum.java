package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

public class DigitSum {
    static int MOD = (int) (1e9 + 7);
    static int D;

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        String K = in.next();
        D = in.nextInt();

        long[][][] dp = new long[K.length() + 1][2][D];

        for (long[][] aa : dp) {
            for (long[] a : aa) {
                Arrays.fill(a, -1);
            }
        }

        long answer = findNumbers(K.length(), 1, 0, K, dp);

        String A = "0";
        for (long[][] aa : dp) {
            for (long[] a : aa) {
                Arrays.fill(a, -1);
            }
        }
        answer = (answer % MOD - findNumbers(A.length(), 1, 0, A, dp) % MOD + MOD) % MOD;
        out.println(answer);
        out.close();

    }

    static long findNumbers(int digitsToPlace, int isTight, int modDigitSum, String lastNumber, long[][][] dp) {
        if (digitsToPlace == 0) {
            return modDigitSum == 0 ? 1 : 0;
        }

        if (dp[digitsToPlace][isTight][modDigitSum] != -1)
            return dp[digitsToPlace][isTight][modDigitSum];

        int upperBound = isTight == 1 ? getDigit(lastNumber.length() - digitsToPlace, lastNumber) : 9;
        long answer = 0;

        for (int digit = 0; digit <= upperBound; digit++) {
            int nextIsTight = ((isTight == 1) && (digit == upperBound)) ? 1 : 0;
            int nextModDigitSum = (modDigitSum % D + digit % D) % D;

            answer = modAdd(answer, findNumbers(digitsToPlace - 1, nextIsTight, nextModDigitSum, lastNumber, dp));
        }

        dp[digitsToPlace][isTight][modDigitSum] = answer;
        return answer;
    }

    static long modAdd(long a, long b) {
        return (long) (a % MOD + b % MOD) % MOD;
    }

    static int getDigit(int index, String s) {
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
