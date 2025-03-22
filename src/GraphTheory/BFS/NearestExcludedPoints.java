package GraphTheory.BFS;

import java.io.*;
import java.util.*;

public class NearestExcludedPoints {
    static Map<String, Integer> setOfPoints;

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        setOfPoints = new HashMap<>();
        int[][] points = new int[n][2];

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            points[i][0] = x;
            points[i][1] = y;
            setOfPoints.put(x + "*" + y, i);
        }

        int[][] res = bfs(points);
        for (int i = 0; i < res.length; i++) {
            out.println(res[i][0] + " " + res[i][1]);
        }
        out.close();
    }

    static int[][] bfs(int[][] points) {
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int n = points.length;
        int[] dis = new int[n];
        int[][] res = new int[n][2];
        Arrays.fill(dis, -1);
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < points.length; i++) {
            int[] cur = points[i];
            for (int[] d : dir) {
                int[] point = {d[0] + cur[0], d[1] + cur[1]};
                if (!isInSet(point)) {
                    dis[i] = 1;
                    res[i] = point;
                    q.add(i);
                    break;
                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (int d[] : dir) {
                    int[] point = {d[0] + points[cur][0], d[1] + points[cur][1]};
                    if (isInSet(point)) {
                        int ind = setOfPoints.get(point[0] + "*" + point[1]);
                        if (dis[ind] == -1) {
                            dis[ind] = dis[cur] + 1;
                            res[ind] = points[cur];
                            q.add(ind);
                        }
                    }
                }
            }
        }

        return res;
    }

    static boolean isInSet(int[] point) {
        String toSearch = point[0] + "*" + point[1];
        return setOfPoints.containsKey(toSearch);
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
