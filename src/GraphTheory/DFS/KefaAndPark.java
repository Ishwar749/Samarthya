package GraphTheory.DFS;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/580/C

public class KefaAndPark {

    public static void main(String args[]) {

        FastIO in = new FastIO();

        int n = in.nextInt();
        int m = in.nextInt();

        Map<Integer, List<Integer>> adj = new HashMap<>();
        boolean hasCat[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x == 1) hasCat[i] = true;
            adj.put(i, new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int ans[] = new int[1];

        find(0, -1, hasCat[0] ? 1 : 0, ans, adj, hasCat, m);

        in.println(ans[0]);
        in.close();

    }

    static void find(int cur, int par, int catsTillNow, int ans[], Map<Integer, List<Integer>> adj, boolean hasCat[], int m) {

        if (catsTillNow > m) return;

        if (adj.get(cur).size() == 1 && adj.get(cur).get(0) == par) {
            ans[0]++;
        }

        for (int nei : adj.get(cur)) {
            if (nei != par) {
                find(nei, cur, hasCat[nei] ? (catsTillNow + 1) : 0, ans, adj, hasCat, m);
            }
        }
    }

    // FAST IO TEMPLATE STARTS HERE:
    static class FastIO extends PrintWriter {
        private InputStream stream;
        private byte[] buf = new byte[1 << 16];
        private int curChar;
        private int numChars;

        // standard input
        public FastIO() {
            this(System.in, System.out);
        }

        public FastIO(InputStream i, OutputStream o) {
            super(o);
            stream = i;
        }

        // file input
        public FastIO(String i, String o) throws IOException {
            super(new FileWriter(o));
            stream = new FileInputStream(i);
        }

        // throws InputMismatchException() if previously detected end of file
        private int nextByte() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars == -1) {
                    return -1;  // end of file
                }
            }
            return buf[curChar++];
        }

        // to read in entire lines, replace c <= ' '
        // with a function that checks whether c is a line break
        public String next() {
            int c;
            do {
                c = nextByte();
            } while (c <= ' ');

            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = nextByte();
            } while (c > ' ');
            return res.toString();
        }

        public int nextInt() {  // nextLong() would be implemented similarly
            int c;
            do {
                c = nextByte();
            } while (c <= ' ');

            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = nextByte();
            }

            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res = 10 * res + c - '0';
                c = nextByte();
            } while (c > ' ');
            return res * sgn;
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
