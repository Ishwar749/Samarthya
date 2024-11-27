package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class BookShop {

    public static void main(String args[]) {

        FastIO in = new FastIO();

        int n = in.nextInt();
        int x = in.nextInt();

        int price[] = new int[n];
        int pages[] = new int[n];

        for (int i = 0; i < n; i++) {
            price[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            pages[i] = in.nextInt();
        }

        long dp[] = new long[x + 1];

        for (int i = 0; i <= x; i++) {
            if (i < price[0]) {
                dp[i] = (long) 0;
            } else {
                dp[i] = (long) pages[0];
            }
        }


        for (int i = 1; i < n; i++) {

            long prev[] = new long[x + 1];
            for (int j = 0; j <= x; j++) {
                prev[j] = dp[j];
            }

            for (int j = 0; j <= x; j++) {
                if (j >= price[i]) {
                    dp[j] = Math.max(prev[j], prev[j - price[i]] + pages[i]);
                }
            }

        }

        in.println(dp[x]);

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
