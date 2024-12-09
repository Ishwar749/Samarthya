package CPSheetTLE;

import java.io.*;
import java.util.*;

public class DontTryToCount {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {

            int n = in.nextInt();
            int m = in.nextInt();

            String a = in.next();
            String x = a;
            String s = in.next();
            int ops = 0;

            while (!x.contains(s)) {
                x += x;
                if (x.length() > (2 * s.length()) && !x.contains(s)) {
                    ops = -1;
                    break;
                }
                ops++;
            }

            out.println(ops);
        }
        out.close();
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
