package DynamicProgramming.ClassicDP.RangeDP;

import java.io.*;
import java.util.*;

// Problem: https://saco-evaluator.org.za/cms/sapo2015z/tasks/jazz/description
// Solution: https://www.saco-evaluator.org.za/aqu-editorials/SACO-2015-Space-Jazz.html

public class SpaceJazz {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        char[] a = in.next().toCharArray();
        int len = a.length;
        int[][] dp = new int[len][len];
        for (int[] e : dp) Arrays.fill(e, -1);

        int answer = find(0, len - 1, a, dp);
        out.println(answer);
        out.close();
    }

    static int find(int i, int j, char[] a, int[][] dp) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (dp[i][j] != -1) return dp[i][j];

        int answer = find(i + 1, j, a, dp) + 1;

        for (int k = i + 1; k <= j; k++) {
            if (a[k] == a[i]) {
                answer = Math.min(answer, find(i + 1, k - 1, a, dp) + find(k + 1, j, a, dp));
            }
        }

        dp[i][j] = answer;
        return answer;
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
