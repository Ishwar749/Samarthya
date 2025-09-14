package Basics.BinarySearch;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/edu/course/2/lesson/6/2/practice/contest/283932/problem/B

public class Ropes {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();
        double[] a = new double[n];

        for (int i = 0; i < n; i++) a[i] = in.nextDouble();

        double answer = solve(n, k, a);
        out.println(answer);
        out.close();
    }

    static double solve(int n, int k, double[] a) {
        double l = 0;
        double r = 1e8;

        for (int i = 0; i < 100; i++) {
            double mid = l + (r - l) / (double) 2;

            if (isGood(mid, a, k)) l = mid;
            else r = mid;
        }
        return l;
    }

    static boolean isGood(double mid, double[] a, int k) {
        int fit = 0;
        for (double e : a) fit += (int) (e / mid);

        return fit >= k;
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
