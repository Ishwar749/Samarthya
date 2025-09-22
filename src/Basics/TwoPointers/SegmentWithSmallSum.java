//package Basics.TwoPointers;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/edu/course/2/lesson/9/2/practice/contest/307093/problem/A

public class SegmentWithSmallSum {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        long s = in.nextLong();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) a[i] = in.nextInt();

        int l = 0;
        int r = 0;
        long sum = 0;
        int maxSize = 0;

        while (r < n) {
            if (sum + a[r] <= s) {
                sum += a[r];
                r++;
            } else {
                if (r == l) {
                    l++;
                    r++;
                } else {
                    maxSize = Math.max(maxSize, r - l);
                    sum -= a[l];
                    l++;
                }
            }
        }

        if (sum <= s) maxSize = Math.max(maxSize, r - l);

        out.println(maxSize);
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
