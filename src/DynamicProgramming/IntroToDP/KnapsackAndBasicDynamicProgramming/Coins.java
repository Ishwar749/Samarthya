package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/dp/tasks/dp_i
public class Coins {

    public static void main(String args[]){

        FastIO in = new FastIO();

        int n = in.nextInt();
        double p[] = new  double[n];

        for(int i = 0; i<n; i++){
            p[i] = in.nextDouble();
        }

        double dp[][] = new double[n][n+1];

        dp[0][0] = (double)1-p[0];
        for(int i = 1; i<n; i++){
            dp[i][0] = dp[i-1][0]*(double)((double)1-p[i]);
        }

        dp[0][1] = p[0];

        for(int i = 1; i<n; i++){
            for(int j = 1; j<=i+1; j++){
                dp[i][j] = dp[i-1][j]*((double)((double)1-p[i])) + dp[i-1][j-1]*(p[i]);
            }
        }

        double totalProbability = (double)0;
        int heads = n;

        while(heads>n/2){
            totalProbability += dp[n-1][heads];
            heads--;
        }

        in.println(totalProbability);
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
