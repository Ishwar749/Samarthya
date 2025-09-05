package DataStructures.MonotonicStack;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/547/B

public class MikeAndFeet {
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
        int[] a = new int[n];

        for (int i = 0; i < n; i++) a[i] = in.nextInt();

        int[] answer = solve(a, n);
        for (int e : answer) out.print(e + " ");
        out.close();
    }

    static int[] solve(int[] a, int n) {
        int[] L = nearestSmallerToLeft(a, n);
        int[] R = nearestSmallerOrEqualToRight(a, n);

        int[] res = new int[n];
        Arrays.fill(res, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            int range = (R[i] - L[i]) - 1;
            res[range - 1] = Math.max(res[range - 1], a[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            res[i] = Math.max(res[i], res[i + 1]);
        }

        return res;
    }

    static int[] nearestSmallerToLeft(int[] a, int n) {
        int[] res = new int[n];
        Stack<Pair> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek().val >= a[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) res[i] = -1;
            else res[i] = stack.peek().ind;

            stack.push(new Pair(i, a[i]));
        }

        return res;
    }

    static int[] nearestSmallerOrEqualToRight(int[] a, int n) {
        int[] res = new int[n];
        Stack<Pair> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().val > a[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) res[i] = n;
            else res[i] = stack.peek().ind;

            stack.push(new Pair(i, a[i]));
        }

        return res;
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
