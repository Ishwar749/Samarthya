package GraphTheory.DFS;


import java.io.*;
import java.util.*;

public class BuildingRoads {

    public static void main(String args[]){

        FastIO in = new FastIO();
        int n = in.nextInt();
        int m = in.nextInt();
        boolean vis[] = new boolean[n+1];
        Map<Integer,List<Integer>> adj = new HashMap<>();

        for(int i = 0; i<=n; i++){
            adj.put(i, new ArrayList<>());
        }

        for(int i = 0; i<m; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        List<Integer> roadsToAdd = new ArrayList<>();

        traverse(1,adj,vis);

        for(int i = 1; i<=n; i++){
            if(!vis[i]) {
                roadsToAdd.add(i);
                traverse(i,adj,vis);
            }
        }

        System.out.println(roadsToAdd.size());
        for(int e: roadsToAdd){
            System.out.println(1+" "+e);
        }

    }

    static void traverse(int cur, Map<Integer,List<Integer>> adj, boolean vis[]){

        vis[cur] = true;

        for(int nei: adj.get(cur)){
            if(!vis[nei]){
                traverse(nei,adj,vis);
            }
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
