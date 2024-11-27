package DynamicProgramming.ClassicDP.LongestIncreasingSubsequence;

import java.util.*;

// Problem: https://leetcode.com/problems/longest-increasing-subsequence/description/
// Optimized Approach: https://cp-algorithms.com/sequences/longest_increasing_subsequence.html

public class LongestIncreasingSubsequence {

    public static void main(String args[]) {

    }

    static class Solution {
        public static int lengthOfLIS(int[] nums) {
            return standardLIS(nums);
        }

        static int standardLIS(int[] nums) {

            int len = nums.length;
            int dp[] = new int[len];
            Arrays.fill(dp, 1);

            for (int i = 1; i < len; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            int lis = dp[0];
            for (int e : dp) lis = Math.max(lis, e);

            return lis;
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
    }
}
