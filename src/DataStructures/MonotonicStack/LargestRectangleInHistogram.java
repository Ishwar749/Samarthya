package DataStructures.MonotonicStack;

import java.util.Stack;

// Problem: https://leetcode.com/problems/largest-rectangle-in-histogram/

public class LargestRectangleInHistogram {
}

class Solution {

    static class Pair{
        int val;
        int ind;

        public Pair(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }
    }


    public int largestRectangleArea(int[] heights) {

        int n = heights.length;
        Pair[] a = new Pair[n];

        for(int i = 0; i < n; i++) a[i] = new Pair(heights[i],i);

        int[] right = nearestMinimumToRight(a);
        int[] left = nearestMinimumToLeft(a);

        int ans = 0;

        for(int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            ans = Math.max(ans, heights[i]*width);
        }

        return ans;
    }

    static int[] nearestMinimumToRight(Pair[] a) {

        int n = a.length;
        int[] ans = new int[n];
        ans[n-1] = n;

        Stack<Pair> st = new Stack<>();
        st.add(a[n-1]);

        for(int i = n - 2; i >= 0; i--) {
            while(!st.isEmpty() && st.peek().val >= a[i].val) {
                st.pop();
            }

            if(st.isEmpty()) ans[i] = n;
            else {
                ans[i] = st.peek().ind;
            }

            st.push(a[i]);
        }

        return ans;
    }

    static int[] nearestMinimumToLeft(Pair[] a) {
        int n = a.length;
        int[] ans = new int[n];
        ans[0] = -1;

        Stack<Pair> st = new Stack<>();
        st.add(a[0]);

        for(int i = 1; i < n; i++) {
            while(!st.isEmpty() && st.peek().val >= a[i].val) {
                st.pop();
            }

            if(st.isEmpty()) ans[i] = -1;
            else {
                ans[i] = st.peek().ind;
            }

            st.push(a[i]);
        }

        return ans;
    }
}


