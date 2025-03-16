package GraphTheory.DFS;

import java.io.*;
import java.util.*;

// Problem: https://www.spoj.com/problems/KOZE/

public class KozeSheep {
    static int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int rows = in.nextInt();
        int cols = in.nextInt();
        char[][] yard = new char[rows][cols];
        int[] totalAnimals = new int[2];
        for (int i = 0; i < rows; i++) {
            String curCol = in.next();
            for (int j = 0; j < cols; j++) {
                yard[i][j] = curCol.charAt(j);
                if (yard[i][j] == 'k') totalAnimals[0]++;
                if (yard[i][j] == 'v') totalAnimals[1]++;
            }
        }

        boolean[][] vis = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!vis[i][j] && isValidCell(i, j, yard)) {
                    vis[i][j] = true;
                    int[] count = new int[2];
                    boolean allSurvive = isOpenSector(i, j, yard, vis, count);
                    if (!allSurvive) {
                        if (count[0] > count[1]) totalAnimals[1] -= count[1];
                        else totalAnimals[0] -= count[0];
                    }
                }
            }
        }

        out.println(totalAnimals[0] + " " + totalAnimals[1]);
        out.close();
    }

    static boolean isOpenSector(int curRow, int curCol, char[][] yard, boolean[][] vis, int[] count) {
        if (yard[curRow][curCol] == 'k') count[0]++;
        if (yard[curRow][curCol] == 'v') count[1]++;

        boolean isOpenSector = false;

        for (int[] dir : dirs) {
            int nextRow = dir[0] + curRow;
            int nextCol = dir[1] + curCol;
            if (isValidCell(nextRow, nextCol, yard) && !vis[nextRow][nextCol]) {
                vis[nextRow][nextCol] = true;
                isOpenSector |= isOpenSector(nextRow, nextCol, yard, vis, count);
            }
        }

        isOpenSector |= isBoundaryCell(curRow, curCol, yard);
        return isOpenSector;
    }

    static boolean isValidCell(int row, int col, char[][] yard) {
        if (row < 0 || row >= yard.length || col < 0 || col >= yard[0].length || yard[row][col] == '#')
            return false;
        return true;
    }

    static boolean isBoundaryCell(int row, int col, char[][] yard) {
        int rows = yard.length;
        int cols = yard[0].length;
        if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) return true;
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
