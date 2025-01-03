package CPSheetTLE._900;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1878/C

public class VasilijeInCacak {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            long n = in.nextInt();
            long k = in.nextInt();
            long x = in.nextLong();

            long minSum = (k * (k + 1)) / 2;
            long totalSum = (n * (n + 1)) / 2;
            long remaining = n - k;
            long sum = (remaining * (remaining + 1)) / 2;
            long maxSum = totalSum - sum;

            if (x >= minSum && x <= maxSum) out.println("YES");
            else out.println("NO");
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
