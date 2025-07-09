package GraphTheory.ShortestPaths.FloydWarshall;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/295/B

public class GregAndGraph {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        long[][] graph = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = in.nextInt();
            }
        }

        int[] query = new int[n];
        for (int i = 0; i < n; i++) query[i] = in.nextInt() - 1;

        long[][] dp = new long[n][n];
        for (long[] row : dp) Arrays.fill(row, (long) (1e9));
        boolean[] removed = new boolean[n];
        Arrays.fill(removed, true);

        long[] answers = new long[n];

        for (int i = n - 1; i >= 0; i--) {
            int addNode = query[i];
            removed[addNode] = false;
            answers[i] = findAnswer(addNode, graph, removed, dp);
        }

        for (long answer : answers) out.print(answer + " ");
        out.close();
    }

    static long findAnswer(int nodeAdded, long[][] graph, boolean[] removed, long[][] dp) {
        int n = graph.length;

        for (int i = 0; i < n; i++) {
            dp[i][nodeAdded] = Math.min(dp[i][nodeAdded], graph[i][nodeAdded]);
            dp[nodeAdded][i] = Math.min(dp[nodeAdded][i], graph[nodeAdded][i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i][nodeAdded] + dp[nodeAdded][j]);
            }
        }

        long answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!removed[i] && !removed[j]) answer += dp[i][j];
            }
        }

        return answer;
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
