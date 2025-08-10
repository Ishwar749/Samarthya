package GraphTheory.TreeDiameter;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1131

public class TreeDiameterDFS {

    static int maxDistance = 0;
    static int firstEndOfDiameter = -1;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        if (n == 1) {
            out.println(0);
            out.close();
            return;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(i, new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        dfs(0, -1, adj, 0);
        dfs(firstEndOfDiameter, -1, adj, 0);

        out.println(maxDistance);
        out.close();
    }

    static void dfs(int cur, int par, List<List<Integer>> adj, int depth) {
        for (int nei : adj.get(cur)) {
            if (nei != par) dfs(nei, cur, adj, depth + 1);
        }

        if (depth > maxDistance) {
            maxDistance = depth;
            firstEndOfDiameter = cur;
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
