package Basics.Math;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/2431

public class DigitQueries {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int queries = in.nextInt();
        while (queries-- > 0) {
            long k = in.nextLong();
            char answer = findKthDigit(k);
            out.println(answer);
        }
        out.close();
    }

    static char findKthDigit(long k) {
        long digits = 1;
        long numbersInRange = 9;
        while (k - (numbersInRange * digits) > 0) {
            k -= (numbersInRange * digits);
            numbersInRange = numbersInRange * 10;
            digits++;
        }

        long first = (long) Math.pow(10, digits - 1);
        long number = (first + ((k - 1) / digits));
        String num = number + "";

        char answer = num.charAt((int) ((k - 1) % digits));
        return answer;
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
