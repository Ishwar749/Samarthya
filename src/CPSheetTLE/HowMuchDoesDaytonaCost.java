package CPSheetTLE;

import java.io.*;
import java.util.*;

public class HowMuchDoesDaytonaCost {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();

            int[] a = new int[n];
            boolean containsK = false;

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                if (a[i] == k) containsK = true;
            }

            if (containsK) out.println("YES");
            else out.println("NO");
        }
        out.close();
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) try {
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
