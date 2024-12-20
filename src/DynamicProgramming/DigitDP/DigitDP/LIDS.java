package DynamicProgramming.DigitDP.DigitDP;

import java.io.*;
import java.util.*;

// Problem: https://toph.co/p/lids

public class LIDS {

    static String L;
    static String R;

    static class Pair {
        int length;
        int ways;

        public Pair(int length, int ways) {
            this.length = length;
            this.ways = ways;
        }
    }

    static Pair[][][][][][] dp = new Pair[12][12][12][2][2][2];

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        for (int test = 1; test <= tests; test++) {
            long x = in.nextLong();
            long y = in.nextLong();

            L = x + "";
            R = y + "";

            while (L.length() < R.length()) {
                L = "0" + L;
            }

            for (Pair[][][][][] ppppp : dp) {
                for (Pair[][][][] pppp : ppppp) {
                    for (Pair[][][] ppp : pppp) {
                        for (Pair[][] pp : ppp) {
                            for (Pair[] p : pp) {
                                Arrays.fill(p, new Pair(-1, -1));
                            }
                        }
                    }
                }
            }

            Pair answer = find(0, 0, 0, 0, 0, 0);
            out.println("Case " + test + ": " + answer.length + " " + answer.ways);
        }
        out.close();
    }

    static Pair find(int lengthOfLIDS, int lengthOfSequence, int lastDigitInLIDS, int isHigher, int isSmaller, int isNonZero) {
        if (lengthOfSequence == R.length())
            return new Pair(lengthOfLIDS, 1);

        if (dp[lengthOfLIDS][lengthOfSequence][lastDigitInLIDS][isHigher][isSmaller][isNonZero].length != -1)
            return dp[lengthOfLIDS][lengthOfSequence][lastDigitInLIDS][isHigher][isSmaller][isNonZero];

        Pair currentAnswer = new Pair(-1, -1);

        int lowerBound = (isHigher == 1) ? 0 : L.charAt(lengthOfSequence) - '0';
        int upperBound = (isSmaller == 1) ? 9 : R.charAt(lengthOfSequence) - '0';

        for (int digit = lowerBound; digit <= upperBound; digit++) {
            int nextIsHigher = isHigher;
            if (digit > lowerBound) nextIsHigher = 1;

            int nextIsSmaller = isSmaller;
            if (digit < upperBound) nextIsSmaller = 1;

            int nextIsNonZero = isNonZero;
            if (digit != 0) nextIsNonZero = 1;

            currentAnswer = combine(currentAnswer, find(lengthOfLIDS, lengthOfSequence + 1, lastDigitInLIDS, nextIsHigher, nextIsSmaller, nextIsNonZero));

            if (digit > lastDigitInLIDS || (digit == 0 && lengthOfLIDS == 0 && (isNonZero == 1))) {
                currentAnswer = combine(currentAnswer, find(lengthOfLIDS + 1, lengthOfSequence + 1, digit, nextIsHigher, nextIsSmaller, nextIsNonZero));
            }
        }

        dp[lengthOfLIDS][lengthOfSequence][lastDigitInLIDS][isHigher][isSmaller][isNonZero] = currentAnswer;
        return currentAnswer;
    }

    static Pair combine(Pair a, Pair b) {
        if (a.length == b.length) {
            return new Pair(a.length, a.ways + b.ways);
        } else if (a.length > b.length) return new Pair(a.length, a.ways);
        return new Pair(b.length, b.ways);
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
