package CPSheetTLE;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1862/B

public class SequenceGame {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int[] b = new int[n];

            for (int i = 0; i < n; i++) b[i] = in.nextInt();

            List<Integer> answer = new ArrayList<>();
            answer.add(b[0]);

            for (int i = 1; i < n; i++) {
                int last = answer.get(answer.size() - 1);
                if (b[i] < last) answer.add(b[i]);
                answer.add(b[i]);
            }

            out.println(answer.size());
            for (int e : answer) out.print(e + " ");
            out.println();
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
