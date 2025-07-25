package GraphTheory.BinaryLiftingAndLCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class CompanyQueriesII {

    static int LOG = 20;
    static Map<Integer, List<Integer>> tree;
    static int[] depth;
    static int[][] nthParent;

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();

        tree = new HashMap<>();
        depth = new int[n + 1];
        nthParent = new int[n + 1][LOG];

        for (int i = 1; i <= n; i++) tree.put(i, new ArrayList<>());

        for (int i = 2; i <= n; i++) {
            int parent = in.nextInt();
            tree.get(parent).add(i);
        }

        dfs(1);

        for (int i = 0; i < q; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            out.println(findLCA(a, b));
        }

        out.close();
    }

    static void dfs(int node) {
        for (int nei : tree.get(node)) {
            depth[nei] = depth[node] + 1;
            nthParent[nei][0] = node;
            for (int i = 1; i < LOG; i++) {
                nthParent[nei][i] = nthParent[nthParent[nei][i - 1]][i - 1];
            }
            dfs(nei);
        }
    }

    static int findLCA(int a, int b) {
        if (depth[b] > depth[a]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];
        for (int i = 0; i < LOG; i++) {
            if (((1 << i) & diff) > 0) a = nthParent[a][i];
        }

        if (a == b) return a;

        for (int i = LOG - 1; i >= 0; i--) {
            if (nthParent[a][i] != nthParent[b][i]) {
                a = nthParent[a][i];
                b = nthParent[b][i];
            }
        }

        return nthParent[a][0];
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
