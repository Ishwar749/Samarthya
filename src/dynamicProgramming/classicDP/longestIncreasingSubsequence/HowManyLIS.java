package dynamicProgramming.classicDP.longestIncreasingSubsequence;

import java.io.*;
import java.util.*;

// Problem: https://www.spoj.com/problems/HMLIS/
// Approach: https://www.youtube.com/watch?v=QbwvIY0ri28

public class HowManyLIS {

    static class Pair {
        int val;
        int ind;

        public Pair(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }
    }

    static class Node {
        int maxLength; // maximum length of LIS within the range covered by this node in segment tree
        int countOfWays; // the number of ways to form the maxLength LIS in the range covered

        public Node(int maxLength, int countOfWays) {
            this.maxLength = maxLength;
            this.countOfWays = countOfWays;
        }
    }

    static Node[] segmentTree;
    static int MOD = (int) (1e9 + 7);

    public static void main(String args[]) {

        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) nums[i] = in.nextInt();

        Node answer = findHowManyLIS(nums);
        out.println(answer.maxLength + " " + answer.countOfWays);
        out.close();
    }

    static Node findHowManyLIS(int[] nums) {

        int length = nums.length;
        segmentTree = new Node[(4 * length) + 1];
        int[] positionInSortedArray = new int[length];
        Map<Integer, Integer> firstOccurrence = getFirstOccurrence(nums, positionInSortedArray);

        for (int i = 0; i < length; i++) {
            int index = firstOccurrence.get(nums[i]);
            Node currentLIS = new Node(1, 1);

            if (index > 0) {
                Node previousLIS = query(1, 0, length - 1, 0, index - 1);
                currentLIS = new Node((previousLIS.maxLength + 1) % MOD, previousLIS.countOfWays);
            }
            update(1, 0, length - 1, positionInSortedArray[i], currentLIS);
        }

        return query(1, 0, length - 1, 0, length - 1);
    }

    static Node query(int vertex, int rangeStart, int rangeEnd, int queryStart, int queryEnd) {
        if (queryStart > queryEnd)
            return new Node(0, 1);
        if (rangeStart == queryStart && rangeEnd == queryEnd)
            return segmentTree[vertex];

        int rangeMiddle = rangeStart + (rangeEnd - rangeStart) / 2;

        Node left = query(vertex * 2, rangeStart, rangeMiddle, queryStart, Math.min(rangeMiddle, queryEnd));
        Node right = query((vertex * 2) + 1, rangeMiddle + 1, rangeEnd, Math.max(rangeMiddle + 1, queryStart), queryEnd);

        return merge(left, right);
    }

    static void update(int vertex, int rangeStart, int rangeEnd, int position, Node newNode) {
        if (rangeStart == rangeEnd)
            segmentTree[vertex] = newNode;
        else {
            int rangeMiddle = rangeStart + (rangeEnd - rangeStart) / 2;

            if (position <= rangeMiddle)
                update(vertex * 2, rangeStart, rangeMiddle, position, newNode);
            else
                update((vertex * 2) + 1, rangeMiddle + 1, rangeEnd, position, newNode);

            segmentTree[vertex] = merge(segmentTree[vertex * 2], segmentTree[(vertex * 2) + 1]);
        }
    }

    static Node merge(Node left, Node right) {

        if (left == null) left = new Node(0, 1);
        if (right == null) right = new Node(0, 1);

        if (left.maxLength > right.maxLength)
            return new Node(left.maxLength, left.countOfWays);
        else if (right.maxLength > left.maxLength)
            return new Node(right.maxLength, right.countOfWays);
        else {
            if (left.maxLength == 0) {
                return new Node(0, 1);
            }
            return new Node(left.maxLength, (left.countOfWays + right.countOfWays) % MOD);
        }
    }

    static Map<Integer, Integer> getFirstOccurrence(int[] nums, int[] positionInSortedArray) {

        int length = nums.length;
        Pair[] sortedArray = new Pair[length];
        Map<Integer, Integer> firstOccurrence = new HashMap<>();

        for (int i = 0; i < length; i++) sortedArray[i] = new Pair(nums[i], i);

        Arrays.sort(sortedArray, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return Integer.compare(a.val, b.val);
            }
        });

        for (int i = 0; i < length; i++) {
            if (!firstOccurrence.containsKey(sortedArray[i].val)) {
                firstOccurrence.put(sortedArray[i].val, i);
            }
            positionInSortedArray[sortedArray[i].ind] = i;
        }

        return firstOccurrence;
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
