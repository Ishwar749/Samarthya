package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/contest/1446/problem/A
public class Knapsack {

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tc = in.nextInt();

        while (tc-- > 0) {

            int n = in.nextInt();
            long w = in.nextLong();
            long min = (w + 1) / 2;

            long a[] = new long[n];
            int singleIndex = -1;
            long sum = 0;
            List<Integer> res = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                a[i] = in.nextLong();

                if (a[i] > w) continue;
                if (a[i] >= min) {
                    singleIndex = i + 1;
                } else {
                    if (sum + a[i] <= w) {
                        res.add(i + 1);
                        sum += a[i];
                    }
                }
            }

            if (singleIndex > -1) {
                out.println(1);
                out.println(singleIndex);
            } else if (sum >= min) {
                out.println(res.size());
                for (int e : res) {
                    out.print(e + " ");
                }
                out.println();
            } else {
                out.println(-1);
            }
        }

        out.close();
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
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
