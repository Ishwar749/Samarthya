package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CoinCombinations2 {

    static long mod = (long) (1e9 + 7);

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int x = in.nextInt();

        int coins[] = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = in.nextInt();
        }

        long dp[] = new long[x + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= x; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = (dp[j] % mod + dp[j - coins[i]] % mod) % mod;
                }
            }
        }

        long ans = dp[x];
        out.println(ans);
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
