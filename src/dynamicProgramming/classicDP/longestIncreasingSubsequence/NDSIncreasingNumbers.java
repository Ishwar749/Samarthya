package dynamicProgramming.classicDP.longestIncreasingSubsequence;

import java.io.*;
import java.util.*;

// Problem: https://www.spoj.com/problems/NDS/

public class NDSIncreasingNumbers {

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();

            int L = in.nextInt();

            int answer = findLIS(a, L);

            if (answer < Integer.MAX_VALUE) out.println(answer);
            else out.println(-1);
        }

        out.close();
    }

    static int findLIS(int[] a, int L) {
        int length = a.length;
        int[] dp = new int[L + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;

        for (int i = 0; i < length; i++) {
            int index = find(dp, a[i]);

            if (index < dp.length && (dp[index - 1] < a[i] && a[i] < dp[index])) {
                dp[index] = a[i];
            }
        }

        return dp[L];
    }

    static int find(int[] dp, int val) {
        int low = 0;
        int high = dp.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (dp[mid] <= val) low = mid + 1;
            else high = mid - 1;
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
