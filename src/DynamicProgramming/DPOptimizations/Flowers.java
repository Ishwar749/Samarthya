package DynamicProgramming.DPOptimizations;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/dp/tasks/dp_q

public class Flowers {

    static class SegmentTree {
        private int size;
        private long[] beauty;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) size *= 2;
            beauty = new long[size * 2];
        }

        public void set(int ind, long val) {
            set(ind, val, 0, 0, size);
        }

        public void set(int ind, long val, int x, int lx, int rx) {
            if (rx - lx == 1) {
                beauty[x] = val;
                return;
            }
            int m = (lx + rx) / 2;
            if (ind < m) set(ind, val, (2 * x) + 1, lx, m);
            else set(ind, val, (2 * x) + 2, m, rx);
            beauty[x] = Math.max(beauty[(2 * x) + 1], beauty[(2 * x) + 2]);
        }

        public long maximum(int l, int r) {
            return maximum(l, r, 0, 0, size);
        }

        public long maximum(int l, int r, int x, int lx, int rx) {
            if (lx >= l && rx <= r) return beauty[x];
            if (lx >= r || rx <= l) return 0L;

            int m = (lx + rx) / 2;
            long max1 = maximum(l, r, (2 * x) + 1, lx, m);
            long max2 = maximum(l, r, (2 * x) + 2, m, rx);
            return Math.max(max1, max2);
        }


    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int[] h = new int[N];
        int[] a = new int[N];

        for (int i = 0; i < N; i++) h[i] = in.nextInt();
        for (int i = 0; i < N; i++) a[i] = in.nextInt();

        SegmentTree st = new SegmentTree(N);
        long answer = a[0];
        st.set(h[0], a[0]);

        for (int i = 1; i < N; i++) {
            long maxTillNow = st.maximum(0, h[i]);
            long currentBeauty = maxTillNow + a[i];
            answer = Math.max(answer, currentBeauty);
            st.set(h[i], currentBeauty);
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
