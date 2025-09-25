package Basics.TwoPointers;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1141

public class Playlist {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) a[i] = in.nextInt();

        Map<Integer, Integer> map = new HashMap<>();
        int l = 0;
        int r = 0;
        int windowSize = 0;

        while (r < n) {
            if (map.containsKey(a[r]) && map.get(a[r]) >= l) {
                l = map.get(a[r]) + 1;
            }
            map.put(a[r], r);
            windowSize = Math.max(windowSize, (r - l) + 1);
            r++;
        }

        out.println(windowSize);
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
