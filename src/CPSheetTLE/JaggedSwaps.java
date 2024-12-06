package CPSheetTLE;

import java.io.*;
import java.util.*;

public class JaggedSwaps {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];

            for (int i = 0; i < n; i++) a[i] = in.nextInt();

            int swapAt = -1;

            for (int i = 1; i < n - 1; i++) {
                if (a[i] > a[i - 1] && a[i] > a[i + 1]) {
                    swapAt = i;
                    break;
                }
            }

            while (swapAt != -1) {
                swap(swapAt, a);
                swapAt = -1;
                for (int i = 1; i < n - 1; i++) {
                    if (a[i] > a[i - 1] && a[i] > a[i + 1]) {
                        swapAt = i;
                        break;
                    }
                }
            }

            if (isSorted(a)) out.println("YES");
            else out.println("NO");

            /*
             * Alternate solution:
             * System.out.println(arr[0]==1?"YES":"NO");
             */
        }
        out.close();
    }

    static void swap(int index, int[] a) {
        int temp = a[index];
        a[index] = a[index + 1];
        a[index + 1] = temp;
    }

    static boolean isSorted(int[] a) {
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
