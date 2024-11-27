package DynamicProgramming.ClassicDP.LongestIncreasingSubsequence;

import java.io.*;
import java.util.*;

// Problem: https://www.spoj.com/problems/DOSA/
// To understand what we have done here, read these blogs:
// https://codeforces.com/blog/entry/56332, https://codeforces.com/blog/entry/10652

public class Dosa {

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int[] nums = new int[N];
        int elementsToConsider = 0;

        for (int i = 0; i < N; i++) {
            nums[i] = in.nextInt();
            if (nums[i] - (i + 1) >= 0) elementsToConsider++;
        }

        int[] arr = new int[elementsToConsider];
        int ind = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] - (i + 1) >= 0) {
                arr[ind] = nums[i] - (i + 1);
                ind++;
            }
        }

        int nonDecreasingLIS = 1;
        if (elementsToConsider > 1) nonDecreasingLIS = optimizedLIS(arr);

        int answer = N - nonDecreasingLIS;
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
            if (dp[index - 1] <= nums[i] && nums[i] < dp[index]) {
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
