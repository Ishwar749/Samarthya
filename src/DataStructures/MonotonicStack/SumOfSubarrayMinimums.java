package DataStructures.MonotonicStack;

import java.io.*;
import java.util.*;

// Problem: https://leetcode.com/problems/sum-of-subarray-minimums/description/

public class SumOfSubarrayMinimums {
    static class Pair {
        int ind;
        int val;

        public Pair(int ind, int val) {
            this.ind = ind;
            this.val = val;
        }
    }

    static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long mod = (long) (1e9 + 7);

        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] * -1;
        }

        int[] L = nearestGreaterToLeft(arr, n);
        int[] R = nearestGreaterOrEqualToRight(arr, n);
        long answer = 0;

        for (int i = 0; i < n; i++) {
            int fromTheLeft = i - L[i];
            int fromTheRight = R[i] - i;
            long contribution = modMul(arr[i], modMul(fromTheLeft, fromTheRight, mod), mod);
            answer = modAdd(answer, contribution, mod);
        }

        return (int) answer * -1;
    }

    static int[] nearestGreaterToLeft(int[] arr, int n) {
        Stack<Pair> stack = new Stack<>();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek().val <= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) result[i] = -1;
            else result[i] = stack.peek().ind;

            stack.push(new Pair(i, arr[i]));
        }

        return result;
    }

    static int[] nearestGreaterOrEqualToRight(int[] arr, int n) {
        Stack<Pair> stack = new Stack<>();
        int[] result = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().val < arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) result[i] = n;
            else result[i] = stack.peek().ind;

            stack.push(new Pair(i, arr[i]));
        }

        return result;
    }

    static long modMul(long a, long b, long mod) {
        return ((a % mod) * (b % mod)) % mod;
    }

    static long modAdd(long a, long b, long mod) {
        return ((a % mod) + (b % mod)) % mod;
    }

}
