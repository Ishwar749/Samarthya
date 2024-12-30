package CPSheetTLE;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1783/A

public class MakeItBeautiful {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            TreeMap<Integer, Integer> tm = new TreeMap<>(Collections.reverseOrder());
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                tm.put(a[i], tm.getOrDefault(a[i], 0) + 1);
            }

            if (tm.keySet().size() == 1) {
                out.println("NO");
            } else {
                out.println("YES");
                boolean endLoop = false;

                while (!endLoop) {
                    boolean didPrint = false;
                    for (int key : tm.keySet()) {
                        if (tm.get(key) > 0) {
                            didPrint = true;
                            out.print(key + " ");
                            tm.put(key, tm.get(key) - 1);
                        }
                    }
                    if (!didPrint) endLoop = true;
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
