package CodeForces.Contests.Round995;


import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            long n = in.nextLong();
            long a = in.nextLong();
            long b = in.nextLong();
            long c = in.nextLong();

            long sum = a + b + c;
            long days = (n / sum) * 3;
            long mod = n % sum;

            if (mod > 0) {
                if (mod <= a) days++;
                else if (mod <= (a + b)) days += 2;
                else days += 3;
            }

            out.println(days);
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
