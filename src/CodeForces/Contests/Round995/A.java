package CodeForces.Contests.Round995;

import java.io.*;
import java.util.*;

public class A {
    static class Pair {
        int m;
        int s;

        public Pair(int m, int s) {
            this.m = m;
            this.s = s;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            Pair[] p = new Pair[n];

            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            for (int i = 0; i < n; i++) b[i] = in.nextInt();

            for (int i = 0; i < n; i++) {
                if (i == n - 1) {
                    p[i] = new Pair(a[i], 0);
                } else {
                    p[i] = new Pair(a[i], b[i + 1]);
                }
            }

            Arrays.sort(p, new Comparator<Pair>() {
                public int compare(Pair a, Pair b) {
                    return Integer.compare(b.m, a.m);
                }
            });

            int maxDiff = a[n - 1];

            for (int i = 0; i < n; i++) {
                int ms = 0;
                int ss = 0;
                for (int j = i; j < n; j++) {
                    ms += p[j].m;
                    ss += p[j].s;
                    maxDiff = Math.max(maxDiff, ms - ss);
                }
            }

            int x = 0;

            for (int i = 0; i < n; i++) {
                int diff = p[i].m - p[i].s;
                if (diff > 0) x += diff;
            }
            maxDiff = Math.max(maxDiff, x);
            out.println(maxDiff);
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
