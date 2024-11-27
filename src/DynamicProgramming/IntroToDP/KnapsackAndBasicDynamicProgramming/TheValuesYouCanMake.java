package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class TheValuesYouCanMake {

    static int dp[][];

    public static void main(String args[]) {

        FastIO in = new FastIO();

        int N = in.nextInt();
        int K = in.nextInt();

        int coins[] = new int[N];

        for (int i = 0; i < N; i++) {
            coins[i] = in.nextInt();
        }


        boolean dp[][][] = new boolean[N][K + 1][K + 1];
        dp[0][0][0] = true;
        if (coins[0] <= K) {
            dp[0][coins[0]][0] = true;
            dp[0][coins[0]][coins[0]] = true;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= K; j++) {
                for (int k = 0; k <= K; k++) {

                    boolean usedInJandK = false;
                    if (j - coins[i] >= 0 && k - coins[i] >= 0) usedInJandK = dp[i - 1][j - coins[i]][k - coins[i]];

                    boolean usedInJ = false;
                    if (j - coins[i] >= 0) usedInJ = dp[i - 1][j - coins[i]][k];

                    boolean notUsedInBoth = dp[i - 1][j][k];

                    dp[i][j][k] = usedInJandK || usedInJ || notUsedInBoth;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int k = 0; k <= K; k++) {
            if (dp[N - 1][K][k]) result.add(k);
        }

        in.println(result.size());
        for (int e : result) in.print(e + " ");

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
