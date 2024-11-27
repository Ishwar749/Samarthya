package DynamicProgramming.ClassicDP.EditDistance;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1639
public class EditDistance {

    static int[][] dp;

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        String a = in.next();
        String b = in.next();

        int rows = a.length();
        int cols = b.length();

        dp = new int[rows + 1][cols + 1];

        for (int i = 1; i <= rows; i++) dp[i][0] = i;
        for (int i = 1; i <= cols; i++) dp[0][i] = i;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        int answer = dp[rows][cols];
        out.println(answer);
        out.close();
    }

    static int find(int i, int j, String a, String b) {

        if (i < 0 && j < 0) return 0;
        else if (i < 0 || j < 0) {
            int answer = 0;
            if (i < 0) answer = j + 1;
            if (j < 0) answer = i + 1;
            return answer;
        }

        if (dp[i][j] != -1) return dp[i][j];

        if (a.charAt(i) == b.charAt(j)) {
            dp[i][j] = find(i - 1, j - 1, a, b);
            return dp[i][j];
        } else {
            dp[i][j] = find(i, j - 1, a, b) + 1; // Added one character to String a
            dp[i][j] = Math.min(dp[i][j], find(i - 1, j, a, b) + 1); // Removed the character at i in String a
            dp[i][j] = Math.min(dp[i][j], find(i - 1, j - 1, a, b) + 1); // Replaced the character at i in String a
            return dp[i][j];
        }
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
