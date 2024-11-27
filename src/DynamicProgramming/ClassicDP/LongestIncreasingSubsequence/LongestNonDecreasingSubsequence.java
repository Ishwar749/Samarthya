package DynamicProgramming.ClassicDP.LongestIncreasingSubsequence;

import java.util.*;


// NOTE: This is NON-DECREASING Subsequence.
// Hence, repeated elements can be considered.

public class LongestNonDecreasingSubsequence {
    public static void main(String args[]) {

        int[] nums = {2, 1, 3, 1, 6, 2};
        int lis = optimizedLIS(nums);

        System.out.println(lis);
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
}
