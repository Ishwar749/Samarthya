package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

public class PerfectNumber {

    static int[][][] dp;

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int k = in.nextInt();

        long low = 0;
        long high = (long) 1e18;

        while (low < high) {
            long mid = low + (high - low) / 2;

            String cur = mid + "";
            int digitsToPlace = cur.length();
            dp = new int[20][11][2];
            for (int[][] aa : dp) {
                for (int[] a : aa) {
                    Arrays.fill(a, -1);
                }
            }

            int countOfPerfects = findNumbers(digitsToPlace, 10, 1, cur);

            if (countOfPerfects >= k) high = mid;
            else low = mid + 1;
        }

        out.println(high);
        out.close();
    }

    static int findNumbers(int digitsToPlace, int sumToMake, int isTight, String lastNumber) {
        if (digitsToPlace == 0) {
            if (sumToMake == 0) return 1;
            else return 0;
        }

        if (dp[digitsToPlace][sumToMake][isTight] != -1)
            return dp[digitsToPlace][sumToMake][isTight];

        int upperBound = isTight == 1 ? getDigitAtIndex(lastNumber.length() - digitsToPlace, lastNumber) : 9;
        int answer = 0;

        for (int digit = 0; digit <= upperBound; digit++) {
            if (sumToMake - digit >= 0) {
                int nextIsTight = isTight;
                if (digit < upperBound) nextIsTight = 0;

                answer += findNumbers(digitsToPlace - 1, sumToMake - digit, nextIsTight, lastNumber);
            }
        }

        dp[digitsToPlace][sumToMake][isTight] = answer;
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