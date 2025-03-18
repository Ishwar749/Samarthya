package GraphTheory.DFS;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/841/problem/D

public class LehaAndAnotherGameAboutGraph {
    static class Pair {
        int v;
        int n;

        public Pair(int v, int n) {
            this.v = v;
            this.n = n;
        }
    }

    static Map<Integer, List<Pair>> graph;

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        graph = new HashMap<>();

        int n = in.nextInt();
        int m = in.nextInt();
        int[] d = new int[n];
        int[] deg = new int[n];

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
            d[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            graph.get(u).add(new Pair(v, i));
            graph.get(v).add(new Pair(u, i));
        }

        boolean[] vis = new boolean[m];
        List<Integer> takenEdges = new ArrayList<>();
        boolean isPossible = true;

        for (int i = 0; i < n; i++) {
            if (d[i] != -1 && deg[i] % 2 != d[i]) {
                isPossible = dfs(i, d, deg, vis, takenEdges);
                if (!isPossible) break;
            }
        }

        if (!isPossible) out.println(-1);
        else {
            out.println(takenEdges.size());
            for (int num : takenEdges) out.println((num + 1));
        }

        out.close();
    }

    static boolean dfs(int cur, int[] d, int[] deg, boolean[] vis, List<Integer> takenEdges) {
        if (d[cur] == -1 || d[cur] == (deg[cur] % 2)) return true;

        for (Pair nei : graph.get(cur)) {
            if (!vis[nei.n] && d[nei.v] == 1 && (deg[nei.v] % 2) == 0) {
                deg[cur]++;
                deg[nei.v]++;
                vis[nei.n] = true;
                takenEdges.add(nei.n);
                return true;
            }
        }

        for (Pair nei : graph.get(cur)) {
            if (!vis[nei.n] && d[nei.v] == -1) {
                deg[cur]++;
                vis[nei.n] = true;
                takenEdges.add(nei.n);
                return true;
            }
        }

        for (Pair nei : graph.get(cur)) {
            if (!vis[nei.n]) {
                vis[nei.n] = true;
                deg[cur]++;
                deg[nei.v]++;
                if (dfs(nei.v, d, deg, vis, takenEdges)) {
                    takenEdges.add(nei.n);
                    return true;
                } else {
                    vis[nei.n] = false;
                    deg[cur]--;
                    deg[nei.v]--;
                }
            }
        }

        return false;
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();

            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }

                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public int nextInt() {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-') {
                sgn = -1;
                c = read();
            }

            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));

            return res * sgn;
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;

            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }

        public double nextDouble() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, nextInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}
