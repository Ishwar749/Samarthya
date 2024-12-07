package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

public class Investigation {

    static int A;
    static int B;
    static int K;
    static int[][][][] dp;

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        for (int test = 1; test <= tests; test++) {
            A = in.nextInt() - 1;
            B = in.nextInt();
            K = in.nextInt();

            if (K > 90) out.println("Case " + test + ": 0");
            else {
                dp = new int[10][2][100][100];

                fillArray(dp);
                String BB = B + "";
                int answerTillB = findNumbers(BB.length(), 1, 0, 0, BB);

                fillArray(dp);
                String AA = A + "";
                int answerTillA = findNumbers(AA.length(), 1, 0, 0, AA);

                int answer = answerTillB - answerTillA;
                out.println("Case " + test + ": " + answer);
            }
        }
        out.close();
    }

    static int findNumbers(int digitsToPlace, int isTight, int modMultiplication, int modDigitSum, String lastNumber) {
        if (digitsToPlace == 0) {
            if (modMultiplication == 0 && modDigitSum == 0) return 1;
            return 0;
        }

        if (dp[digitsToPlace][isTight][modMultiplication][modDigitSum] != -1)
            return dp[digitsToPlace][isTight][modMultiplication][modDigitSum];

        int upperBound = isTight == 1 ? getDigit(lastNumber.length() - digitsToPlace, lastNumber) : 9;
        int answer = 0;

        for (int digit = 0; digit <= upperBound; digit++) {
            int nextIsTight = ((isTight == 1) && (digit == upperBound)) ? 1 : 0;
            int nextModMultiplication = (int) (((modMultiplication * 10) + digit) % K);
            int nextModDigitSum = modAdd(modDigitSum, digit);
            answer += findNumbers(digitsToPlace - 1, nextIsTight, nextModMultiplication, nextModDigitSum, lastNumber);
        }

        dp[digitsToPlace][isTight][modMultiplication][modDigitSum] = answer;
        return answer;
    }

    static int modAdd(int a, int b) {
        return (a % K + b % K) % K;
    }


    static int getDigit(int index, String lastNumber) {
        return lastNumber.charAt(index) - '0';
    }

    static void fillArray(int[][][][] dp) {
        for (int[][][] aaa : dp) {
            for (int[][] aa : aaa) {
                for (int[] a : aa) {
                    Arrays.fill(a, -1);
                }
            }
        }
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
