package GraphTheory.BinaryLiftingAndLCA;

// Problem: https://cses.fi/problemset/task/1687

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CompanyQueriesI {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();

        int[] parent = new int[n];
        parent[0] = -1;

        for (int i = 1; i < n; i++) {
            parent[i] = in.nextInt() - 1;
        }

        int[][] nthParent = new int[n][20];
        for (int[] row : nthParent) Arrays.fill(row, -1);
        fillParentValues(n, parent, nthParent);

        for (int qq = 0; qq < q; qq++) {
            int x = in.nextInt() - 1;
            int k = in.nextInt();

            if (k > n || x < 0) out.println(-1);
            else {
                int answer = x; // Zero based indexing

                for (int i = 0; i < 20; i++) {
                    if (((1 << i) & k) > 0) answer = nthParent[answer][i];
                    if (answer == -1) break;
                }


                if (answer != -1) answer++; // Converting to 1 based indexing
                out.println(answer);
            }
        }

        out.close();
    }

    static void fillParentValues(int n, int[] parent, int[][] nthParent) {

        for (int i = 0; i < n; i++) {
            nthParent[i][0] = parent[i];
        }

        for (int j = 1; j < 20; j++) {
            for (int i = 0; i < n; i++) {
                if (nthParent[i][j - 1] != -1) {
                    nthParent[i][j] = nthParent[nthParent[i][j - 1]][j - 1];
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
