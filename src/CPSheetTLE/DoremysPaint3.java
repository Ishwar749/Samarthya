package CPSheetTLE;

import java.io.*;
import java.util.*;

public class DoremysPaint3 {
    public static void main(String[] args) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tests = in.nextInt();

        while (tests-- > 0) {
            int n = in.nextInt();
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                int key = in.nextInt();
                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            if (map.keySet().size() > 2) {
                out.println("NO");
            } else {
                if (map.keySet().size() == 1) {
                    out.println("YES");
                } else {
                    int first = -1;
                    int second = -1;

                    for (int key : map.keySet()) {
                        if (first == -1) first = map.get(key);
                        else second = map.get(key);
                    }

                    int diff = Math.abs(first - second);
                    if (diff > 1) {
                        out.println("NO");
                    } else {
                        out.println("YES");
                    }
                }
            }
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
