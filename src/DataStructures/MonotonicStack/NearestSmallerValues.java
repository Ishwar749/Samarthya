package DataStructures.MonotonicStack;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1645

public class NearestSmallerValues {
    static class Pair {
        int val;
        int ind;

        public Pair(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int[] res = getNearestSmallerValues(a, n);
        for (int val : res) out.print(val + " ");
        out.close();
    }

    static int[] getNearestSmallerValues(int[] a, int n) {

        int[] res = new int[n];
        Stack<Pair> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek().val >= a[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                res[i] = 0;
            } else {
                res[i] = stack.peek().ind;
            }

            stack.add(new Pair(a[i], i + 1));
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
