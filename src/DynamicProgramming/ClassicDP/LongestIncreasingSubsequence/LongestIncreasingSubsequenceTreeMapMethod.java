package DynamicProgramming.ClassicDP.LongestIncreasingSubsequence;

import java.util.*;

// The approach below works in O(NlogN), here's how:

/*
getMaxBeauty(tm, height[i]): This uses floorKey, which is an
𝑂(log𝑁) operation since TreeMap operations are logarithmic in time complexity.

insert(tm, height[i], maxBeauty): This method has a while-loop that could potentially remove several entries,
but each entry in the TreeMap can only be inserted or removed once.
This means that, in total across the loop, the number of removals across all calls is bounded by 𝑂(𝑁)
*/

public class LongestIncreasingSubsequenceTreeMapMethod {
    public static void main(String args[]) {

        int[] nums = {0, 1, 0, 3, 2, 3};
        int LIS = findLIS(nums);

        System.out.println(LIS);
    }

    static int findLIS(int[] nums) {

        int n = nums.length;

        TreeMap<Integer, Integer> tm = new TreeMap<>();

        tm.put(nums[0], 1);
        int maxLen = 1;

        for (int i = 1; i < n; i++) {

            int len = getMaxLen(tm, nums[i]);

            maxLen = Math.max(maxLen, len + 1);

            insert(tm, nums[i], len + 1);

        }
        return maxLen;
    }

    static void insert(TreeMap<Integer, Integer> tm, int num, int len) {

        Integer key = tm.ceilingKey(num);

        while (key != null && tm.get(key) <= len) {
            tm.remove(key);
            key = tm.ceilingKey(num);
        }
        tm.put(num, len);
    }

    static int getMaxLen(TreeMap<Integer, Integer> tm, int num) {

        Integer key = tm.floorKey(num - 1);

        if (key == null) {
            return 0;
        }

        return tm.get(key);
    }
}
