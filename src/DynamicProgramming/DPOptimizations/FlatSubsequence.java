package DynamicProgramming.DPOptimizations;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/abl/tasks/abl_d
public class FlatSubsequence {

    static class SegmentTree {
        private int size;
        private int[] values;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) size *= 2;
            values = new int[size * 2];
        }

        public void set(int ind, int val) {
            set(ind, val, 0, 0, size);
        }

        public void set(int ind, int val, int x, int lx, int rx) {
            if (rx - lx == 1) {
                values[x] = val;
                return;
            }
            int m = (lx + rx) / 2;
            if (ind < m) set(ind, val, (2 * x) + 1, lx, m);
            else set(ind, val, (2 * x) + 2, m, rx);
            values[x] = Math.max(values[(2 * x) + 1], values[(2 * x) + 2]);
        }

        public int maximum(int l, int r) {
            return maximum(l, r, 0, 0, size);
        }

        public int maximum(int l, int r, int x, int lx, int rx) {
            if (lx >= r || l >= rx) return 0;
            if (lx >= l && rx <= r) return values[x];

            int m = (lx + rx) / 2;
            int max1 = maximum(l, r, (2 * x) + 1, lx, m);
            int max2 = maximum(l, r, (2 * x) + 2, m, rx);
            return Math.max(max1, max2);
        }

    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        int max = 0;
        int min = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            max = Math.max(a[i], max);
            min = Math.min(a[i], min);
        }

        SegmentTree segTree = new SegmentTree(max + 1);
        segTree.set(a[0], 1);
        long answer = 1;

        for (int i = 1; i < n; i++) {
            int maxTillNow = segTree.maximum(Math.max(0, a[i] - k), Math.min(a[i] + k, max) + 1);
            answer = Math.max(answer, maxTillNow + 1);
            segTree.set(a[i], maxTillNow + 1);
        }

        out.println(answer);
        out.close();
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
