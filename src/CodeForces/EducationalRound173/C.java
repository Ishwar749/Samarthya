package CodeForces.EducationalRound173;

import java.io.*;
import java.util.*;
public class C {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while(tests-- > 0) {

        }
        out.close();
    }

    static void ruffleSort(int a[]) {

        int n = a.length;

        Random r = new Random();

        for (int i = 0; i < n; i++) {

            int oi = r.nextInt(n);
            int temp = a[oi];
            a[oi] = a[i];
            a[i] = temp;
        }

        Arrays.sort(a);
    }
    static void ruffleSort(long a[]) {

        int n = a.length;

        Random r = new Random();

        for (int i = 0; i < n; i++) {

            int oi = r.nextInt(n);
            long temp = a[oi];
            a[oi] = a[i];
            a[i] = temp;
        }

        Arrays.sort(a);
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
