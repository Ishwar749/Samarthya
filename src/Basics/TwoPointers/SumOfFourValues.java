package Basics.TwoPointers;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1642
public class SumOfFourValues {
    static class Pair {
        int ind;
        int val;

        public Pair(int ind, int val) {
            this.ind = ind;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int target = in.nextInt();

        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = in.nextInt();

        Map<Integer, int[]> twoSum = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int differenceToX = target - a[i] - a[j];
                if (twoSum.containsKey(differenceToX)) {
                    int c = twoSum.get(differenceToX)[0];
                    int d = twoSum.get(differenceToX)[1];
                    out.println(i + " " + j + " " + c + " " + d);
                    out.close();
                    return;
                }
            }

            for (int j = 1; j <= i - 1; j++) {
                twoSum.put(a[i] + a[j], new int[]{j, i});
            }
        }

        out.println("IMPOSSIBLE");
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
