package DivideAndConquer;

import java.io.*;
import java.util.*;

// Problem: https://www.spoj.com/problems/ABACABA/en/

public class PrintThePattern {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        generatePattern(n, out);
        out.close();
    }

    static void generatePattern(int n, PrintWriter out) {
        if (n == 1) {
            out.print('A');
            return;
        }

        generatePattern(n - 1, out);
        out.print((char) ('A' + (n - 1)));
        generatePattern(n - 1, out);
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
