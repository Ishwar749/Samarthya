package GraphTheory.DFS;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/638/problem/C

public class RoadImprovement {
    static class Edge {
        int v;
        int n;

        public Edge(int v, int n) {
            this.v = v;
            this.n = n;
        }
    }

    static Map<Integer, List<Edge>> graph;
    static int answer;

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        graph = new HashMap<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            graph.get(u).add(new Edge(v, i));
            graph.get(v).add(new Edge(u, i));
        }
        answer = 0;
        for (int key : graph.keySet()) {
            answer = Math.max(answer, graph.get(key).size());
        }

        boolean[] vis = new boolean[n];
        int[] repairOn = new int[n - 1];
        vis[0] = true;
        dfs(0, vis, repairOn, 0);

        out.println(answer);
        Map<Integer, List<Integer>> res = new HashMap<>();
        for (int edge = 0; edge < n - 1; edge++) {
            int day = repairOn[edge];
            if (!res.containsKey(day)) res.put(day, new ArrayList<>());
            res.get(day).add(edge);
        }

        for (int i = 1; i <= answer; i++) {
            out.print(res.get(i).size() + " ");
            for (int edge : res.get(i)) out.print((edge + 1) + " ");
            out.println();
        }
        out.close();
    }

    static void dfs(int cur, boolean[] vis, int[] repairOn, int skip) {
        int day = 1;
        for (Edge nei : graph.get(cur)) {
            if (!vis[nei.v]) {
                vis[nei.v] = true;
                if (day == skip) day++;
                repairOn[nei.n] = day;
                dfs(nei.v, vis, repairOn, day);
                day++;
            }
        }
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;
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
