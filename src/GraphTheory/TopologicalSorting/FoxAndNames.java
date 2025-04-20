package GraphTheory.TopologicalSorting;

import java.io.*;
import java.util.*;

public class FoxAndNames {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < 26; i++) graph.put(i, new ArrayList<>());
        int[] inDegree = new int[26];

        int n = in.nextInt();
        String[] s = new String[n];

        for (int i = 0; i < n; i++) {
            s[i] = in.next();
        }

        boolean isPossible = true;
        for (int i = 0; i < n - 1; i++) {
            if (!addEdge(s[i], s[i + 1], graph, inDegree)) {
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            int[] answer = new int[26];
            Arrays.fill(answer, -1);
            int index = 0;

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < 26; i++) if (inDegree[i] == 0) q.add(i);

            while (!q.isEmpty()) {
                int cur = q.poll();
                answer[index] = cur;
                index++;

                for (int nei : graph.get(cur)) {
                    inDegree[nei]--;
                    if (inDegree[nei] == 0) q.add(nei);
                }
            }

            if (answer[25] == -1) out.println("Impossible");
            else for (int e : answer) out.print(getChar(e));
        } else {
            out.println("Impossible");
        }

        out.close();
    }

    static boolean addEdge(String a, String b, Map<Integer, List<Integer>> graph, int[] inDegree) {
        int indA = 0;
        int indB = 0;

        while (indA < a.length() && indB < b.length() && a.charAt(indA) == b.charAt(indB)) {
            indA++;
            indB++;
        }

        if (indA < a.length() && indB < b.length()) {
            int u = getIndex(a.charAt(indA));
            int v = getIndex(b.charAt(indB));
            graph.get(u).add(v);
            inDegree[v]++;
        }

        if (indB >= b.length() && indA < a.length()) return false;
        return true;
    }

    static int getIndex(char a) {
        return a - 97;
    }

    static char getChar(int a) {
        return (char) (a + 97);
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
