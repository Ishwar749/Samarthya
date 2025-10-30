package Basics.Math;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1713
public class CountingDivisors {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int[] divisors = new int[1000001];

        for(int i = 1; i < 1000001; i++) {
            for(int j = i; j < 1000001; j+=i) {
                divisors[j]++;
            }
        }

        int n = in.nextInt();
        for(int i = 0; i < n; i++) {
            int x = in.nextInt();
            out.println(divisors[x]);
        }

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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
