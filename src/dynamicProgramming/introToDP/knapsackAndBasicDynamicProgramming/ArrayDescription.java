package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class ArrayDescription {

    static long mod = 1000000007;
    static long dp[][];

    public static void main(String args[]){

        FastIO in = new FastIO();

        int n = in.nextInt();
        int m = in.nextInt();
        int a[] = new int[n];

        for(int i = 0; i<n; i++){
            a[i] = in.nextInt();
        }

        if(n==1) {
            if(a[0]==0) in.println(m);
            else in.println(1);
            in.close();
            return;
        }

        dp = new long[m+1][n];

        for(long e[]: dp){
            Arrays.fill(e,(long)-1);
        }

        long ans = 0L;

        if(a[0]==0){
            for(int i =1; i<=m; i++){
                ans = (ans%mod+find(i,1,a,m)%mod)%mod;
            }
        }
        else {
            ans = find(a[0], 1, a, m);
        }
        in.println(ans);
        in.close();

    }

    static long find(int last, int cur, int a[], int m){

        if(last<0 || last>m) return 0L;
        if(cur==a.length) return 1L;

        if(dp[last][cur]!=-1) return dp[last][cur];

        long res = 0L;

        if(a[cur]==0){
            res = find(last,cur+1,a,m)%mod;
            res = (res%mod+find(last-1,cur+1,a,m)%mod)%mod;
            res = (res%mod+find(last+1,cur+1,a,m)%mod)%mod;
        }
        else{
            if(Math.abs(last-a[cur])<=1) {
                res = find(last, cur + 1, a, m)%mod;
            }
        }

        dp[last][cur] = res;
        return res;
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
