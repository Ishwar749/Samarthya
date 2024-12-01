package CPSheetTLE;

import java.io.*;
import java.util.*;

public class HalloumiBoxes {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();

            int[] a = new int[n];

            for (int i = 0; i < n; i++) a[i] = in.nextInt();

            if (k == 1 && !isAlreadySorted(a))
                out.println("NO");
            else
                out.println("YES");
        }
        out.close();
    }

    static boolean isAlreadySorted(int[] a) {

        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) return false;
        }

        return true;
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
