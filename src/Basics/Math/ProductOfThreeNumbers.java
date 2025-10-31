package Basics.Math;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1294/C

public class ProductOfThreeNumbers {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            findTriplet(n);
        }
    }

    static void findTriplet(int n) {
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                int m = n / i;
                for (int j = i + 1; j <= (int) Math.sqrt(m); j++) {
                    if (m % j == 0) {
                        int k = m / j;
                        if (i != j && i != k && j != k) {
                            System.out.println("YES");
                            System.out.println(i + " " + j + " " + k);
                            return;
                        }
                    }
                }
            }
        }

        System.out.println("NO");
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
