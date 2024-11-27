package DynamicProgramming.ClassicDP.LongestIncreasingSubsequence;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/arc149/tasks/arc149_b
// Solution: Sort A and take all its elements in the answer. Calculate LIS for B and add it to answer.
// Editorial: https://atcoder.jp/contests/arc149/editorial/4954

public class TwoLISSum {
    static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        Pair[] AB = new Pair[N];

        for (int i = 0; i < N; i++) AB[i] = new Pair(in.nextInt(), 0);
        for (int i = 0; i < N; i++) AB[i].b = in.nextInt();

        Arrays.sort(AB, new Comparator<>() {
            public int compare(Pair a, Pair b) {
                return Integer.compare(a.a, b.a);
            }
        });

        int[] B = new int[N];

        for (int i = 0; i < N; i++) B[i] = AB[i].b;

        int LISB = findLIS(B);

        int answer = N + LISB;
        out.println(answer);
        out.close();
    }

    static int findLIS(int[] nums) {

        int n = nums.length;

        TreeMap<Integer, Integer> tm = new TreeMap<>();

        tm.put(nums[0], 1);
        int maxLen = 1;

        for (int i = 1; i < n; i++) {

            int len = getMaxLen(tm, nums[i]);

            maxLen = Math.max(maxLen, len + 1);

            insert(tm, nums[i], len + 1);

        }
        return maxLen;
    }

    static void insert(TreeMap<Integer, Integer> tm, int num, int len) {

        Integer key = tm.ceilingKey(num);

        while (key != null && tm.get(key) <= len) {
            tm.remove(key);
            key = tm.ceilingKey(num);
        }
        tm.put(num, len);
    }

    static int getMaxLen(TreeMap<Integer, Integer> tm, int num) {

        Integer key = tm.floorKey(num - 1);

        if (key == null) {
            return 0;
        }

        return tm.get(key);
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
