package dynamicProgramming.classicDP;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1145
// Optimized Approach: https://cp-algorithms.com/sequences/longest_increasing_subsequence.html

public class IncreasingSubsequence {
    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int nums[] = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        int answer = optimizedLIS(nums);
        out.println(answer);
        out.close();
    }

    static int optimizedLIS(int[] nums) {

        int len = nums.length;
        int dp[] = new int[len + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            int index = findIndex(dp, nums[i]);
            if (dp[index - 1] < nums[i] && nums[i] < dp[index]) {
                dp[index] = nums[i];
            }
        }

        int lis = 0;
        for (int l = 0; l < dp.length; l++) {
            if (dp[l] != Integer.MAX_VALUE) lis = l;
        }

        return lis;
    }

    static int findIndex(int[] dp, int val) {

        int low = 0;
        int high = dp.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (dp[mid] <= val) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
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

