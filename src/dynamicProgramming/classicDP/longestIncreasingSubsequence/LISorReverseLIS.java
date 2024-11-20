package dynamicProgramming.classicDP.longestIncreasingSubsequence;

import java.io.*;
import java.util.*;

// Problem: https://codeforces.com/problemset/problem/1682/C

public class LISorReverseLIS {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int N = in.nextInt();
            int[] nums = new int[N];
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < N; i++) {
                nums[i] = in.nextInt();
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }

            int singles = 0;
            int multiples = 0;

            for (int key : map.keySet()) {
                if (map.get(key) == 1) singles++;
                else multiples++;
            }

            int answer = multiples + ((singles + 1) / 2);
            out.println(answer);
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
