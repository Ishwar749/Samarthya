//package DynamicProgramming.SubstringDP;

import java.io.*;
import java.util.*;

public class RequiredSubstring {

    static long MOD = (long) (1e9 + 7);
    static long[][] dp;
    static int[][] dfa;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        String pat = in.next();
        dp = new long[n + 1][pat.length() + 1];
        for (long[] e : dp) Arrays.fill(e, -1);

        KMP(pat);
        long answer = find(0, n, 0, pat);

        out.println(answer);
        out.close();
    }

    public static long find(int i, int n, int j, String pat) {

        if (i == n) return dp[i][j] = (j == pat.length() ? 1L : 0L);
        if (dp[i][j] != -1) return dp[i][j];
        if (j == pat.length()) {
            return dp[i][j] = modMul(26, find(i + 1, n, j, pat));
        }

        long ans = 0;

        for (int c = 0; c < 26; c++) {
            int t = j;
            t = dfa[c][j];
            ans = modAdd(ans, find(i + 1, n, t, pat));
        }

        dp[i][j] = ans;
        return ans;
    }

    public static void KMP(String pat) {
        int M = pat.length();
        int R = 26;
        dfa = new int[R][M];
        dfa[pat.charAt(0) - 'A'][0] = 1;

        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j) - 'A'][j] = j + 1;
            X = dfa[pat.charAt(j) - 'A'][X];
        }
    }

    public static long modAdd(long a, long b) {
        return (a % MOD + b % MOD) % MOD;
    }

    public static long modMul(long a, long b) {
        return (a % MOD * b % MOD) % MOD;
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) try {
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
