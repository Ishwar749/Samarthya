package Basics.TwoPointers;

import java.io.*;
import java.util.*;

//Problem: https://cses.fi/problemset/task/2428

public class DistinctValuesSubarraysII {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) a[i] = in.nextInt();

        Map<Integer, Integer> map = new HashMap<>();
        int l = 0;
        int r = 0;
        long ans = 0;

        while (r < n) {
            add(map, a[r]);

            while (map.keySet().size() > k) {
                remove(map, a[l]);
                l++;
            }
            ans += ((r - l) + 1);
            r++;
        }

        out.println(ans);
        out.close();
    }

    static void add(Map<Integer, Integer> map, int key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    static void remove(Map<Integer, Integer> map, int key) {
        map.put(key, map.get(key) - 1);
        if (map.get(key) == 0) map.remove(key);
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
