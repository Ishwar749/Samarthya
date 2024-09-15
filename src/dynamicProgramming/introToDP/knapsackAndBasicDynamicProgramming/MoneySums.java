package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class MoneySums {

    public static void main(String args[]){

        FastIO in = new FastIO();

        int n = in.nextInt();
        int a[] = new int[n];

        int max = 0;

        for(int i = 0; i<n; i++){
            a[i] = in.nextInt();
            max = Math.max(max,a[i]);
        }

        int maxSum = max*n;

        boolean dp[][] = new boolean[n][maxSum+1];
        // dp[i][j] is true if using the first i elements of the array, we can make a sum equal to j

        for(int i = 0; i<n; i++){
            dp[i][0] = true;
        }

        dp[0][a[0]] = true;

        for(int i = 1; i<n; i++){
            for(int j = 0; j<=maxSum; j++){
                dp[i][j] = dp[i-1][j];

                if(!dp[i][j] && j-a[i]>=0) dp[i][j] |= dp[i-1][j-a[i]];
            }
        }

        List<Integer> ans = new ArrayList<>();

        for(int j = 1; j<=maxSum; j++){
            if(dp[n-1][j]) ans.add(j);
        }

        in.println(ans.size());
        for(int e: ans){
            in.print(e+" ");
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
