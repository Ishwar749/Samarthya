package GraphTheory.ShortestPaths.FloydWarshall;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1672

public class ShortestRoutesII {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int nodeCount = in.nextInt();
        int edgeCount = in.nextInt();
        int queryCount = in.nextInt();
        long[][] distance = new long[nodeCount][nodeCount];
        for (long[] row : distance) Arrays.fill(row, Long.MAX_VALUE);
        for (int i = 0; i < nodeCount; i++) distance[i][i] = 0L;

        for (int i = 0; i < edgeCount; i++) {
            int start = in.nextInt() - 1;
            int end = in.nextInt() - 1;
            long price = in.nextInt();
            distance[start][end] = Math.min(distance[start][end], price);
            distance[end][start] = Math.min(distance[end][start], price);
        }

        for (int k = 0; k < nodeCount; k++) {
            for (int i = 0; i < nodeCount; i++) {
                for (int j = 0; j < nodeCount; j++) {
                    if (distance[i][k] != Long.MAX_VALUE && distance[k][j] != Long.MAX_VALUE) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }

        while (queryCount-- > 0) {
            int start = in.nextInt() - 1;
            int end = in.nextInt() - 1;
            if (distance[start][end] == Long.MAX_VALUE)
                out.println(-1);
            else
                out.println(distance[start][end]);
        }

        out.close();
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
