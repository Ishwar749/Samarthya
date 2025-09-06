package DataStructures.MonotonicQueue;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/3221

public class SlidingWindowMinimum {
    static class Pair {
        int ind;
        long val;

        public Pair(int ind, long val) {
            this.ind = ind;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();

        long x = in.nextInt();
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();

        Deque<Pair> queue = new LinkedList<>();
        long prevVal = x;
        Pair firstPair = new Pair(0, prevVal);
        queue.addLast(firstPair);

        for (int i = 1; i < k; i++) {
            long curVal = ((a * prevVal) + b) % c;

            while (!queue.isEmpty() && queue.peekLast().val >= curVal) {
                queue.pollLast();
            }
            queue.addLast(new Pair(i, curVal));
            prevVal = curVal;
        }

        long answer = queue.peekFirst().val;

        for (int i = k; i < n; i++) {
            long curVal = ((a * prevVal) + b) % c;

            while (!queue.isEmpty() && queue.peekLast().val >= curVal) {
                queue.pollLast();
            }
            queue.addLast(new Pair(i, curVal));
            prevVal = curVal;

            while (!queue.isEmpty() && queue.peekFirst().ind <= i - k) {
                queue.pollFirst();
            }
            answer = (answer ^ queue.peekFirst().val);
        }

        out.println(answer);
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
