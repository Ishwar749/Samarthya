package GraphTheory.DFS;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/1093/problem/D

public class BeautifulGraph {
    static Map<Integer, List<Integer>> graph;
    static long MOD = (long) 998244353;

    static class Pair {
        int col;
        int ele;

        public Pair(int col, int ele) {
            this.col = col;
            this.ele = ele;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            graph = new HashMap<>();
            int[] color = new int[n + 1];
            for (int i = 1; i <= n; i++) graph.put(i, new ArrayList<>());

            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            long answer = 1;
            for (int i = 1; i <= n; i++) {
                if (color[i] == 0) {
                    int[] cnt = new int[2];
                    boolean isPossible = isBipartite(i, color, cnt);
                    if (!isPossible) {
                        answer = 0;
                        break;
                    }
                    long ways = modAdd(modPow(2, cnt[0]), modPow(2, cnt[1]));
                    answer = modMul(answer, ways);
                }
            }

            out.println(answer);
        }
        out.close();
    }

    static boolean isBipartite(int cur, int[] color, int[] cnt) {
        color[cur] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(cur);
        int ind = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            cnt[ind] += size;
            for (int i = 0; i < size; i++) {
                int u = q.poll();
                for (int nei : graph.get(u)) {
                    if (color[nei] == 0) {
                        color[nei] = color[u] == 1 ? 2 : 1;
                        q.add(nei);
                    } else if (color[nei] == color[u]) return false;
                }
            }
            ind = (ind + 1) % 2;
        }

        return true;
    }

    public static long modAdd(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }

    public static long modMul(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    public static long modPow(long a, long b) {
        long res = 1L;
        a = a % MOD;

        if (a == 0)
            return 0;

        while (b > 0) {
            if ((b & 1) != 0)
                res = modMul(res, a);

            b = b >> 1;
            a = modMul(a, a);
        }
        return res;
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
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
