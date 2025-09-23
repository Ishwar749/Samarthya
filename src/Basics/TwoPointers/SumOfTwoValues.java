package Basics.TwoPointers;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1640

public class SumOfTwoValues {
    static class Pair {
        int val;
        int ind;

        public Pair(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int x = in.nextInt();
        Pair[] p = new Pair[n];

        for (int i = 0; i < n; i++) p[i] = new Pair(in.nextInt(), i);
        Arrays.sort(p, (Pair a, Pair b) -> Integer.compare(a.val, b.val));

        int l = 0;
        int r = n - 1;

        int left = -1;
        int right = -1;

        while (l < r) {
            int diff = x - p[l].val;

            while (l < r && p[r].val > diff) {
                r--;
            }

            if (l < r && p[r].val == diff) {
                left = Math.min(p[l].ind + 1, p[r].ind + 1);
                right = Math.max(p[l].ind + 1, p[r].ind + 1);
                break;
            } else if (l < r) {
                l++;
            }
        }

        if (left == -1) out.println("IMPOSSIBLE");
        else out.println(left + " " + right);
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
