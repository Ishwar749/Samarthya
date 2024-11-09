package dynamicProgramming.classicDP.longestIncreasingSubsequence;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/dp/tasks/dp_q
// Solution: https://youtu.be/FAQxdm0bTaw?t=9613
// Segment Tree Implementation: https://cp-algorithms.com/data_structures/segment_tree.html#implementation

public class FlowersAtCoder {
    static int N;
    static long[] segmentTree;

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        N = in.nextInt();
        segmentTree = new long[(4 * N) + 1];
        int[] height = new int[N];
        int[] beauty = new int[N];

        for (int i = 0; i < N; i++) height[i] = in.nextInt();
        for (int i = 0; i < N; i++) beauty[i] = in.nextInt();

        long[] dp = new long[N + 1];
        update(1, 0, N - 1, height[0], beauty[0]);
        dp[height[0]] = beauty[0];

        for (int i = 1; i < N; i++) {
            long maxBeautyOnTheLeft = getMaxInRange(1, 0, N - 1, 0, height[i] - 1);
            long currentBeauty = maxBeautyOnTheLeft + beauty[i];
            update(1, 0, N - 1, height[i], currentBeauty);
            dp[height[i]] = currentBeauty;
        }

        long maxBeauty = 0;
        for (int i = 1; i < dp.length; i++) {
            maxBeauty = Math.max(maxBeauty, dp[i]);
        }

        out.println(maxBeauty);
        out.close();
    }

    static void build(int[] arr, int vertex, int tl, int tr) {

        if (tl == tr) {
            segmentTree[vertex] = arr[tl];
        } else {
            int tm = tl + (tr - tl) / 2;
            build(arr, vertex * 2, tl, tm);
            build(arr, (vertex * 2) + 1, tm + 1, tr);
            segmentTree[vertex] = Math.max(segmentTree[vertex * 2], segmentTree[(vertex * 2) + 1]);
        }
    }

    static long getMaxInRange(int vertex, int tl, int tr, int l, int r) {
        if (l > r)
            return 0;
        if (l == tl && r == tr) {
            return segmentTree[vertex];
        }
        int tm = tl + (tr - tl) / 2;
        return Math.max(
                getMaxInRange(vertex * 2, tl, tm, l, Math.min(r, tm)),
                getMaxInRange((vertex * 2) + 1, tm + 1, tr, Math.max(l, tm + 1), r)
        );
    }

    static void update(int vertex, int tl, int tr, int pos, long newVal) {
        if (tl == tr) {
            segmentTree[vertex] = newVal;
        } else {
            int tm = tl + (tr - tl) / 2;
            if (pos <= tm)
                update(vertex * 2, tl, tm, pos, newVal);
            else
                update((vertex * 2) + 1, tm + 1, tr, pos, newVal);

            segmentTree[vertex] = Math.max(segmentTree[vertex * 2], segmentTree[(vertex * 2) + 1]);
        }
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
