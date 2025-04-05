package GraphTheory.EulerTourTechnique;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1137

public class SubtreeQueries {
    static Map<Integer, List<Integer>> graph;
    static int[] start;
    static int[] end;
    static int timer;

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        graph = new HashMap<>();

        int n = in.nextInt();
        int q = in.nextInt();

        int[] vals = new int[n + 1];
        start = new int[n + 1];
        end = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
            vals[i] = in.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        timer = 0;
        dfs(1, 0);

        SegmentTree segTree = new SegmentTree(n + 1);
        for (int i = 1; i <= n; i++) {
            segTree.set(start[i], vals[i]);
        }


        for (int i = 0; i < q; i++) {
            int form = in.nextInt();
            if (form == 1) {
                int node = in.nextInt();
                int val = in.nextInt();
                segTree.set(start[node], (long) val);
            } else {
                int node = in.nextInt();
                long answer = segTree.sum(start[node], end[node] + 1);
                out.println(answer);
            }
        }
        out.close();
    }

    static void dfs(int cur, int par) {
        timer++;
        start[cur] = timer;
        for (int nei : graph.get(cur)) {
            if (nei != par) dfs(nei, cur);
        }
        end[cur] = timer;
    }

    static class SegmentTree {

        private int size;
        private long[] sums;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) size *= 2;
            sums = new long[2 * size];
        }

        public void build(long[] a) {
            build(a, 0, 0, size);
        }

        public void build(long[] a, int x, int lx, int rx) {
            if (rx - lx == 1) {
                if (lx < a.length) sums[x] = a[lx];
                return;
            }
            int m = (lx + rx) / 2;
            build(a, (2 * x) + 1, lx, m);
            build(a, (2 * x) + 2, m, rx);
            sums[x] = sums[(2 * x) + 1] + sums[(2 * x) + 2];
        }

        public void set(int i, long v) {
            set(i, v, 0, 0, size);
        }

        public void set(int i, long v, int x, int lx, int rx) {
            if (rx - lx == 1) {
                sums[x] = v;
                return;
            }
            int m = (lx + rx) / 2;
            if (i < m) {
                set(i, v, (2 * x) + 1, lx, m);
            } else {
                set(i, v, (2 * x) + 2, m, rx);
            }
            sums[x] = sums[(2 * x) + 1] + sums[(2 * x) + 2];
        }

        public long sum(int l, int r) {
            return sum(l, r, 0, 0, size);
        }

        public long sum(int l, int r, int x, int lx, int rx) {
            if (lx >= r || l >= rx) return 0L;
            if (lx >= l && rx <= r) return sums[x];
            int m = (lx + rx) / 2;
            long s1 = sum(l, r, (2 * x) + 1, lx, m);
            long s2 = sum(l, r, (2 * x) + 2, m, rx);
            sums[x] = s1 + s2;
            return sums[x];
        }
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
