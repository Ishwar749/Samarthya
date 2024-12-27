package CPSheetTLE;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1788/A

public class OneAndTwo {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();
        while (tests-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            int twos = 0;

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                if (a[i] == 2) twos++;
            }

            if (twos == 0) out.println(1);
            else if (twos % 2 == 1) out.println(-1);
            else {
                int cnt = 0;
                for (int i = 0; i < n; i++) {
                    if (a[i] == 2) cnt++;
                    if (cnt == twos / 2) {
                        out.println((i + 1));
                        break;
                    }
                }
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
