package CPSheetTLE;

import java.io.*;
import java.util.StringTokenizer;

public class LineTrip {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int x = in.nextInt();

            int[] points = new int[n];

            int maxDiff = 0;
            int last = 0;

            for (int i = 0; i < n; i++) {
                points[i] = in.nextInt();
                maxDiff = Math.max(maxDiff, points[i] - last);
                last = points[i];
            }

            maxDiff = Math.max(maxDiff, (x - last) * 2);

            out.println(maxDiff);
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
