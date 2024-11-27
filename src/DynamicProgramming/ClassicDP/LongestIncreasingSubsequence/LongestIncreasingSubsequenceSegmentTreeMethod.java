package DynamicProgramming.ClassicDP.LongestIncreasingSubsequence;

// Problem: https://leetcode.com/problems/longest-increasing-subsequence/description/
// Approach: https://cp-algorithms.com/sequences/longest_increasing_subsequence.html#solution-in-on-log-n-with-data-structures
public class LongestIncreasingSubsequenceSegmentTreeMethod {

    static int[] segmentTree;
    static int maxAi = 100000;

    public static void main(String args[]) {

        segmentTree = new int[(4 * maxAi) + 1];
        int[] nums = {2, 1, 5, 4, 3};
        int LIS = lengthOfLIS(nums);

        System.out.println(LIS);
    }

    static int lengthOfLIS(int[] nums) {

        int len = nums.length;
        int[] dp = new int[len];
        // dp[i] = length of longest increasing subsequence ending at nums[i]
        int[] t = new int[maxAi + 1];
        // t[nums[i]] will store the length of longest increasing subsequence ending at nums[i]
        int answer = 1;

        dp[0] = 1;
        t[nums[0]] = 1;
        build(t, 1, 0, t.length - 1);

        for (int i = 1; i < len; i++) {
            int previousLIS = getMaxInRange(1, 0, t.length - 1, 0, nums[i] - 1);
            dp[i] = previousLIS + 1;
            t[nums[i]] = dp[i];
            update(1, 0, t.length - 1, nums[i], dp[i]);
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    static void build(int[] arr, int vertex, int tl, int tr) {

        if (tl == tr) {
            segmentTree[vertex] = arr[tl];
        } else {
            int tm = tl + (tr - tl) / 2;
            build(arr, vertex * 2, tl, tm);
            build(arr, (vertex * 2) + 1, tm + 1, tr);
            segmentTree[vertex] = Math.max(segmentTree[vertex * 2], segmentTree[(vertex * 2) + 1]);
        }
    }

    static int getMaxInRange(int vertex, int tl, int tr, int l, int r) {
        if (l > r)
            return 0;
        if (l == tl && r == tr) {
            return segmentTree[vertex];
        }
        int tm = tl + (tr - tl) / 2;
        return Math.max(
                getMaxInRange(vertex * 2, tl, tm, l, Math.min(r, tm)),
                getMaxInRange((vertex * 2) + 1, tm + 1, tr, Math.max(l, tm + 1), r)
        );
    }

    static void update(int vertex, int tl, int tr, int pos, int newVal) {
        if (tl == tr) {
            segmentTree[vertex] = newVal;
        } else {
            int tm = tl + (tr - tl) / 2;
            if (pos <= tm)
                update(vertex * 2, tl, tm, pos, newVal);
            else
                update((vertex * 2) + 1, tm + 1, tr, pos, newVal);

            segmentTree[vertex] = Math.max(segmentTree[vertex * 2], segmentTree[(vertex * 2) + 1]);
        }
    }
}
