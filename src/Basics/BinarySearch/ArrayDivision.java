package Basics.BinarySearch;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1085

public class ArrayDivision {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) a[i] = in.nextInt();

        out.println(solve(n, k, a));
        out.close();
    }

    static long solve(int n, int k, int[] a) {
        long l = -1;
        for (int e : a) l = Math.max(l, e);

        long r = (3L * (long) 1e14);

        while (l < r) {
            long mid = l + (r - l) / 2;

            if (isGood(mid, k, n, a)) r = mid;
            else l = mid + 1;
        }

        return l;
    }

    static boolean isGood(long mid, int k, int n, int[] a) {
        long sum = 0;

        for (int i = 0; i < n; i++) {
            if (sum + a[i] > mid) {
                k--;
                sum = a[i];
            } else {
                sum += a[i];
            }
        }
        k--;

        return k >= 0;
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
