package GraphTheory.BinaryLiftingAndLCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// Problem: https://www.spoj.com/problems/LCASQ/
public class LowestCommonAncestor {

    static int LOG = 14; // 2^14 = 16384
    static Map<Integer, List<Integer>> tree;
    static int[][] nthParent;
    static int[] depth;

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        tree = new HashMap<>();

        for (int i = 0; i < n; i++) {
            tree.put(i, new ArrayList<>());
            int m = in.nextInt();
            for (int j = 0; j < m; j++) {
                int child = in.nextInt();
                tree.get(i).add(child);
            }
        }

        depth = new int[n];
        nthParent = new int[n][LOG];
        dfs(0);

        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            out.println(getLCA(u, v));
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

    static int getLCA(int a, int b) {
        // We always assume that node a is deeper than node b
        if (depth[b] > depth[a]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // Bring node a to the same level as b:
        int diff = depth[a] - depth[b];
        for (int i = 0; i < LOG; i++) {
            if (((1 << i) & diff) > 0) a = nthParent[a][i];
        }

        // If node b was the parent of a;
        if (a == b) return a;

        /* 0 to LOG-1 in last for loop will not work. Because we are moving both nodes one by one to the level just below the lowest common ancestor.
        Let us call this level as 'Limiting level'.
        Suppose, we have to make 2 jumps to reach limiting level.
        As you start for loop from 0, you'll check for 2^0 jumps from initial position, then it is allowed (as they will not be equal) , therefore you will make jump.
        From that position, you can check for 2, 4 ,8... jumps. But we are just 1 jump away from limiting level.
        Therefore we can not reach limiting level here, that is why it is not working.
        */
        for (int i = LOG - 1; i >= 0; i--) {
            if (nthParent[a][i] != nthParent[b][i]) {
                a = nthParent[a][i];
                b = nthParent[b][i];
            }
        }

        // Both the nodes are 1 level below their LCA, hence return the parent of a or b
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
