package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class GridPaths {

    public static void main(String args[]) {

        FastIO in = new FastIO();

        int n = in.nextInt();
        long mod = (long) (1e9 + 7);

        char c[][] = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < n; j++) {
                c[i][j] = s.charAt(j);
            }
        }

        long ans[][] = new long[n][n];

        if (c[0][0] == '.') {
            ans[0][0] = (long) 1;
        }

        for (int i = 1; i < n; i++) {
            if (c[0][i] == '.') {
                ans[0][i] = ans[0][i - 1];
            } else {
                ans[0][i] = (long) 0;
            }
        }

        for (int i = 1; i < n; i++) {
            if (c[i][0] == '.') {
                ans[i][0] = ans[i - 1][0];
            } else {
                ans[i][0] = (long) 0;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (c[i][j] == '.') {
                    ans[i][j] = (ans[i - 1][j] + ans[i][j - 1]) % mod;
                } else {
                    ans[i][j] = (long) 0;
                }
            }
        }

        in.println(ans[n - 1][n - 1]);
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
