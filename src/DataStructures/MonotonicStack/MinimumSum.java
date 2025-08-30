package DataStructures.MonotonicStack;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/agc005/tasks/agc005_b

public class MinimumSum {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) a[i] = in.nextInt() * -1;

        long answer = sumOfAllSubarrayMaximums(a) * -1L;
        out.println(answer);
        out.close();
    }

    static long sumOfAllSubarrayMaximums(int[] a) {
        int n = a.length;
        Map<Integer, Integer> valToIndex = new HashMap<>();

        for (int i = 0; i < n; i++) valToIndex.put(a[i], i);

        int[] L = nearestGreaterToLeft(n, a, valToIndex);
        int[] R = nearestGreaterOrEqualToRight(n, a, valToIndex);

        long answer = 0;
        for (int i = 0; i < n; i++) {
            long fromTheLeft = (i - L[i]);
            long fromTheRight = (R[i] - i);
            long toAdd = fromTheLeft * a[i] * fromTheRight;
            answer += toAdd;
        }

        return answer;
    }

    static int[] nearestGreaterToLeft(int n, int[] a, Map<Integer, Integer> valToIndex) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() <= a[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) result[i] = -1;
            else result[i] = valToIndex.get(stack.peek());

            stack.push(a[i]);
        }

        return result;
    }

    static int[] nearestGreaterOrEqualToRight(int n, int[] a, Map<Integer, Integer> valToIndex) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < a[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) result[i] = n;
            else result[i] = valToIndex.get(stack.peek());

            stack.push(a[i]);
        }

        return result;
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
