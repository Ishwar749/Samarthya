package GraphTheory.BinaryLiftingAndLCA;

import java.io.*;
import java.util.*;

public class DistanceQuerySPOJ {
    static class Node {
        int node;
        int len;

        public Node(int node, int len) {
            this.node = node;
            this.len = len;
        }
    }

    static int LOG;
    static int[] depth;
    static int[][] min;
    static int[][] max;
    static int[][] par;
    static Map<Integer, List<Node>> tree;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        LOG = 0;
        while ((1 << LOG) < n) {
            LOG++;
        }
        depth = new int[n + 1];
        Arrays.fill(depth, -1);
        depth[1] = 0;
        min = new int[n + 1][LOG];
        max = new int[n + 1][LOG];
        par = new int[n + 1][LOG];
        for (int[] row : par) Arrays.fill(row, -1);
        tree = new HashMap<>();
        for (int i = 0; i <= n; i++) tree.put(i, new ArrayList<>());


        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            tree.get(u).add(new Node(v, w));
            tree.get(v).add(new Node(u, w));
        }

        dfs(1);

        int k = in.nextInt();
        for (int i = 0; i < k; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            if (u == v) {
                out.println(0 + " " + 0);
                continue;
            }
            int[] ans = new int[2];
            getAns(u, v, ans);
            out.println(ans[0] + " " + ans[1]);
        }
        out.close();
    }

    static void getAns(int a, int b, int[] ans) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int mn = Integer.MAX_VALUE;
        int mx = Integer.MIN_VALUE;

        int diff = depth[a] - depth[b];
        for (int i = 0; i < LOG; i++) {
            if (((1 << i) & diff) > 0 && par[a][i] != -1) {
                mn = Math.min(mn, min[a][i]);
                mx = Math.max(mx, max[a][i]);
                a = par[a][i];
            }
        }

        if (a == b) {
            ans[0] = mn;
            ans[1] = mx;
            return;
        }

        for (int i = LOG - 1; i >= 0; i--) {
            if (par[a][i] != -1 && par[b][i] != -1 && par[a][i] != par[b][i]) {
                mn = Math.min(mn, min[a][i]);
                mx = Math.max(mx, max[a][i]);
                mn = Math.min(mn, min[b][i]);
                mx = Math.max(mx, max[b][i]);
                a = par[a][i];
                b = par[b][i];
            }
        }

        mn = Math.min(mn, min[a][0]);
        mx = Math.max(mx, max[a][0]);
        mn = Math.min(mn, min[b][0]);
        mx = Math.max(mx, max[b][0]);
        ans[0] = mn;
        ans[1] = mx;
    }

    static void dfs(int curNode) {
        for (Node nei : tree.get(curNode)) {
            if (depth[nei.node] == -1) {
                depth[nei.node] = depth[curNode] + 1;
                par[nei.node][0] = curNode;
                min[nei.node][0] = nei.len;
                max[nei.node][0] = nei.len;

                for (int i = 1; i < LOG; i++) {
                    if (par[nei.node][i - 1] != -1) {
                        par[nei.node][i] = par[par[nei.node][i - 1]][i - 1];
                        min[nei.node][i] = Math.min(min[nei.node][i - 1], min[par[nei.node][i - 1]][i - 1]);
                        max[nei.node][i] = Math.max(min[nei.node][i - 1], max[par[nei.node][i - 1]][i - 1]);
                    }
                }
                dfs(nei.node);
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
