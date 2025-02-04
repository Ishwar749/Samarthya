package DynamicProgramming.DPOptimizations;

import java.io.*;
import java.util.*;

// Problem: https://cses.fi/problemset/task/1140
public class Projects {

    static class Interval {
        int start;
        int end;
        int reward;

        public Interval(int start, int end, int reward) {
            this.start = start;
            this.end = end;
            this.reward = reward;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        Interval[] intervals = new Interval[n];

        for (int i = 0; i < n; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            int reward = in.nextInt();
            intervals[i] = new Interval(start, end, reward);
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a.end, b.end));

        TreeMap<Integer, Integer> lastIndex = new TreeMap<>();
        for (int i = 0; i < n; i++) lastIndex.put(intervals[i].end, i);

        long[] maxProfitTillNow = new long[n];
        long[] curProfit = new long[n];
        maxProfitTillNow[0] = intervals[0].reward;
        curProfit[0] = intervals[0].reward;

        for (int i = 1; i < n; i++) {
            Integer key = lastIndex.floorKey(intervals[i].start - 1);
            if (key == null) {
                curProfit[i] = intervals[i].reward;
            } else {
                int index = lastIndex.get(key);
                curProfit[i] = intervals[i].reward + maxProfitTillNow[index];
            }
            maxProfitTillNow[i] = Math.max(maxProfitTillNow[i - 1], curProfit[i]);
        }

        long answer = maxProfitTillNow[n - 1];
        out.println(answer);
        out.close();
    }
}

class FastScanner {
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
