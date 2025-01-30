package CodeForces.Contests.EducationalRound172;

import java.io.*;
import java.util.*;

public class B {

    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int[] freq = new int[n + 1];

            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                freq[x]++;
            }

            int singles = 0;
            int multiples = 0;

            for (int i = 0; i <= n; i++) {
                if (freq[i] == 1) singles++;
                else if (freq[i] > 0) multiples++;
            }

            int alicesScore = (((singles + 1) / 2) * 2) + multiples;
            out.println(alicesScore);
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
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
