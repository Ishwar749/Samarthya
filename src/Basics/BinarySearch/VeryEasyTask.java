package Basics.BinarySearch;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/edu/course/2/lesson/6/2/practice/contest/283932/problem/C

public class VeryEasyTask {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        int x = in.nextInt();
        int y = in.nextInt();
        int yy = y;
        y = Math.max(x, y);
        x = Math.min(x, yy);

        long answer = x;
        n--;

        int l = 0;
        int r = (int) (1e9 + 10);

        while (l < r) {
            int mid = (l + r) / 2;
            if (isGood(mid, x, y, n)) r = mid;
            else l = mid + 1;
        }

        answer += l;
        out.println(answer);
        out.close();
    }

    static boolean isGood(int mid, int x, int y, int n) {
        int copies = (mid / x) + (mid / y);
        return copies >= n;
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
