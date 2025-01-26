package DynamicProgramming.SubstringDP;

import java.io.*;
import java.util.*;


// Problem: https://codeforces.com/problemset/problem/1015/F

public class BracketSubstring {

    static long MOD = (long) (1e9 + 7);
    static int[][] dfa;
    static long dp[][][];

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        char[] pat = in.next().toCharArray();
        dfa = dfa(pat, 2, failureFunctionKMP(pat));

        dp = new long[n + 1][n + 1][pat.length + 1];
        for (long[][] ee : dp)
            for (long[] e : ee)
                Arrays.fill(e, -1L);

        long answer = solve(0, 0, n, 0, pat);
        out.println(answer);
        out.close();
    }

    static long solve(int open, int closed, int n, int j, char[] pat) {

        if (open == n && closed == n) {
            if (j == pat.length) return 1L;
            else return 0L;
        }

        if (dp[open][closed][j] != -1L)
            return dp[open][closed][j];

        if (j < pat.length) {
            long answer = 0L;
            if (open < n)
                answer = modAdd(answer, solve(open + 1, closed, n, dfa[j][0], pat));
            if (closed < open) {
                answer = modAdd(answer, solve(open, closed + 1, n, dfa[j][1], pat));
            }

            dp[open][closed][j] = answer;
            return answer;
        } else {
            long answer = 0L;
            if (open < n) {
                answer = modAdd(answer, solve(open + 1, closed, n, j, pat));
            }
            if (closed < open) {
                answer = modAdd(answer, solve(open, closed + 1, n, j, pat));
            }

            dp[open][closed][j] = answer;
            return answer;
        }
    }

    static int[] failureFunctionKMP(char[] pat) {
        int len = pat.length;
        int[] kmp = new int[len];
        kmp[0] = 0; // Always;

        for (int i = 1; i < len; i++) {
            int j = i - 1;
            int x = kmp[j];

            while (pat[i] != pat[x]) {
                j = x - 1;
                if (j < 0) break;
                x = kmp[j];
            }

            if (j < 0) kmp[i] = 0;
            else kmp[i] = kmp[j] + 1;
        }

        return kmp;
    }

    static int[][] dfa(char[] pat, int R, int[] kmp) {
        int len = pat.length;
        int[][] dfa = new int[len][R];

        dfa[0][pat[0] - '('] = 1;

        for (int i = 1; i < len; i++) {
            for (int c = 0; c < R; c++) {
                dfa[i][c] = dfa[kmp[i - 1]][c];
                if (pat[i] - '(' == c) {
                    dfa[i][c] = i + 1;
                }
            }
        }

        return dfa;
    }

    public static long modAdd(long a, long b) {
        return (a % MOD + b % MOD) % MOD;
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
