package GraphTheory.BFS;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/796/problem/D

public class PoliceStations {
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

        int n = in.nextInt();
        int k = in.nextInt();
        int d = in.nextInt();
        graph = new HashMap<>();

        int[] stations = new int[k];
        for (int i = 0; i < k; i++) stations[i] = in.nextInt() - 1;
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            graph.get(u).add(new Pair(v, i));
            graph.get(v).add(new Pair(u, i));
        }

        boolean[] shutRoad = new boolean[n - 1];
        bfs(stations, n, shutRoad);

        int cnt = 0;
        for (int i = 0; i < n - 1; i++) if (shutRoad[i]) cnt++;

        out.println(cnt);
        for (int i = 0; i < n - 1; i++) if (shutRoad[i]) out.print((i + 1) + " ");
        out.close();
    }

    static void bfs(int[] stations, int n, boolean[] shutRoad) {

        boolean[] vis = new boolean[n - 1];
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> q = new LinkedList<>();
        for (int station : stations) {
            q.add(station);
            dis[station] = 0;
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (Pair nei : graph.get(cur)) {
                    if (dis[nei.v] == -1) {
                        dis[nei.v] = dis[cur] + 1;
                        q.add(nei.v);
                        vis[nei.n] = true;
                    } else if (dis[nei.v] <= dis[cur] + 1 && !vis[nei.n]) {
                        shutRoad[nei.n] = true;
                    }
                }
            }
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
