package GraphTheory.EulerianPathAndCycle;

import java.io.*;
import java.util.*;

public class TanyaAndPassword {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        String[] s = new String[n];
        boolean[] isSame = new boolean[n];
        Map<String, List<Integer>> first = new HashMap<>();
        Map<String, List<Integer>> last = new HashMap<>();

        for (int i = 0; i < n; i++) {
            s[i] = in.next();
            if (s[i].charAt(1) == s[i].charAt(0) && s[i].charAt(2) == s[i].charAt(1)) isSame[i] = true;

            String firstTwo = s[i].substring(0, 2);
            first.putIfAbsent(firstTwo, new ArrayList<>());
            first.get(firstTwo).add(i);

            String lastTwo = s[i].substring(1);
            last.putIfAbsent(lastTwo, new ArrayList<>());
            last.get(lastTwo).add(i);
        }

        solve(n, s, isSame, first, last, out);
        out.close();
    }

    static void solve(int n, String[] s, boolean[] isSame, Map<String, List<Integer>> first, Map<String, List<Integer>> last, PrintWriter output) {

        int[] in = new int[n];
        int[] out = new int[n];
        calculateDegrees(n, s, isSame, first, last, in, out);

        if (!hasEulerianPath(n, in, out)) {
            output.println("NO");
            return;
        }

        boolean[] visited = new boolean[n];
        List<Integer> path = new ArrayList<>();

        int start = getStartNode(n, in, out);
        visited[start] = true;
        dfs(start, s, visited, first, path);

        for (int e : path) output.print(s[e] + " ");
        output.println();
        if (path.size() != n) output.println("NO");
        else {
            output.println("YES");
            output.print(s[path.get(0)]);
            for (int i = 1; i < path.size(); i++) {
                output.print(s[path.get(i)].charAt(2));
            }
        }
    }

    static void dfs(int cur, String[] s, boolean[] visited, Map<String, List<Integer>> first, List<Integer> path) {
        path.add(cur);
        String lastTwo = s[cur].substring(1);

        for (int nei : first.get(lastTwo)) {
            if (!visited[nei]) {
                visited[nei] = true;
                dfs(nei, s, visited, first, path);
            }
        }
    }

    static int getStartNode(int n, int[] in, int[] out) {
        int start = 0;

        for (int i = 0; i < n; i++) {
            if (out[i] - in[i] == 1) return i;
            if (out[i] > 0) start = i;
        }

        return start;
    }

    static boolean hasEulerianPath(int n, int[] in, int[] out) {
        int startNodes = 0;
        int endNodes = 0;

        for (int i = 0; i < n; i++) {
            if (Math.abs(out[i] - in[i]) > 1) return false;
            if (out[i] - in[i] == 1) startNodes++;
            if (in[i] - out[i] == 1) endNodes++;
        }

        return (startNodes == 0 && endNodes == 0) || (startNodes == 1 && endNodes == 1);
    }

    static void calculateDegrees(int n, String[] s, boolean[] isSame, Map<String, List<Integer>> first, Map<String, List<Integer>> last, int[] in, int[] out) {
        for (int i = 0; i < n; i++) {
            String firstTwo = s[i].substring(0, 2);
            if (last.containsKey(firstTwo)) {
                in[i] = last.get(firstTwo).size();
                if (isSame[i]) in[i]--;
            }

            String lastTwo = s[i].substring(1);
            if (first.containsKey(lastTwo)) {
                out[i] = first.get(lastTwo).size();
                if (isSame[i]) out[i]--;
            }
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
