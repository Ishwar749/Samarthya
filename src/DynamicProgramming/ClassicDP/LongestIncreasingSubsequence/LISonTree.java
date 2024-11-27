package DynamicProgramming.ClassicDP.LongestIncreasingSubsequence;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/abc165/tasks/abc165_f

public class LISonTree {

    static int[] answer;
    static int[] nums;
    static int[] dp;

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        nums = new int[N + 1];

        Map<Integer, List<Integer>> tree = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            tree.put(i, new ArrayList<>());
            nums[i] = in.nextInt();
        }

        for (int i = 0; i < N - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        answer = new int[N + 1];
        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;

        boolean[] vis = new boolean[N + 1];
        vis[0] = true;

        traverseTree(1, tree, vis);

        for (int i = 1; i <= N; i++) out.println(answer[i]);
        out.close();
    }

    static void traverseTree(int treeVertex, Map<Integer, List<Integer>> tree, boolean[] vis) {

        vis[treeVertex] = true;
        int index = find(dp, nums[treeVertex]);
        int previousValue = dp[index];


        if (dp[index - 1] < nums[treeVertex] && nums[treeVertex] < dp[index]) {
            dp[index] = nums[treeVertex];
        }

        // 'index' is the value of LIS ending at this vertex's number
        // But in the answer we have to store the LIC till this number. Not necessarily ending at this number

        int LisTillNow = find(dp, Integer.MAX_VALUE - 1)-1;
        answer[treeVertex] = LisTillNow;

        // The reason to subtract one at the end is because find(x) gives the index of a number just greater
        // than x in dp. Hence, find(MAX-1) gives the first index where MAX is stored.
        // But the LIS ends at the last index where MAX is not stored. Which is : first index of MAX - 1;

        for (int child : tree.get(treeVertex)) {
            if (!vis[child]) traverseTree(child, tree, vis);
        }

        dp[index] = previousValue;
    }

    static int find(int[] dp, int val) {
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
