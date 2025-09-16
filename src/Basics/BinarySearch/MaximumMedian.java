package Basics.BinarySearch;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/1201/problem/C
public class MaximumMedian {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        Arrays.sort(a);

        out.println(solve(n, k, a));
        out.close();
    }

    static long solve(int n, int k, int[] a) {
        long l = a[n / 2];
        long r = (long) (1e11);

        while (l < r) {
            long mid = l + (r - l + 1) / 2;
            if (isGood(a, n, k, mid)) l = mid;
            else r = mid - 1;
        }

        return l;
    }

    static boolean isGood(int[] a, int n, int k, long mid) {
        long sum = 0;

        for (int i = n / 2; i < n; i++) {
            sum += Math.max(0,(mid - a[i]));
        }

        return sum <= k;
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
