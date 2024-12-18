package CPSheetTLE;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1873/C

public class TargetPractice {

    static List<List<int[]>> points;

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int sum = 0;

            for (int i = 0; i < 10; i++) {
                String s = in.next();
                for (int j = 0; j < 10; j++) {
                    if (s.charAt(j) == 'X')
                        sum += Math.min(Math.min(i + 1, j + 1), Math.min(10 - i, 10 - j));
                }
            }
            out.println(sum);
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
