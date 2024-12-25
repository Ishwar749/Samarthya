package CodeForces.EducationalRound173;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            int d = in.nextInt();

            if (n <= 3) {
                int num = 0;
                int fact = 1;
                for (int i = 1; i <= n; i++) fact = fact * i;
                while (fact-- > 0) num = ((num * 10) + d);

                List<Integer> answer = new ArrayList<>();
                answer.add(1);
                for (int i = 3; i <= 9; i += 2) {
                    if (num % i == 0) answer.add(i);
                }

                for (int e : answer) out.print(e + " ");
                out.println();
            } else {
                Set<Integer> answer = new HashSet<>();
                answer.add(1);
                if (d % 2 == 1) answer.add(d);

                for (int digit = 3; digit <= 9; digit += 2) {
                    long num = 0;
                    int x = -1;
                    for (int len = 1; len <= 12; len++) {
                        num = ((num * 10) + d);
                        if (num % digit == 0) {
                            x = len;
                            break;
                        }
                    }
                    if (x != -1) {
                        if (n >= x) answer.add(digit);
                        else {
                            long fact = 1;
                            for (int i = 1; i <= n; i++) fact = fact * i;
                            if (fact % x == 0) answer.add(digit);
                        }
                    }
                }

                List<Integer> res = new ArrayList<>();
                for (int e : answer) res.add(e);
                Collections.sort(res);
                for (int e : res) out.print(e + " ");
                out.println();
            }
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
