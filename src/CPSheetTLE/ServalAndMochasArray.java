package CPSheetTLE;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1789/A

public class ServalAndMochasArray {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();
        while (tests-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();

            boolean isBeauty = false;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int ans = gcd(a[i], a[j]);
                    if (ans == 1 || ans == 2) {
                        isBeauty = true;
                        break;
                    }
                }
                if (isBeauty) break;
            }

            if (isBeauty) out.println("Yes");
            else out.println("No");
        }
        out.close();
    }

    static int gcd(int a, int b) {
        while (a > 0 && b > 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        if (a == 0) {
            return b;
        }
        return a;
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
