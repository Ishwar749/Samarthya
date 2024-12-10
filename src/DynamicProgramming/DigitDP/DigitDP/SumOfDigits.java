package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

public class SumOfDigits {

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            int a = in.nextInt() - 1;
            int b = in.nextInt();

            if (a == -2 && b == -1) break;

            long answer = findNumbers(b);
            if (a > 0) answer -= findNumbers(a);

            out.println(answer);
        }

        out.close();
    }

    static long findNumbers(int N) {

        String NN = N + "";
        int digitsInN = NN.length();
        long answer = 0;
        int[][] dp = new int[digitsInN + 1][91];
        for (int[] e : dp) {
            Arrays.fill(e, -1);
        }


        for (int i = 90; i >= 1; i--) {
            answer += ((long) findCountOfNumbers(digitsInN, i, true, dp, NN) * i);
        }

        return answer;
    }

    static int findCountOfNumbers(int digitsToPlace, int sumToMake, boolean isTight, int[][] dp, String lastNumber) {
        if (digitsToPlace == 0)
            return sumToMake == 0 ? 1 : 0;


        if (!isTight && dp[digitsToPlace][sumToMake] != -1)
            return dp[digitsToPlace][sumToMake];

        int upperBound = isTight ? getDigitAtIndex(lastNumber.length() - digitsToPlace, lastNumber) : 9;

        int answer = 0;

        for (int digit = 0; digit <= upperBound; digit++) {
            if (sumToMake - digit >= 0) {
                boolean nextIsTight = isTight;
                if (digit < upperBound) nextIsTight = false;

                answer += findCountOfNumbers(digitsToPlace - 1, sumToMake - digit, nextIsTight, dp, lastNumber);
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
