package dynamicProgramming.introToDP.knapsackAndBasicDynamicProgramming;

import java.io.*;
import java.util.*;

public class SubsetSumKWithAddAndErase {


    public static void main(String args[]){

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int q = in.nextInt();
        int k = in.nextInt();

        int a[] = new int[q];
        int lastPos = 0;
        Stack<Integer> st = new Stack<>();
        Map<Integer,Stack<Integer>> map = new HashMap<>();
        long mod = 998244353;
        long dp[][] = new long[q][k+1];
        dp[0][0] = 1;

        for(int i = q-1; i>=0; i--){
            st.push(i);
        }

        for(int query = 0; query<q; query++){
            String s = in.next();
            int num = Integer.parseInt(in.next());
            int startPos = 0;

            if(s.equals("+")){
                startPos = lastPos;
                a[lastPos] = num;

                if(!map.containsKey(num)) map.put(num, new Stack<>());
                map.get(num).push(lastPos);

                lastPos++;
            }
            else{
                startPos = map.get(num).pop();

                for(int i = startPos+1; i<=lastPos; i++){
                    a[i-1] = a[i];
                }

                lastPos--;
            }

            for(int i = 0; i<lastPos; i++){
                System.out.print(a[i]+" ");
            }
            System.out.println();

            if(lastPos==1){
                dp[startPos][0] = 1;
                if(a[startPos]<=k) dp[startPos][a[startPos]] = 1;
            }
            else {
                for (int i = startPos; i < lastPos; i++) {
                    Arrays.fill(dp[i], 0);
                    for (int j = 0; j <= k; j++) {
                        if (i - 1 >= 0) {
                            dp[i][j] = (dp[i][j] % mod + dp[i - 1][j] % mod) % mod;
                            if (j - a[i] >= 0) dp[i][j] = (dp[i][j] % mod + dp[i - 1][j - a[i]] % mod) % mod;
                        }
                    }
                }
            }

            if(lastPos==0) out.println(0);
            else out.println(dp[lastPos-1][k]);
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
