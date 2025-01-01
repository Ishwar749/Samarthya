package DynamicProgramming.BitmaskDP;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/16/problem/E

public class Fish {

    static int n;
    static double[][] p;
    static double[] dp;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        n = in.nextInt();
        p = new double[n][n];
        dp = new double[1 << n];

        Arrays.fill(dp, -1);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                p[i][j] = in.nextDouble();


        for (int i = 0; i < n; i++)
            out.print(find(1 << i) + " ");
        out.close();
    }

    static double find(int bitmask) {
        if (Integer.bitCount(bitmask) == n)
            return 1.0;

        if (dp[bitmask] > -0.5)
            return dp[bitmask];

        double answer = 0;

        for (int fish = 0; fish < n; fish++) {
            if (!isSet(bitmask, fish)) {
                int prevBitMask = bitmask ^ (1 << fish);
                double prevDay = find(prevBitMask);
                answer += prevDay * pMove(prevBitMask, fish);
            }
        }

        dp[bitmask] = answer;
        return answer;
    }

    static double pMove(int prevBitMask, int j) {

        int fishesAlive = Integer.bitCount(prevBitMask);
        double denominator = (fishesAlive * (fishesAlive - 1)) / 2.0;

        double probability = 0.0;

        for (int fish = 0; fish < n; fish++) {
            if (isSet(prevBitMask, fish)) {
                probability += p[fish][j];
            }
        }

        return probability / denominator;
    }

    static boolean isSet(int mask, int pos) {
        if (((mask >> pos) & 1) == 1) return true;
        return false;
    }

    static int set(int mask, int pos) {
        return (mask | (1 << pos));
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
