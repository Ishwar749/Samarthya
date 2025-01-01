package CPSheetTLE._900;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1904/A

public class Forked {

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {

            int a = in.nextInt();
            int b = in.nextInt();
            int ki = in.nextInt();
            int kj = in.nextInt();
            int qi = in.nextInt();
            int qj = in.nextInt();

            int[][] dir = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

            List<int[]> attackKing = new ArrayList<>();
            for (int[] e : dir) {
                int[] first = {(e[0] * a) + ki, (e[1] * b) + kj};
                int[] second = {(e[0] * b) + ki, (e[1] * a) + kj};
                if (!contains(first, attackKing)) attackKing.add(first);
                if (!contains(second, attackKing)) attackKing.add(second);
            }

            int count = 0;

            for (int[] e : dir) {
                int[] first = {(e[0] * a) + qi, (e[1] * b) + qj};
                int[] second = {(e[0] * b) + qi, (e[1] * a) + qj};
                if (contains(first, attackKing)) count++;
                if (!equals(first, second) && contains(second, attackKing)) count++;
            }

            out.println(count);
        }
        out.close();
    }

    static boolean equals(int[] x, int[] y) {
        if (x[0] == y[0] && x[1] == y[1]) return true;
        return false;
    }

    static boolean contains(int[] x, List<int[]> al) {
        for (int[] e : al) {
            if (x[0] == e[0] && x[1] == e[1]) return true;
        }
        return false;
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
