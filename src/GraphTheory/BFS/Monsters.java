package GraphTheory.BFS;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1194

public class Monsters {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int rows = in.nextInt();
        int cols = in.nextInt();

        char[][] lab = new char[rows][cols];
        List<int[]> startA = new ArrayList<>();
        List<int[]> startB = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            String s = in.next();
            for (int j = 0; j < cols; j++) {
                lab[i][j] = s.charAt(j);
                if (lab[i][j] == 'A') startA.add(new int[]{i, j});
                if (lab[i][j] == 'M') startB.add(new int[]{i, j});
            }
        }


        char[][] dirs = new char[rows][cols];
        for (char[] d : dirs) Arrays.fill(d, 'X');
        int disA[][] = bfs(startA, lab, dirs, true);
        int disB[][] = bfs(startB, lab, dirs, false);

        int[] exit = {-1, -1};
        for (int i = 0; i < rows; i++) {
            if (disA[i][0] != Integer.MAX_VALUE && disA[i][0] < disB[i][0]) {
                exit[0] = i;
                exit[1] = 0;
                break;
            }
            if (disA[i][cols - 1] != Integer.MAX_VALUE && disA[i][cols - 1] < disB[i][cols - 1]) {
                exit[0] = i;
                exit[1] = cols - 1;
                break;
            }
        }
        if (exit[0] == -1) {
            for (int j = 0; j < cols; j++) {
                if (disA[0][j] != Integer.MAX_VALUE && disA[0][j] < disB[0][j]) {
                    exit[0] = 0;
                    exit[1] = j;
                    break;
                }
                if (disA[rows - 1][j] != Integer.MAX_VALUE && disA[rows - 1][j] < disB[rows - 1][j]) {
                    exit[0] = rows - 1;
                    exit[1] = j;
                    break;
                }
            }
        }

        if (exit[0] == -1) {
            out.println("NO");
        } else {
            StringBuilder answer = new StringBuilder();
            int row = exit[0];
            int col = exit[1];

            while (dirs[row][col] != 'X') {
                answer.append(dirs[row][col]);
                if (dirs[row][col] == 'U') row++;
                else if (dirs[row][col] == 'D') row--;
                else if (dirs[row][col] == 'L') col++;
                else if (dirs[row][col] == 'R') col--;
            }

            out.println("YES");
            out.println(answer.length());
            out.println(answer.reverse());
        }
        out.close();
    }

    static int[][] bfs(List<int[]> startingPoints, char[][] lab, char[][] dirs, boolean writeDirections) {
        int rows = lab.length;
        int cols = lab[0].length;
        Queue<int[]> q = new LinkedList<>();
        int[][] dis = new int[rows][cols];
        for (int[] d : dis) Arrays.fill(d, Integer.MAX_VALUE);

        for (int[] s : startingPoints) {
            dis[s[0]][s[1]] = 0;
            q.add(s);
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];

            // UP
            if (isValidCell(row - 1, col, lab) && dis[row - 1][col] > dis[row][col] + 1) {
                dis[row - 1][col] = dis[row][col] + 1;
                int[] toAdd = {row - 1, col};
                q.add(toAdd);
                if (writeDirections) dirs[row - 1][col] = 'U';
            }

            // DOWN
            if (isValidCell(row + 1, col, lab) && dis[row + 1][col] > dis[row][col] + 1) {
                dis[row + 1][col] = dis[row][col] + 1;
                int[] toAdd = {row + 1, col};
                q.add(toAdd);
                if (writeDirections) dirs[row + 1][col] = 'D';
            }

            // LEFT
            if (isValidCell(row, col - 1, lab) && dis[row][col - 1] > dis[row][col] + 1) {
                dis[row][col - 1] = dis[row][col] + 1;
                int[] toAdd = {row, col - 1};
                q.add(toAdd);
                if (writeDirections) dirs[row][col - 1] = 'L';
            }

            // RIGHT
            if (isValidCell(row, col + 1, lab) && dis[row][col + 1] > dis[row][col] + 1) {
                dis[row][col + 1] = dis[row][col] + 1;
                int[] toAdd = {row, col + 1};
                q.add(toAdd);
                if (writeDirections) dirs[row][col + 1] = 'R';
            }
        }

        return dis;
    }

    static boolean isValidCell(int row, int col, char[][] lab) {
        int rows = lab.length;
        int cols = lab[0].length;

        if (row >= 0 && row < rows && col >= 0 && col < cols && lab[row][col] != '#')
            return true;

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
