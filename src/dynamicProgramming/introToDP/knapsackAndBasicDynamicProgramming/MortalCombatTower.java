package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class MortalCombatTower {

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tc = in.nextInt();

        while (tc-- > 0) {

            int n = in.nextInt();
            int a[] = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            // 0 is him, 1 is me
            int dp[][] = new int[n][2];

            dp[0][0] = a[0];
            dp[0][1] = 100000000;

            if (n >= 2) {
                dp[1][0] = a[1] + a[0];
                dp[1][1] = a[0];
            }

            for (int i = 2; i < n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1] + a[i], dp[i - 2][1] + a[i] + a[i - 1]);
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 2][0]);
            }

            int ans = Math.min(dp[n - 1][0], dp[n - 1][1]);

            out.println(ans);
        }
        out.close();
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
