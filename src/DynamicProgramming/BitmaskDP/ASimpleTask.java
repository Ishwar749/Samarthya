package DynamicProgramming.BitmaskDP;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/11/problem/D

public class ASimpleTask {
    static long[][] dp;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        dp = new long[n][1 << n];
        for (long[] e : dp) Arrays.fill(e, -1);

        boolean[][] graph = new boolean[n][n];

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            graph[u][v] = true;
            graph[v][u] = true;
        }

        long answer = 0;

        for (int i = 0; i < n; i++) {
            answer += findCycles(i, i, 1 << i, graph);
            out.println(i + ": " + answer);
        }

        out.println((answer / 2));
        out.close();
    }

    static long findCycles(int cur, int startOfCycle, int bitmask, boolean[][] graph) {
        if (dp[cur][bitmask] != -1)
            return dp[cur][bitmask];

        long answer = 0;

        for (int nei = startOfCycle; nei < graph.length; nei++) {
            if (graph[cur][nei]) {
                if (nei == startOfCycle && bitmask != ((1 << nei) | (1 << cur))) answer++;
                if (nei != startOfCycle && !isSet(bitmask, nei)) {
                    answer += findCycles(nei, startOfCycle, set(bitmask, nei), graph);
                }
            }
        }

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
