package Basics.BinarySearch;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/edu/course/2/lesson/6/2/practice/contest/283932/problem/E

public class Equation {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        double c = in.nextDouble();
        double answer = solve(c);

        out.println(answer);
        out.close();
    }

    static double solve(double c) {
        double l = 0;
        double r = c;

        for (int i = 0; i < 100; i++) {
            double mid = (l + r) / (double) 2;
            if (isGood(mid, c)) l = mid;
            else r = mid;
        }

        return l;
    }

    static boolean isGood(double mid, double c) {
        double value = (mid * mid) + Math.sqrt(mid);
        return value <= c;
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
