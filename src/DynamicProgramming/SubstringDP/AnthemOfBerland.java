package DynamicProgramming.SubstringDP;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/808/problem/G

public class AnthemOfBerland {

    static int[] kmp;
    static int[][] dfa;
    static int[][] dp;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        char[] text = in.next().toCharArray();
        char[] pat = in.next().toCharArray();

        kmp = failureFunctionKMP(pat);
        dfa = dfa(pat, 26, kmp);

        dp = new int[text.length][pat.length];
        for (int[] e : dp) Arrays.fill(e, -1);

        int answer = solve(0, text, 0, pat);
        out.println(answer);
        out.close();
    }

    static int solve(int i, char[] text, int j, char pat[]) {

        if (i == text.length) return 0;

        if (dp[i][j] != -1) return dp[i][j];
        int answer = Integer.MIN_VALUE;

        if (text[i] == '?') {
            for (int c = 0; c < 26; c++) {
                int next = dfa[j][c];
                if (next == pat.length) {
                    next = kmp[j];
                    answer = Math.max(answer, solve(i + 1, text, next, pat) + 1);
                } else {
                    answer = Math.max(answer, solve(i + 1, text, next, pat));
                }
            }
        } else {
            int next = dfa[j][text[i] - 'a'];
            if (next == pat.length) {
                next = kmp[j];
                answer = Math.max(answer, solve(i + 1, text, next, pat) + 1);
            } else {
                answer = Math.max(answer, solve(i + 1, text, next, pat));
            }
        }

        dp[i][j] = answer;
        return answer;
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

        dfa[0][pat[0] - 'a'] = 1;

        for (int i = 1; i < len; i++) {
            for (int c = 0; c < R; c++) {
                dfa[i][c] = dfa[kmp[i - 1]][c];
                if (pat[i] - 'a' == c) {
                    dfa[i][c] = i + 1;
                }
            }
        }

        return dfa;
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
