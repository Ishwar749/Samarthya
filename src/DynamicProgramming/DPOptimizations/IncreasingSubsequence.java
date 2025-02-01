package DynamicProgramming.DPOptimizations;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1145

public class IncreasingSubsequence {

    static class SegmentTree {
        private int size;
        private int[] values;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) size *= 2;
            values = new int[size * 2];
        }

        public void build(int[] a) {
            build(a, 0, 0, size);
        }

        public void build(int[] a, int x, int lx, int rx) {
            if (rx - lx == 1) {
                if (lx < a.length) values[x] = a[lx];
                return;
            }

            int m = (lx + rx) / 2;
            build(a, (2 * x) + 1, lx, m);
            build(a, (2 * x) + 2, m, rx);
            values[x] = Math.max(values[(2 * x) + 1], values[2 * x] + 2);
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
            if (ind < m) {
                set(ind, val, (2 * x) + 1, lx, m);
            } else {
                set(ind, val, (2 * x) + 2, m, rx);
            }
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
            values[x] = Math.max(max1, max2);
            return values[x];
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int[] b = new int[n];
        for (int i = 0; i < n; i++) b[i] = in.nextInt();
        int[] a = getNormalizedArray(b);

        SegmentTree segTree = new SegmentTree(n);
        // We don't have to build the segmentTree on any array. The segment tree is built on DP array values.

        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int previousMax = segTree.maximum(0, a[i]);
            dp[a[i]] = previousMax + 1;
            segTree.set(a[i], dp[a[i]]);
        }

        int answer = segTree.maximum(0, n + 1);
        out.println(answer);
        out.close();
    }

    static int[] getNormalizedArray(int[] a) {
        Map<Integer, Integer> getNormalValue = new HashMap<>();
        int value = 1;
        int len = a.length;
        int[] b = new int[len];
        for (int i = 0; i < len; i++) b[i] = a[i];
        Arrays.sort(b);

        for (int i = 0; i < len; i++) {
            if (!getNormalValue.containsKey(b[i])) {
                getNormalValue.put(b[i], value);
                value++;
            }
        }

        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = getNormalValue.get(a[i]);
        }
        return res;
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
