package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1633/

public class diceCombinations {
    static final int MOD = (int) 1e9 + 7;
    static int SIZE = 7;
    static long dice[] = new long[SIZE];

    public static void main(String args[]) {

        Arrays.fill(dice, 1L);
        dice[0] = 0;

        for (int i = 2; i < SIZE; i++) {
            for (int j = 1; j < i; j++) {
                dice[i] += dice[j];
            }
        }

        FastIO in = new FastIO();
        int n = in.nextInt();

        for (int i = 7; i <= n; i++) {
            dice[i % SIZE] = (dice[(i - 1) % SIZE] % MOD
                    + dice[(i - 2) % SIZE] % MOD
                    + dice[(i - 3) % SIZE] % MOD
                    + dice[(i - 4) % SIZE] % MOD
                    + dice[(i - 5) % SIZE] % MOD
                    + dice[(i - 6) % SIZE] % MOD) % MOD;
        }

        System.out.println(dice[n % SIZE]);
    }
}

// FAST IO TEMPLE STARTS HERE:
class FastIO extends PrintWriter {
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
