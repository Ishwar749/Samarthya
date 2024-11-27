package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class Frog2 {

    static int dp[];

    public static void main(String args[]) {

        FastIO in = new FastIO();
        int n = in.nextInt();
        int k = in.nextInt();

        int height[] = new int[n];

        dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            height[i] = in.nextInt();
        }

        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0 && j >= i - k; j--) {
                dp[i] = Math.min(dp[i], Math.abs(height[i] - height[j]) + dp[j]);
            }
        }

        in.println(dp[n - 1]);
        in.close();
//
//        int ans = find(0,height,k);
//
//        in.println(ans);
//        in.close();
    }

    static int find(int cur, int height[], int k) {

        if (cur == height.length - 1) return 0;

        if (dp[cur] != -1) return dp[cur];

        int min = Integer.MAX_VALUE;

        for (int i = cur + 1; i < height.length && i <= cur + k; i++) {

            min = Math.min(min, Math.abs(height[i] - height[cur]) + find(i, height, k));
        }

        dp[cur] = min;
        return min;

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
