package GraphTheory.BFS;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/242/problem/C
public class KingsPath {

    public static void main(String args[]) {

        FastIO in = new FastIO();

        int start[] = {in.nextInt(), in.nextInt()};
        int dest[] = {in.nextInt(), in.nextInt()};
        int n = in.nextInt();

        Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int row = in.nextInt();
            int low = in.nextInt();
            int high = in.nextInt();

            if (!map.containsKey(row)) map.put(row, new TreeMap<>());
            if (!map.get(row).containsKey(low)) map.get(row).put(low, high);
            map.get(row).put(low, Math.max(map.get(row).get(low), high));
        }

        Map<Integer, Set<Integer>> vis = new HashMap<>();

        int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        Queue<int[]> q = new LinkedList<>();
        int first[] = {start[0], start[1], 0};
        q.add(first);
        vis.put(start[0], new HashSet<>());
        vis.get(start[0]).add(start[1]);

        int steps = 0;
        boolean reached = false;

        while (!q.isEmpty()) {

            int cur[] = q.poll();

            if (cur[0] == dest[0] && cur[1] == dest[1]) {
                reached = true;
                steps = cur[2];
                break;
            }

            for (int e[] : dir) {
                int curRow = e[0] + cur[0];
                int curCol = e[1] + cur[1];

                if (isValidMove(curRow, curCol, map, vis)) {
                    if (!vis.containsKey(curRow)) vis.put(curRow, new HashSet<>());
                    vis.get(curRow).add(curCol);
                    int toAdd[] = {curRow, curCol, cur[2] + 1};
                    q.add(toAdd);
                }
            }
        }

        if (reached) in.println(steps);
        else in.println(-1);

        in.close();

    }


    static boolean isValidMove(int row, int col, Map<Integer, TreeMap<Integer, Integer>> map, Map<Integer, Set<Integer>> vis) {

        if (row < 0 || col < 0 || row > 1e9 || col > 1e9) return false;

        if (vis.containsKey(row) && vis.get(row).contains(col)) return false;

        if (map.containsKey(row)) {
            Integer closest = map.get(row).floorKey(col);
            if (closest != null) {
                int ans[] = {closest, map.get(row).get(closest)};
                if (ans != null && ans[0] <= col && col <= ans[1]) return true;
            }
        }

        return false;
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
