package Basics.BinarySearch;

import java.io.*;
import java.util.*;

//Problem: https://cses.fi/problemset/task/2422

public class MultiplicationTable {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        long n = in.nextLong();
        long L = 1;
        long R = n * n;
        long middleIndex = ((n * n) + 1) / 2;

        while (L < R) {
            long mid = L + (R - L) / 2;
            long lessOrEqualToMid = 0;

            for (int i = 1; i <= n; i++) {
                lessOrEqualToMid += Math.min(n, (mid / i));
            }

            if (lessOrEqualToMid >= middleIndex) R = mid;
            else L = mid + 1;
        }

        out.println(R);
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
