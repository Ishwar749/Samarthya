package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class RoundSubset {

    static int INT_MIN = -(int)1e9;
    public static void main(String args[]){

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int K = in.nextInt();

        long a[] = new long[n];
        for(int i = 0; i<n; i++) a[i] = in.nextLong();

        int [] pow2s = new int[n], pow5s = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int two = 0, five = 0;
            while (a[i] % 2 == 0) { two++; a[i] /= 2; }
            while (a[i] % 5 == 0) { five++; a[i] /= 5; }
            pow2s[i] = two; pow5s[i] = five;
            sum += five;
        }

        int dp1[][] = new int[K+1][sum+1];
        int dp2[][] = new int[K+1][sum+1];

        for(int e[]: dp1) Arrays.fill(e, INT_MIN);
        for(int e[]: dp2) Arrays.fill(e, INT_MIN);
        dp1[0][0] = 0;

        for(int i = 0; i<n; i++){
            for(int k = 0; k<=Math.min(K,i+1); k++){
                for(int fives = 0; fives<=sum; fives++){
                    dp2[k][fives] = dp1[k][fives];

                    if(k>0 && fives-pow5s[i]>=0){
                        dp2[k][fives] = Math.max(dp2[k][fives], dp1[k-1][fives-pow5s[i]]+pow2s[i]);
                    }
                }
            }

            for(int k = 0; k<=K; k++){
                for(int fives = 0; fives<=sum; fives++){
                    dp1[k][fives] = dp2[k][fives];
                }
            }

            for(int e[]: dp2) Arrays.fill(e,INT_MIN);
        }

        int ans = 0;

        for(int fives = 0; fives<=sum; fives++){
            ans = Math.max(ans, Math.min(fives,dp1[K][fives]));
        }

        out.println(ans);
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
