package CPSheetTLE;

import java.io.*;
import java.util.*;

public class CoverInWater {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            String s = in.next();
            int emptyCells = 0;
            int answer = 0;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '.') emptyCells++;
            }

            answer = emptyCells;

            for (int i = 2; i < n; i++) {
                if (hasThreeEmptyCells(i, s)) {
                    answer = 2;
                    break;
                }
            }

            out.println(answer);
        }
        out.close();
    }

    static boolean hasThreeEmptyCells(int index, String s) {
        return s.charAt(index) == '.' && s.charAt(index - 1) == '.' && s.charAt(index - 2) == '.';
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
