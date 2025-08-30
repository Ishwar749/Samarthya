package DataStructures.MonotonicStack;

import java.io.*;
import java.util.*;

// Problem: https://leetcode.com/problems/next-greater-element-i/

public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int n1 = nums1.length;
        int n2 = nums2.length;

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> answer = new HashMap<>();

        for (int i = n2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i])
                stack.pop();

            if (stack.isEmpty()) answer.put(nums2[i], -1);
            else answer.put(nums2[i], stack.peek());

            stack.push(nums2[i]);
        }

        int[] result = new int[n1];
        for (int i = 0; i < n1; i++) {
            result[i] = answer.get(nums1[i]);
        }

        return result;
    }
}
