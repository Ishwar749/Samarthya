package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class ArrayDescription {

    static long mod = 1000000007;

    public static void main(String args[]){

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        int a[] = new int[n];

        for(int i = 0; i<n; i++){
            a[i] = in.nextInt();
        }

        long dp[][] = new long[n][m+1];
    // dp[i][j] is the number of ways to make the array using the first i elements such that last number is j

        if(a[0]==0){
            for(int i =1; i<=m; i++){
                dp[0][i] = 1;
            }
        }
        else dp[0][a[0]] = 1;

        for(int i = 1; i<n; i++){

            if(a[i]==0){
                for(int j = 1; j<=m; j++){
                    long ways = dp[i-1][j]%mod;
                    if(j-1>0) ways = (ways%mod + dp[i-1][j-1]%mod)%mod;
                    if(j+1<=m) ways = (ways%mod + dp[i-1][j+1]%mod)%mod;
                    dp[i][j] = ways;
                }
            }
            else{
                long ways = dp[i-1][a[i]]%mod;
                if(a[i]-1>0) ways = (ways%mod + dp[i-1][a[i]-1]%mod)%mod;
                if(a[i]+1<=m) ways = (ways%mod + dp[i-1][a[i]+1]%mod)%mod;
                dp[i][a[i]] = ways;
            }
        }

        long ans = 0;

        for(int i =1; i<=m; i++){
            ans = (ans%mod + dp[n-1][i]%mod)%mod;
        }

        out.println(ans);
        out.close();
    }

    // FAST IO TEMPLATE STARTS HERE:
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
