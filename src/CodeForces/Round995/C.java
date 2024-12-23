package CodeForces.Round995;


import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();

            int[] a = new int[m];
            int[] q = new int[k];

            for (int i = 0; i < m; i++) a[i] = in.nextInt();
            for (int i = 0; i < k; i++) q[i] = in.nextInt();

            if (k == n - 1) {
                Set<Integer> knows = new HashSet<>();
                for (int e : q) knows.add(e);

                StringBuilder sb = new StringBuilder();

                for (int e : a) {
                    if (!knows.contains(e)) sb.append(1);
                    else sb.append(0);
                }
                out.println(sb);
            } else if (k == n) {
                for (int i = 0; i < m; i++) {
                    out.print(1);
                }
                out.println();
            } else {
                for (int i = 0; i < m; i++) {
                    out.print(0);
                }
                out.println();
            }
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
