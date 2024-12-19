package CPSheetTLE;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1859/A

public class UnitedWeStand {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();
        while (tests-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];

            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            Arrays.sort(a);

            List<Integer> b = new ArrayList<>();
            List<Integer> c = new ArrayList<>();

            int first = a[0];
            b.add(first);

            for (int i = 1; i < n; i++) {
                if (a[i] == first) b.add(first);
                else c.add(a[i]);
            }

            if (b.isEmpty() || c.isEmpty()) {
                out.println(-1);
            } else {
                out.println(b.size() + " " + c.size());
                for (int e : b) out.print(e + " ");
                out.println();
                for (int e : c) out.print(e + " ");
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
