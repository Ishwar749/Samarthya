package Basics.BinarySearch;

import java.io.*;
import java.util.*;

//Problem: https://codeforces.com/edu/course/2/lesson/6/3/practice/contest/285083/problem/A
public class GetTogether {

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int[] pos = new int[n];
        int[] speeds = new int[n];

        for (int i = 0; i < n; i++) {
            pos[i] = in.nextInt();
            speeds[i] = in.nextInt();
        }

        double answer = minimumTimeToMeet(pos, speeds);
        out.println(answer);
        out.close();
    }

    static double minimumTimeToMeet(int[] positions, int[] speeds) {
        double left = 0, right = 2e9;

        // Binary search for minimum time
        for (int i = 0; i < 100; i++) {
            double mid = left + (right - left) / 2;

            if (canAllMeetInTime(positions, speeds, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    static boolean canAllMeetInTime(int[] pos, int[] speed, double time) {
        double leftBound = Double.NEGATIVE_INFINITY;
        double rightBound = Double.POSITIVE_INFINITY;

        for (int i = 0; i < pos.length; i++) {
            double personLeft = pos[i] - speed[i] * time;
            double personRight = pos[i] + speed[i] * time;

            leftBound = Math.max(leftBound, personLeft);
            rightBound = Math.min(rightBound, personRight);

            // No intersection exists
            if (leftBound > rightBound) return false;
        }
        return true;
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
