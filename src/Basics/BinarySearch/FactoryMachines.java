package Basics.BinarySearch;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1620

public class FactoryMachines {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int t = in.nextInt();
        int[] a = new int[n];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            min = Math.min(a[i], min);
        }

        long R = (long) min * (long) t;
        long L = 0;

        while (L < R) {
            long mid = L + (R - L) / 2;
            long k = 0;

            for (int e : a) {
                k += mid / e;
            }

            if (k >= t) R = mid;
            else L = mid + 1;
        }

        out.println(L);
        out.close();
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
