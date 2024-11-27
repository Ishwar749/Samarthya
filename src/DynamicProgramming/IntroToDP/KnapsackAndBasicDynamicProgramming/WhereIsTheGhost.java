package DynamicProgramming.IntroToDP.KnapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class WhereIsTheGhost {

    static long mod = (long)(1e9 + 7);
    public static void main(String args[]){

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int a[] = new int[n];

        for(int i =0; i<n; i++){
            a[i] = in.nextInt();
        }

        long dp[][] = new long[n][(1<<8)+1];
        for(long e[]: dp){
            Arrays.fill(e, -1);
        }

        long ans = find(0,1<<8,a,dp);

        out.println(ans);
        out.close();
    }

    static long find(int cur, int bits, int a[], long dp[][]){

        if(cur==a.length){
            if((bits & ((1 << 9) - 1)) == ((1 << 9) - 1)) return 1;
            return 0;
        }

        int store = bits;
        if(dp[cur][store]!=-1) return  dp[cur][store];

        long ans = 0;

        ans = find(cur+1,bits,a,dp)%mod;

        if(a[cur]%7==0) bits = (bits&1);
        if(a[cur]%5==0) bits = (bits&(1<<1));

        int temp = a[cur];

        if(temp%3==0){
            if((bits&(1<<2))==0) bits = (bits&(1<<2));
            else if((bits&(1<<3))==0) bits=  (bits&(1<<3));
            temp = temp/3;
        }

        if(temp%3==0){
            if((bits&(1<<3))==0) bits=  (bits&(1<<3));
        }

        temp = a[cur];

        if(temp%2==0){
            if((bits&(1<<4))==0) bits = (bits&(1<<2));
            else if((bits&(1<<5))==0) bits=  (bits&(1<<3));
            else if((bits&(1<<6))==0) bits = (bits&(1<<2));
            temp = temp/2;
        }

        if(temp%2==0){
            if((bits&(1<<5))==0) bits=  (bits&(1<<3));
            else if((bits&(1<<6))==0) bits = (bits&(1<<2));
            temp = temp/2;
        }

        if(temp%2==0){
            if((bits&(1<<6))==0) bits = (bits&(1<<2));
        }

        ans = (ans%mod + find(cur+1,bits,a,dp)%mod)%mod;

        dp[cur][store] = ans;
        return ans;
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
