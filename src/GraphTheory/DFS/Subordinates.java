package GraphTheory.DFS;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1674/

public class Subordinates {

    public static void main(String args[]) {

        FastIO in = new FastIO();

        int n = in.nextInt();

        Map<Integer,List<Integer>> adj = new HashMap<>();
        for(int i = 1; i<=n; i++){
            adj.put(i, new ArrayList<>());
        }

        for(int i = 2; i<=n; i++){
            int u = in.nextInt();
            adj.get(u).add(i);
        }

        int subs[] = new int[n+1];
        find(1,adj,subs);

        for(int i =1; i<=n; i++){
            in.print(subs[i]+" ");
        }

        in.close();

    }

    static int find(int cur, Map<Integer,List<Integer>> adj, int subs[]){

        for(int nei: adj.get(cur)){
            subs[cur] = subs[cur] + (1 + find(nei,adj,subs));
        }

        return subs[cur];
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
