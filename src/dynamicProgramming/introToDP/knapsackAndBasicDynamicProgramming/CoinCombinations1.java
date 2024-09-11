package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1635/
public class CoinCombinations1 {

    static final int MOD = (int) (1e9 + 7);
    static int dp[];
    static PrintWriter pw = new PrintWriter(System.out);

    public static void main(String args[]) {

        FastIO in = new FastIO();
        int n = in.nextInt();
        int x = in.nextInt();
        int coins[] = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = in.nextInt();
        }
        Arrays.sort(coins);

        dp = new int[x + 1];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = 0;
            for (int j = 0; j < n; j++) {
                if (coins[j] > i) { break; }
                dp[i] += dp[i - coins[j]];

                if (dp[i] > MOD) dp[i] -= MOD;
            }
        }
        pw.println(dp[x]);
        pw.close();

//        long answer = findCombinations(x, coins);
//        System.out.println(answer);
    }


    static int findCombinations(int sum, int coins[]) {

        if (sum == 0) return 1;
        if (dp[sum] != -1L) return dp[sum];

        int answer = 0;

        for (int e : coins) {
            if (sum - e >= 0) {
                answer = (answer % MOD + findCombinations(sum - e, coins) % MOD) % MOD;
            }
        }

        dp[sum] = answer;
        return dp[sum];
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
