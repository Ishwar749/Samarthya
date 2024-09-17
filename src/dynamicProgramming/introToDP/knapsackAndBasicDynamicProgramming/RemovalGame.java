package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class RemovalGame {

    static int ddp[][][];
    static long dp[][];

    public static void main(String args[]) {

        FastIO in = new FastIO();

        int n = in.nextInt();
        long a[] = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }

        dp = new long[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {

                if (gap == 0) {
                    dp[i][j] = a[i];
                } else if (gap == 1) {
                    dp[i][j] = Math.max(a[i], a[j]);
                } else {
                    //          Opponent took:    a[i+1]     |    a[j]
                    //                               ^              ^
                    long val1 = a[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);


                    //          Opponent took:     a[i]      |    a[j-1]
                    //                               ^               ^
                    long val2 = a[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]);

                    dp[i][j] = Math.max(val1, val2);
                }
            }
        }

        long ans = dp[0][n - 1];
        in.println(ans);
        in.close();


//        ddp = new int[n][n][2];
//
//        for(int ee[][] : ddp){
//            for(int e[]: ee){
//                Arrays.fill(e,-1);
//            }
//        }
//        int ans = find(0,n-1,true,a);
//        in.println(ans);
//        in.close();
    }

    static int find(int start, int end, boolean myTurn, int a[]) {

        if (start > end) return 0;

        if (myTurn) {

            if (ddp[start][end][1] != -1) return ddp[start][end][1];

            int takeFirst = a[start] + find(start + 1, end, false, a);
            int takeLast = a[end] + find(start, end - 1, false, a);

            ddp[start][end][1] = Math.max(takeFirst, takeLast);
            return ddp[start][end][1];
        } else {

            if (ddp[start][end][0] != -1) return ddp[start][end][0];

            int opTakesFirst = find(start + 1, end, true, a);
            int opTakesLast = find(start, end - 1, true, a);

            ddp[start][end][0] = Math.min(opTakesFirst, opTakesLast);
            return ddp[start][end][0];
        }
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

        public int nextLong() {  // nextLong() would be implemented similarly
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
