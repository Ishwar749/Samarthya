package DynamicProgramming.BitmaskDP;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/1316/problem/E

public class TeamBuilding {
    static long[][] dp;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int p = in.nextInt();
        int k = in.nextInt();

        dp = new long[n][1 << p];
        for (long[] e : dp) Arrays.fill(e, -1);

        int[][] s = new int[n][p + 1];

        for (int i = 0; i < n; i++) s[i][p] = in.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                s[i][j] = in.nextInt();
            }
        }

        Arrays.sort(s, (a, b) -> Integer.compare(b[p], a[p]));

        long answer = findMaxStrength(0, 0, k, p, s);
        out.println(answer);
        out.close();
    }

    static long findMaxStrength(int cur, int bitmask, int k, int p, int[][] s) {
        if (cur == s.length) return 0;

        if (dp[cur][bitmask] != -1) return dp[cur][bitmask];

        long asPlayer = findMaxStrength(cur + 1, bitmask, k, p, s);
        for (int i = 0; i < p; i++) {
            if (!isSet(bitmask, i))
                asPlayer = Math.max(asPlayer, findMaxStrength(cur + 1, set(bitmask, i), k, p, s) + s[cur][i]);
        }

        long asAudience = 0;
        if (cur - Integer.bitCount(bitmask) < k)
            asAudience = Math.max(asAudience, findMaxStrength(cur + 1, bitmask, k, p, s) + s[cur][p]);

        long answer = Math.max(asPlayer, asAudience);
        dp[cur][bitmask] = answer;
        return answer;
    }

    static boolean isSet(int mask, int pos) {
        if (((mask >> pos) & 1) == 1) return true;
        return false;
    }

    static int set(int mask, int pos) {
        return (mask | (1 << pos));
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
