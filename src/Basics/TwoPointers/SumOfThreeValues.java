package Basics.TwoPointers;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1641

public class SumOfThreeValues {
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
        int x = in.nextInt();
        Pair[] a = new Pair[n];

        for (int i = 0; i < n; i++) {
            a[i] = new Pair(i + 1, in.nextInt());
        }

        Arrays.sort(a, (Pair aa, Pair bb) -> Integer.compare(aa.val, bb.val));

        int[] ans = {-1, -1, -1};

        for (int i = 0; i < n; i++) {
            int[] temp = find(i, a, x - a[i].val);
            if (temp[0] != -1) {
                ans[0] = a[i].ind;
                ans[1] = temp[0];
                ans[2] = temp[1];
                break;
            }
        }

        if (ans[0] == -1) out.println("IMPOSSIBLE");
        else {
            Arrays.sort(ans);
            out.print(ans[0] + " " + ans[1] + " " + ans[2]);
        }

        out.close();
    }

    static int[] find(int disabled, Pair[] a, int target) {

        int n = a.length;
        int l = 0;
        int r = n - 1;
        if (l == disabled) l++;
        if (r == disabled) r--;

        while (l < r) {
            int sum = a[l].val + a[r].val;
            if (sum == target) {
                return new int[]{a[l].ind, a[r].ind};
            } else if (sum < target) {
                l++;
                if (l == disabled) l++;
            } else {
                r--;
                if (r == disabled) r--;
            }
        }

        return new int[]{-1, -1};
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
