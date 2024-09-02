package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class Knapsack1 {

    static long dp[][];

    public static void main(String args[]) {

        FastIO in = new FastIO();
        int N = in.nextInt();
        int W = in.nextInt();

        int w[] = new int[N];
        int v[] = new int[N];
        dp = new long[N][W + 1];

        for (int i = 0; i < N; i++) {
            w[i] = in.nextInt();
            v[i] = in.nextInt();
        }

        for (int j = w[0]; j <= W; j++) {
            dp[0][j] = v[0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= W; j++) {
                if (j - w[i] >= 0) dp[i][j] = dp[i - 1][j - w[i]] + v[i];
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        long answer = dp[N - 1][W];
        //long answer = findMaxValue(N - 1, W, w, v);
        System.out.println(answer);

    }

    static long findMaxValue(int item, int weight, int w[], int v[]) {

        if (item < 0 || weight <= 0) return 0L;
        if (dp[item][weight] != -1) return dp[item][weight];

        long takeItem = Long.MIN_VALUE;
        if (weight - w[item] >= 0) {
            takeItem = findMaxValue(item - 1, weight - w[item], w, v) + v[item];
        }

        long leaveItem = findMaxValue(item - 1, weight, w, v);

        dp[item][weight] = Math.max(takeItem, leaveItem);
        return dp[item][weight];
    }


    // FAST IO TEMPLE STARTS HERE:
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
