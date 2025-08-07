package DynamicProgramming.DPonTrees;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/161/problem/D

public class DistanceInTree {

    static long answer;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();

        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) tree.put(i, new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        long[][] dp = new long[n][k + 1];
        dfs(0, -1, k, dp, tree);

        out.println(answer);
        out.close();
    }

    static void dfs(int cur, int parent, int k, long[][] dp, Map<Integer, List<Integer>> tree) {

        dp[cur][0] = 1;

        for (int nei : tree.get(cur)) {
            if (nei != parent) {
                dfs(nei, cur, k, dp, tree);

                for (int i = 0; i < k; i++) {
                    answer += (dp[cur][i] * dp[nei][k - i - 1]);
                }

                for (int i = 1; i <= k; i++) {
                    dp[cur][i] += dp[nei][i - 1];
                }
            }
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


