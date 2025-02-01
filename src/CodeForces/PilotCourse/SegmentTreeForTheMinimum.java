package CodeForces.PilotCourse;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/edu/course/2/lesson/4/1/practice/contest/273169/problem/B
public class SegmentTreeForTheMinimum {

    static class SegmentTree {

        private int size;
        private long[] min;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) size *= 2;
            min = new long[2 * size];
        }

        public void build(long[] a) {
            build(a, 0, 0, size);
        }

        public void build(long[] a, int x, int lx, int rx) {
            if (rx - lx == 1) {
                if (lx < a.length) min[x] = a[lx];
                else min[x] = Integer.MAX_VALUE;
                return;
            }
            int m = (lx + rx) / 2;
            build(a, (2 * x) + 1, lx, m);
            build(a, (2 * x) + 2, m, rx);
            min[x] = Math.min(min[(2 * x) + 1], min[(2 * x) + 2]);
        }

        public void set(int i, long v) {
            set(i, v, 0, 0, size);
        }

        public void set(int i, long v, int x, int lx, int rx) {
            if (rx - lx == 1) {
                min[x] = v;
                return;
            }
            int m = (lx + rx) / 2;
            if (i < m) {
                set(i, v, (2 * x) + 1, lx, m);
            } else {
                set(i, v, (2 * x) + 2, m, rx);
            }
            min[x] = Math.min(min[(2 * x) + 1], min[(2 * x) + 2]);
        }

        public long minimum(int l, int r) {
            return minimum(l, r, 0, 0, size);
        }

        public long minimum(int l, int r, int x, int lx, int rx) {
            if (lx >= r || l >= rx) return Integer.MAX_VALUE;
            if (lx >= l && rx <= r) return min[x];
            int m = (lx + rx) / 2;
            long m1 = minimum(l, r, (2 * x) + 1, lx, m);
            long m2 = minimum(l, r, (2 * x) + 2, m, rx);
            return Math.min(m1, m2);
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = in.nextLong();

        SegmentTree st = new SegmentTree(n);
        st.build(a);

        while (m-- > 0) {
            int op = in.nextInt();

            if (op == 1) {
                int ind = in.nextInt();
                long val = in.nextLong();
                st.set(ind, val);
            } else {
                int l = in.nextInt();
                int r = in.nextInt();
                long minimum = st.minimum(l, r);
                out.println(minimum);
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
