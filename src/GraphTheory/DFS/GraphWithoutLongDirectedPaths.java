package GraphTheory.DFS;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/1144/problem/F

public class GraphWithoutLongDirectedPaths {

    static class Pair {
        int v;
        int edgeNum;
        int dir;

        public Pair(int v, int edgeNum, int dir) {
            this.v = v;
            this.edgeNum = edgeNum;
            this.dir = dir;
        }
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, int[]> edges = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
            edges.put(i, new int[]{u, v});
        }

        int[] side = new int[n];
        Arrays.fill(side, -1);

        if (divideInTwoSides(0, graph, side)) {
            out.println("YES");
            int[] dirs = new int[m];
            for (int key : edges.keySet()) {
                int u = edges.get(key)[0];
                int v = edges.get(key)[1];
                if (side[u] == 0 && side[v] == 1) dirs[key] = 0;
                else dirs[key] = 1;
            }

            for (int i = 0; i < m; i++) {
                out.print(dirs[i]);
            }
        } else {
            out.println("NO");
        }
        out.close();
    }

    static boolean divideInTwoSides(int start, Map<Integer, List<Integer>> graph, int[] side) {
        side[start] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (Integer nei : graph.get(cur)) {
                    if (side[nei] == -1) {
                        side[nei] = (side[cur] + 1) % 2;
                        q.add(nei);
                    } else if (side[nei] == side[cur]) return false;
                }
            }
        }

        return true;
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
