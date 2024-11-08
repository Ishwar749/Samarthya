package dynamicProgramming.classicDP;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/dp/tasks/dp_q

// For this same problem, we have coded another approach that uses Segment Trees.
// Open  FlowersAtCoder.java  to have a look at that approach.

public class Flowers {
    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int[] height = new int[N];
        int[] beauty = new int[N];

        for (int i = 0; i < N; i++) height[i] = in.nextInt();
        for (int i = 0; i < N; i++) beauty[i] = in.nextInt();

        long answer = maxLengthOfLIS(height, beauty);
        out.println(answer);
        out.close();
    }

    // The solution below works! Its an O(NlogN) solution.
    static long maxLengthOfLIS(int[] height, int[] beauty) {

        TreeMap<Integer, Long> tm = new TreeMap<>();
        tm.put(height[0], (long) beauty[0]);
        long answer = beauty[0];

        for (int i = 1; i < height.length; i++) {
            long maxBeauty = getMaxBeauty(tm, height[i]) + beauty[i];
            answer = Math.max(answer, maxBeauty);
            insert(tm, height[i], maxBeauty);
        }

        return answer;
    }

    static long getMaxBeauty(TreeMap<Integer, Long> tm, int height) {
        Integer key = tm.floorKey(height - 1);

        if (key != null) return tm.get(key);
        else return 0;
    }

    static void insert(TreeMap<Integer, Long> tm, int height, long beauty) {

        Integer nextKey = tm.ceilingKey(height);

        while (nextKey != null && tm.get(nextKey) <= beauty) {
            tm.remove(nextKey);
            nextKey = tm.ceilingKey(height);
        }

        tm.put(height, beauty);
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
