package GraphTheory.MinimumSpanningTrees;

import java.io.*;
import java.util.*;

public class PathQueries {
    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int compareTo(Edge that) {
            return Integer.compare(this.w, that.w);
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        if (n == 1) {
            for (int i = 0; i < m; i++) {
                int x = in.nextInt();
                out.print(0 + " ");
            }
            out.close();
            return;
        }
        Edge[] edges = new Edge[n - 1];

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            edges[i] = new Edge(u, v, w);
        }


        Arrays.sort(edges);
        long[] prefixSum = new long[edges.length];

        DSU queryInstance = new DSU(n);

        for (int i = 0; i < n - 1; i++) {
            long currentPairs = queryInstance.union(edges[i].u, edges[i].v);
            prefixSum[i] = currentPairs;
            if (i > 0) prefixSum[i] += prefixSum[i - 1];
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            if (x >= edges[edges.length - 1].w) {
                out.print(prefixSum[edges.length - 1] + " ");
            } else if (x < edges[0].w) {
                out.print(0 + " ");
            } else {
                int index = binarySearch(x, edges);
                out.print(prefixSum[index] + " ");
            }
        }
        out.close();
    }

    static int binarySearch(long x, Edge[] edges) {
        int low = 0;
        int high = edges.length - 1;

        int answer = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (edges[mid].w <= x) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return answer;
    }

    static class DSU {
        int[] size;
        int[] par;
        int n;

        public DSU(int n) {
            this.n = n;
            size = new int[n];
            par = new int[n];
            for (int i = 0; i < n; i++) {
                par[i] = i;
                size[i] = 1;
            }
        }

        public int findParent(int a) {
            if (a == par[a]) return a;
            return par[a] = findParent(par[a]);
        }

        public long union(int a, int b) {
            a = findParent(a);
            b = findParent(b);
            if (a == b) return -1; // This will never happen, as the given graph is a tree

            long toReturn = (long) size[a] * (long) size[b];
            if (size[a] >= size[b]) {
                size[a] += size[b];
                par[b] = a;
            } else {
                size[b] += size[a];
                par[a] = b;
            }

            return toReturn;
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
