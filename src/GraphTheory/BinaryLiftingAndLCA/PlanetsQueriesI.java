package GraphTheory.BinaryLiftingAndLCA;

import java.io.*;
import java.util.*;

public class PlanetsQueriesI {
    static int LOG;
    static int[][] up;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();

        LOG = 31; // Why 31? Don't look at n, observe that k can go till 1e9.
        up = new int[n][LOG];

        for (int i = 0; i < n; i++) {
            int par = in.nextInt() - 1;
            up[i][0] = par;
        }
        for (int i = 1; i < LOG; i++) {
            for (int j = 0; j < n; j++) {
                up[j][i] = up[up[j][i - 1]][i - 1];
            }
        }

        for (int i = 0; i < q; i++) {
            int node = in.nextInt() - 1;
            int k = in.nextInt();
            int answer = getKthParent(node, k) + 1;
            out.println(answer);
        }

        out.close();
    }

    static int getKthParent(int node, int k) {
        for (int i = 0; i < LOG; i++) {
            if (((1 << i) & k) > 0) node = up[node][i];
        }
        return node;
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