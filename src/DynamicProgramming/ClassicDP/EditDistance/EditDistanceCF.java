package DynamicProgramming.ClassicDP.EditDistance;

// Problem: https://codeforces.com/gym/102001/problem/A

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EditDistanceCF {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        String s = in.next();

        int zeros = 0;
        int ones = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') zeros++;
            else ones++;
        }

        String answer = "";

        if (zeros == ones) {

            StringBuilder sb = new StringBuilder(constructString(s.length(), s.charAt(0)));
            sb.setCharAt(0, s.charAt(0) == '1' ? '0' : '1');

            answer = sb.toString();

        } else if (zeros > ones) answer = constructString(s.length(), '1');
        else answer = constructString(s.length(), '0');

        out.println(answer);
        out.close();
    }

    static String constructString(int length, char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append(c);

        return sb.toString();
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
