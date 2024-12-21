package CPSheetTLE;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1834/A

public class UnitArray {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            int neg = 0;

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                if (a[i] < 0) neg++;
            }

            int max = n / 2;
            if (max % 2 == 1) max--;

            int ops = 0;
            if (neg >= max) {
                ops = Math.abs(neg - max);
            } else {
                if (neg % 2 == 1) ops++;
            }

            out.println(ops);
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
    }
}
