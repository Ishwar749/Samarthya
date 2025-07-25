package GraphTheory.TreeDiameter;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1131


/*
Blog: https://codeforces.com/blog/entry/101271

Finding a diameter

Given a tree with n nodes are multiple ways to find a diameter. Here is one of the simplest ways:

Run a DFS from any node p. Let a be a node whose distance from node p is maximized.
Run another DFS from node a. Let b be a node whose distance from node a is maximized.
a→b is a diameter.

*/

public class TreeDiameter {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Map<Integer, List<Integer>> Tree = new HashMap<>();

        int n = in.nextInt();
        for (int i = 0; i < n; i++) Tree.put(i, new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            Tree.get(u).add(v);
            Tree.get(v).add(u);
        }

//        int[] answer = new int[1];
//        maxDistance(0, -1, Tree, answer);
//        out.println(answer[0]);

        int[] distFromAnyNode = bfs(0, Tree);
        int maxDist = -1;
        int firstEndOfDiameter = -1;

        for (int i = 0; i < n; i++) {
            if (distFromAnyNode[i] > maxDist) {
                maxDist = distFromAnyNode[i];
                firstEndOfDiameter = i;
            }
        }

        int[] distFromOneEndOfDiameter = bfs(firstEndOfDiameter, Tree);
        maxDist = -1;

        for (int i = 0; i < n; i++) maxDist = Math.max(maxDist, distFromOneEndOfDiameter[i]);

        out.println(maxDist);
        out.close();
    }

    static Integer maxDistance(int cur, int par, Map<Integer, List<Integer>> Tree, int[] answer) {

        int max1 = 0;
        int max2 = 0;

        for (int child : Tree.get(cur)) {
            if (child != par) {
                int dist = maxDistance(child, cur, Tree, answer);
                if (dist > max1) {
                    max2 = max1;
                    max1 = dist;
                } else max2 = Math.max(max2, dist);
            }
        }

        answer[0] = Math.max(answer[0], max1 + max2);
        return max1 + 1;
    }

    static int[] bfs(int start, Map<Integer, List<Integer>> Tree) {
        int n = Tree.keySet().size();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[start] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nei : Tree.get(cur)) {
                if (dist[nei] == -1) {
                    dist[nei] = dist[cur] + 1;
                    q.add(nei);
                }
            }
        }

        return dist;
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
