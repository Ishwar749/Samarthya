package CPSheetTLE._900;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1883/B

public class Chemistry {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            String s = in.next();

            Map<Character, Integer> freq = new HashMap<>();

            for (int i = 0; i < n; i++) {
                char key = s.charAt(i);
                freq.put(key, freq.getOrDefault(key, 0) + 1);
            }

            int odds = 0;
            for (Character key : freq.keySet()) {
                if (freq.get(key) % 2 == 1) odds++;
            }

            if (k >= odds - 1) out.println("YES");
            else out.println("NO");
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
