package DynamicProgramming.ClassicDP.LongestIncreasingSubsequence;

import java.util.*;
import java.io.*;

// Problem: https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=4600

public class BackToEditDistance {

    static Map<Integer, Integer> indexInB;

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        for (int test = 1; test <= tests; test++) {

            indexInB = new HashMap<>();
            int N = in.nextInt();

            int[] a = new int[N];
            int[] b = new int[N];

            for (int i = 0; i < N; i++) a[i] = in.nextInt();
            for (int i = 0; i < N; i++) {
                b[i] = in.nextInt();
                indexInB.put(b[i], i + 1);
            }

            for (int i = 0; i < N; i++) {
                a[i] = indexInB.get(a[i]);
            }

            int LIS = findLIS(a);
            int answer = (N - LIS) * 2;

            out.println("Case " + test + ": " + answer);
        }
        out.close();
    }

    static int findLIS(int[] a) {

        int length = a.length;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        int LIS = 1;
        treeMap.put(a[0], 1);

        for (int i = 1; i < length; i++) {

            int currentLIS = 1;
            Integer firstSmaller = treeMap.floorKey(a[i]);

            if (firstSmaller != null) currentLIS += treeMap.get(firstSmaller);

            Integer nextKey = treeMap.ceilingKey(a[i]);
            while (nextKey != null && treeMap.get(nextKey) <= currentLIS) {
                treeMap.remove(nextKey);
                nextKey = treeMap.ceilingKey(a[i]);
            }

            treeMap.put(a[i], currentLIS);
            LIS = Math.max(LIS, currentLIS);
        }

        return LIS;
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
