package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;


public class Grid1 {

    public static void main(String args[]) {

        FastIO in = new FastIO();

        int rows = in.nextInt();
        int cols = in.nextInt();

        char a[][] = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String s = in.next();
            for (int j = 0; j < cols; j++) {
                a[i][j] = s.charAt(j);
            }
        }

        long mod = (long) (1e9 + 7);
        long dp[][] = new long[rows][cols];
        dp[0][0] = 1L;

        for (int i = 1; i < rows; i++) {
            if (a[i][0] == '.') dp[i][0] = dp[i - 1][0];
            else {
                dp[i][0] = 0;
            }
        }
        for (int j = 1; j < cols; j++) {
            if (a[0][j] == '.') dp[0][j] = dp[0][j - 1];
            else {
                dp[0][j] = 0;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (a[i][j] == '.') {
                    dp[i][j] = (dp[i - 1][j] % mod + dp[i][j - 1] % mod) % mod;
                } else {
                    dp[i][j] = 0L;
                }
            }
        }

        in.println(dp[rows - 1][cols - 1]);
        in.close();
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
