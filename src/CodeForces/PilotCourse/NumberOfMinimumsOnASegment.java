package CodeForces.PilotCourse;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/edu/course/2/lesson/4/1/practice/contest/273169/problem/C

public class NumberOfMinimumsOnASegment {

    static class Pair {
        int value;
        int count;

        public Pair(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    static class SegmentTree {

        private int size;
        private Pair[] values;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) size *= 2;
            values = new Pair[size * 2];
        }

        public void build(int[] a) {
            build(a, 0, 0, size);
        }

        public void build(int[] a, int x, int lx, int rx) {
            if (rx - lx == 1) {
                if (lx < a.length) values[x] = new Pair(a[lx], 1);
                else values[x] = new Pair(Integer.MAX_VALUE, 0);
                return;
            }

            int m = (lx + rx) / 2;
            build(a, (2 * x) + 1, lx, m);
            build(a, (2 * x) + 2, m, rx);
            values[x] = merge(values[(2 * x) + 1], values[(2 * x) + 2]);
        }

        public void set(int ind, int val) {
            set(ind, val, 0, 0, size);
        }

        public void set(int ind, int val, int x, int lx, int rx) {
            if (rx - lx == 1) {
                values[x] = new Pair(val, 1);
                return;
            }

            int m = (lx + rx) / 2;
            if (ind < m) {
                set(ind, val, (2 * x) + 1, lx, m);
            } else {
                set(ind, val, (2 * x) + 2, m, rx);
            }
            values[x] = merge(values[(2 * x) + 1], values[(2 * x) + 2]);
        }

        public Pair minimum(int l, int r) {
            return minimum(l, r, 0, 0, size);
        }

        public Pair minimum(int l, int r, int x, int lx, int rx) {
            if (lx >= r || l >= rx) return new Pair(Integer.MAX_VALUE, 0);
            if (lx >= l && rx <= r) return values[x];

            int m = (lx + rx) / 2;
            Pair left = minimum(l, r, (2 * x) + 1, lx, m);
            Pair right = minimum(l, r, (2 * x) + 2, m, rx);
            return merge(left, right);
        }

        public Pair merge(Pair left, Pair right) {
            if (left.value < right.value) return left;
            if (right.value < left.value) return right;

            return new Pair(left.value, left.count + right.count);
        }

    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();

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
                Pair answer = st.minimum(l, r);
                out.println(answer.value + " " + answer.count);
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
