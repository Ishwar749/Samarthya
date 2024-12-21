package CPSheetTLE;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1845/A

public class ForbiddenInteger {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int x = in.nextInt();

            if (x == 1) {
                if (n % 2 == 0) {
                    if (k == 1) out.println("NO");
                    else {
                        out.println("YES");
                        out.println(n / 2);
                        for (int i = 0; i < n / 2; i++) {
                            out.print(2 + " ");
                        }
                        out.println();
                    }
                } else {
                    if (k == 1 || k == 2) out.println("NO");
                    else {
                        int twos = (n / 2) - 1;
                        out.println("YES");
                        out.println((twos + 1));
                        for (int i = 0; i < twos; i++) {
                            out.print(2 + " ");
                        }
                        out.print(3 + " ");
                        out.println();
                    }
                }
            } else {
                out.println("YES");
                out.println(n);
                for (int i = 0; i < n; i++) {
                    out.print(1 + " ");
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
