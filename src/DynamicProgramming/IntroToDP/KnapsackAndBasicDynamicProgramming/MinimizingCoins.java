package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class MinimizingCoins {

    public static void main(String args[]) {

        FastIO in = new FastIO();

        int noOfCoins = in.nextInt();
        int sum = in.nextInt();
        int coins[] = new int[noOfCoins];

        for (int i = 0; i < noOfCoins; i++) {
            coins[i] = in.nextInt();
        }

        long minCoins[] = new long[sum + 1];
        long x = (long) (1e10);

        Arrays.fill(minCoins, x);
        minCoins[0] = (long) 0;

        for (int i = 0; i <= sum; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    minCoins[i] = Math.min(minCoins[i], minCoins[i - coin] + 1);
                }
            }
        }

        if (minCoins[sum] == x) {
            in.println(-1);
        } else {
            in.println(minCoins[sum]);
        }

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
