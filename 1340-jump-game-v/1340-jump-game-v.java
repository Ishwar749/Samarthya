class Solution {
    public int maxJumps(int[] arr, int d) {
        
        int len = arr.length;
        int[] dp = new int[len];
        Arrays.fill(dp, -1);
        int ans = 0;

        for(int i = 0; i < arr.length; i++) {
            ans = Math.max(ans, findMaxJumps(i, arr, d, dp));
        }

        return ans;
    }

    private int findMaxJumps(int cur, int[] arr, int d, int[] dp) {

        int jumps = 1;
        if(dp[cur] != -1) return dp[cur];

        for(int i = cur + 1; i <= Math.min(arr.length - 1, cur + d); i++) {
            if(arr[i] < arr[cur]) {
                jumps = Math.max(jumps, findMaxJumps(i, arr, d, dp) + 1);
            }
            else break;
        }

        for(int i = cur - 1; i >= Math.max(0, cur - d); i--) {
            if(arr[i] < arr[cur]) {
                jumps = Math.max(jumps, findMaxJumps(i, arr, d, dp) + 1);
            }
            else break;
        }

        dp[cur] = jumps;
        return jumps;
    }
}