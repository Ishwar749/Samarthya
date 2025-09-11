package Basics.SlidingWindowTechnique;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1076/

public class SlidingWindowMedian {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();

        int[] answer = solve(n, k, a);
        for (int e : answer) out.print(e + " ");
        out.close();
    }

    static int[] solve(int n, int k, int[] a) {
        TreeMap<Integer, Integer> left = new TreeMap();
        TreeMap<Integer, Integer> right = new TreeMap();

        int sizeOfLeft = k;

        for (int i = 0; i < k; i++) {
            insert(left, a[i]);
        }

        sizeOfLeft = fillRight(k, sizeOfLeft, left, right);

        int[] answer = new int[(n - k) + 1];
        answer[0] = getMedian(left);

        for (int i = k; i < n; i++) {
            int toErase = a[i - k];

            if (toErase <= answer[i - k]) {
                erase(left, toErase);
                sizeOfLeft--;
            } else {
                erase(right, toErase);
            }

            if (a[i] >= answer[i - k]) insert(right, a[i]);
            else {
                insert(left, a[i]);
                sizeOfLeft++;
            }

            sizeOfLeft = fillRight(k, sizeOfLeft, left, right);

            sizeOfLeft = fillLeft(k, sizeOfLeft, left, right);

            answer[(i - k) + 1] = getMedian(left);
        }

        return answer;
    }

    private static int fillRight(int k, int sizeOfLeft, TreeMap<Integer, Integer> left, TreeMap<Integer, Integer> right) {
        while (sizeOfLeft > (k + 1) / 2) {
            moveOneToRight(left, right);
            sizeOfLeft--;
        }
        return sizeOfLeft;
    }

    private static int fillLeft(int k, int sizeOfLeft, TreeMap<Integer, Integer> left, TreeMap<Integer, Integer> right) {
        while (sizeOfLeft < (k + 1) / 2) {
            moveOneToLeft(left, right);
            sizeOfLeft++;
        }
        return sizeOfLeft;
    }

    static int getMedian(TreeMap<Integer, Integer> left) {
        return left.floorKey(Integer.MAX_VALUE);
    }

    static void moveOneToRight(TreeMap<Integer, Integer> left, TreeMap<Integer, Integer> right) {
        Integer keyToMove = left.floorKey(Integer.MAX_VALUE);
        insert(right, keyToMove);
        erase(left, keyToMove);
    }

    static void moveOneToLeft(TreeMap<Integer, Integer> left, TreeMap<Integer, Integer> right) {
        Integer keyToMove = right.ceilingKey(Integer.MIN_VALUE);
        insert(left, keyToMove);
        erase(right, keyToMove);
    }

    static void insert(TreeMap<Integer, Integer> map, int key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    static void erase(TreeMap<Integer, Integer> map, int key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) - 1);
            if (map.get(key) == 0) map.remove(key);
        }
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
