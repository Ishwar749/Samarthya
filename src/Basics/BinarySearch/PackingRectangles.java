package Basics.BinarySearch;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/edu/course/2/lesson/6/2/practice/contest/283932/problem/A

public class PackingRectangles {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        long w = in.nextInt();
        long h = in.nextInt();
        long n = in.nextInt();

        long L = 0L;
        long R = 1L;
        while (!fits(R, h, w, n)) {
            R *= 2L;
        }

        while (L < R) {
            long mid = L + (R - L) / 2;
            if (fits(mid, h, w, n)) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        out.println(L);
        out.close();
    }

    static boolean fits(long x, long h, long w, long n) {
        return (x / h) * (x / w) >= n;
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
