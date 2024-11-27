package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MinimizingTheSum {
    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int t = in.nextInt();

        while (t-- > 0) {

            int n = in.nextInt();
            int k = in.nextInt();

            int a[] = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            long dp[][] = new long[n][k + 1];

            for (int i = 0; i <= k; i++) {
                dp[0][i] = a[0];
            }

            for (int i = 1; i < n; i++) {
                dp[i][0] = (long) a[i] + dp[i - 1][0];
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= k; j++) {

                    dp[i][j] = Long.MAX_VALUE;
                    int prevPos = i;
                    int min = Integer.MAX_VALUE;

                    while (prevPos >= 0 && (i - prevPos) <= j) {
                        min = Math.min(min, a[prevPos]);
                        long toAdd = (long) min * (long) ((i - prevPos) + 1);
                        if (prevPos - 1 >= 0) toAdd += dp[prevPos - 1][j - (i - prevPos)];
                        dp[i][j] = Math.min(dp[i][j], toAdd);
                        prevPos--;
                    }
                }
            }

            long ans = dp[n - 1][k];
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
