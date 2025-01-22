package DynamicProgramming.SubstringDP;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/346/problem/B
public class LuckyCommonSubsequence {

    static int[][] dfa;
    static int[][][] dp;
    static StringBuilder ans;
    static String s1;
    static String s2;
    static String virus;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        s1 = in.next();
        s2 = in.next();
        virus = in.next();
        int rows = s1.length();
        int cols = s2.length();

        dp = new int[rows][cols][virus.length()];
        for (int[][] ee : dp)
            for (int[] e : ee)
                Arrays.fill(e, -1);

        KMP(virus);
        int answer = find(0, 0, 0);
        if (answer == 0) {
            out.println(0);
            out.close();
            return;
        }

        ans = new StringBuilder();
        buildString(0, 0, 0);

        out.println(ans);
        out.close();
    }

    static void buildString(int i, int j, int k) {
        if (i == s1.length() || j == s2.length()) return;

        int curAnswer = find(i, j, k);
        int best = 0;

        best = Math.max(best, find(i + 1, j, k));
        if (best == curAnswer) {
            buildString(i + 1, j, k);
            return;
        }

        best = Math.max(best, find(i, j + 1, k));
        if (best == curAnswer) {
            buildString(i, j + 1, k);
            return;
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            ans.append(s1.charAt(i));
            buildString(i + 1, j + 1, dfa[s1.charAt(i) - 'A'][k]);
            return;
        }
    }

    static int find(int i, int j, int k) {

        if (i == s1.length() || j == s2.length()) return 0;

        if (dp[i][j][k] != -1) return dp[i][j][k];

        int answer = Math.max(find(i + 1, j, k), find(i, j + 1, k));

        if (s1.charAt(i) == s2.charAt(j) && dfa[s1.charAt(i) - 'A'][k] < virus.length()) {
            answer = Math.max(answer, find(i + 1, j + 1, dfa[s1.charAt(i) - 'A'][k]) + 1);
        }

        dp[i][j][k] = answer;
        return answer;
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
