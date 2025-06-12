package GraphTheory.BinaryLiftingAndLCA;

import java.io.*;
import java.util.*;

public class JumpOnTree {

    static int LOG = 16;
    static int[] depth;
    static int[][] nthParent;
    static Map<Integer, List<Integer>> tree;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int Q = in.nextInt();
        tree = new HashMap<>();
        for (int i = 0; i < N; i++) tree.put(i, new ArrayList<>());
        depth = new int[N];
        Arrays.fill(depth, -1);
        depth[0] = 0;
        nthParent = new int[N][LOG];


        for (int i = 0; i < N - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dfs(0);

        for (int i = 0; i < Q; i++) {
            int s = in.nextInt();
            int t = in.nextInt();
            int v = in.nextInt();
            int lca = getLCA(s, t);
            int left = (depth[s] - depth[lca]) + 1;
            int right = (depth[t] - depth[lca]) + 1;
            int total = (left + right) - 1;
            int answer = s;
            if (v >= total) {
                out.println(-1);
            } else if (v < left) {
                answer = getNthParent(s, v);
                out.println(answer);
            } else {
                int par = total - (v + 1);
                answer = getNthParent(t, par);
                out.println(answer);
            }

        }
        out.close();
    }

    static int getNthParent(int node, int par) {
        int answer = node;
        for (int i = 0; i < LOG; i++) {
            if (((1 << i) & par) > 0) answer = nthParent[answer][i];
        }
        return answer;
    }

    static void dfs(int curNode) {
        for (int nei : tree.get(curNode)) {
            if (depth[nei] == -1) {
                depth[nei] = depth[curNode] + 1;
                nthParent[nei][0] = curNode;
                for (int i = 1; i < LOG; i++) nthParent[nei][i] = nthParent[nthParent[nei][i - 1]][i - 1];
                dfs(nei);
            }
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
