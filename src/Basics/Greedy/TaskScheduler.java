package Basics.Greedy;

import java.io.*;
import java.util.*;

// Problem: https://leetcode.com/problems/task-scheduler/

public class TaskScheduler {
    class Solution {
        public int leastInterval(char[] tasks, int n) {
            Map<Character, Integer> freq = new HashMap<>();
            Map<Character, Integer> lastRun = new HashMap<>();

            for (char c : tasks) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
                lastRun.put(c, -1000);
            }

            int m = 1;

            while (!freq.keySet().isEmpty()) {
                char fit = 'x';
                char last = 'x';

                for (char key : freq.keySet()) {
                    if (canFit(key, m, n, lastRun)) {
                        if (fit == 'x' || freq.get(key) > freq.get(fit)) fit = key;
                    }

                    if (last == 'x' || lastRun.get(last) > lastRun.get(key)) last = key;
                }

                if (fit == 'x') {
                    remove(last, freq);
                    m = lastRun.get(last) + n + 1;
                    lastRun.put(last, lastRun.get(last) + n + 1);
                    m++;
                } else {
                    remove(fit, freq);
                    lastRun.put(fit, m);
                    m++;
                }
            }

            return m - 1;
        }

        private boolean canFit(char fit, int m, int n, Map<Character, Integer> lastRun) {
            int l = lastRun.get(fit);
            int diff = m - l;
            return diff > n;
        }

        private void remove(char c, Map<Character, Integer> freq) {
            freq.put(c, freq.get(c) - 1);
            if (freq.get(c) == 0) freq.remove(c);
        }
    }
}
