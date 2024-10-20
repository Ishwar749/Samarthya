package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

// Problem: https://atcoder.jp/contests/dp/tasks/dp_k
public class Stones {

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int K = in.nextInt();
        int a[] = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }

        boolean myTurn[] = new boolean[K + 1]; // if its my turn and there are i stones, do I win or not.
        boolean hisTurn[] = new boolean[K + 1];// if its his turn and there are i stones, do I win or not.
        Arrays.fill(hisTurn, true); // Assume the worst i.e if he cant make any move (i-e is always<0) then he definitely loses.


        for (int i = 1; i <= K; i++) {
            for (int e : a) {
                if (i - e >= 0) {
                    myTurn[i] = myTurn[i] || hisTurn[i - e];
                    hisTurn[i] = (hisTurn[i] & myTurn[i - e]);
                }
            }
        }

        if (myTurn[K]) out.println("First");
        else out.println("Second");

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
