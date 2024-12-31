package CPSheetTLE;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1777/A

public class EverybodyLikesGoodArrays {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            int ind = 0;

            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            for (int i = n - 1; i >= 0; i--) {
                b[ind] = a[i];
                ind++;
            }

            int answer = Math.min(findOps(a), findOps(b));
            out.println(answer);
        }
        out.close();
    }

    static int findOps(int[] a) {

        boolean even = a[0] % 2 == 0 ? false : true;
        int ops = 0;

        for (int i = 1; i < a.length; i++) {
            if (even && a[i] % 2 == 1) ops++;
            else if (!even && a[i] % 2 == 0) ops++;
            else even = !even;
        }

        return ops;
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
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
