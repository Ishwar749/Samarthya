package DynamicProgramming.DPOptimizations;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/597/problem/C

public class Subsequences {
    static class SegmentTree {
        private int size;
        private long[] sums;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) size *= 2;
            sums = new long[size * 2];
        }

        public void build(int[] a) {
            build(a, 0, 0, size);
        }

        public void build(int[] a, int x, int lx, int rx) {
            if (rx - lx == 1) {
                if (lx < a.length) sums[x] = a[lx];
                return;
            }

            int m = (lx + rx) / 2;
            build(a, (2 * x) + 1, lx, m);
            build(a, (2 * x) + 2, m, rx);
            sums[x] = sums[(2 * x) + 1] + sums[(2 * x) + 2];
        }

        public void set(int ind, long val) {
            set(ind, val, 0, 0, size);
        }

        public void set(int ind, long val, int x, int lx, int rx) {
            if (rx - lx == 1) {
                sums[x] = val;
                return;
            }

            int m = (lx + rx) / 2;
            if (ind < m) set(ind, val, (2 * x) + 1, lx, m);
            else set(ind, val, (2 * x) + 2, m, rx);
            sums[x] = sums[(2 * x) + 1] + sums[(2 * x) + 2];
        }

        public long getSum(int l, int r) {
            return getSum(l, r, 0, 0, size);
        }

        public long getSum(int l, int r, int x, int lx, int rx) {
            if (lx >= r || l >= rx) return 0L;
            if (lx >= l && rx <= r) return sums[x];
            int m = (lx + rx) / 2;
            long sum1 = getSum(l, r, (2 * x) + 1, lx, m);
            long sum2 = getSum(l, r, (2 * x) + 2, m, rx);
            return sum1 + sum2;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt() + 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();

        if (k == 1) {
            out.println(n);
            out.close();
            return;
        }

        SegmentTree[] st = new SegmentTree[k + 1];
        for (int i = 0; i <= k; i++) st[i] = new SegmentTree(n + 1);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            for (int len = 1; len <= k; len++) {

                if (len == 1) {
                    st[len].set(a[i], 1L);
                } else {
                    long prev = st[len - 1].getSum(0, a[i]);
                    st[len].set(a[i], prev);
                    if (len == k) answer += prev;
                }
            }
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
