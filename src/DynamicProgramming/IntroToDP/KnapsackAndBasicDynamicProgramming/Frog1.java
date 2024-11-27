package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class Frog1 {

    static long dp[];

    public static void main(String args[]) {

        FastIO in = new FastIO();
        int N = in.nextInt();
        int h[] = new int[N];
        for (int i = 0; i < N; i++) {
            h[i] = in.nextInt();
        }

        dp = new long[N];

        for(int i = N-2; i>=0; i--){

            dp[i] = dp[i+1]+(long)Math.abs(h[i]-h[i+1]);
            if(i+2<N) dp[i] = Math.min(dp[i], dp[i+2]+(long)Math.abs(h[i]-h[i+2]));
        }

        System.out.println(dp[0]);

//        long ans = findMinimumCost(0,h);
//        System.out.println(ans);
    }

    static long findMinimumCost(int cur, int h[]) {
        if (cur >= h.length - 1) return 0L;

        if (dp[cur] != (long) -1) return dp[cur];

        dp[cur] = findMinimumCost(cur + 1, h) + (long) Math.abs(h[cur + 1] - h[cur]);
        if (cur + 2 < h.length) {
            dp[cur] = Math.min(dp[cur], findMinimumCost(cur + 2, h) + (long) Math.abs(h[cur + 2] - h[cur]));
        }

        return dp[cur];
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
