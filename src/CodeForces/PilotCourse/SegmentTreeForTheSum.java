package CodeForces.PilotCourse;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/edu/course/2/lesson/4/1/practice/contest/273169/problem/A

public class SegmentTreeForTheSum {

    static class SegmentTree {

        private int size;
        private long[] sums;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) size *= 2;
            sums = new long[2 * size];
        }

        public void build(long[] a) {
            build(a, 0, 0, size);
        }

        public void build(long[] a, int x, int lx, int rx) {
            if (rx - lx == 1) {
                if (lx < a.length) sums[x] = a[lx];
                return;
            }
            int m = (lx + rx) / 2;
            build(a, (2 * x) + 1, lx, m);
            build(a, (2 * x) + 2, m, rx);
            sums[x] = sums[(2 * x) + 1] + sums[(2 * x) + 2];
        }

        public void set(int i, long v) {
            set(i, v, 0, 0, size);
        }

        public void set(int i, long v, int x, int lx, int rx) {
            if (rx - lx == 1) {
                sums[x] = v;
                return;
            }
            int m = (lx + rx) / 2;
            if (i < m) {
                set(i, v, (2 * x) + 1, lx, m);
            } else {
                set(i, v, (2 * x) + 2, m, rx);
            }
            sums[x] = sums[(2 * x) + 1] + sums[(2 * x) + 2];
        }

        public long sum(int l, int r) {
            return sum(l, r, 0, 0, size);
        }

        public long sum(int l, int r, int x, int lx, int rx) {
            if (lx >= r || l >= rx) return 0L;
            if (lx >= l && rx <= r) return sums[x];
            int m = (lx + rx) / 2;
            long s1 = sum(l, r, (2 * x) + 1, lx, m);
            long s2 = sum(l, r, (2 * x) + 2, m, rx);
            return s1 + s2;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        long a[] = new long[n];
        for (int i = 0; i < n; i++) a[i] = in.nextLong();

        SegmentTree st = new SegmentTree(n);
        st.build(a);

        while (m-- > 0) {
            int op = in.nextInt();
            if (op == 1) {
                int ind = in.nextInt();
                int val = in.nextInt();
                st.set(ind, val);
            } else {
                int l = in.nextInt();
                int r = in.nextInt();
                long sum = st.sum(l, r);
                out.println(sum);
            }
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
