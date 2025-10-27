package Basics.Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Problem: https://leetcode.com/problems/letter-combinations-of-a-phone-number/

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        List<String> result = letterCombinations("2349");

        for (String s : result) System.out.println(s);
    }

    static List<String> letterCombinations(String digits) {
        Map<Character, List<Character>> map = constructMap();

        int n = digits.length();
        List<String>[] arr = new ArrayList[n];
        for (int i = 0; i < n; i++) arr[i] = new ArrayList<>();

        for (Character c : map.get(digits.charAt(0))) {
            arr[0].add(c + "");
        }

        for (int i = 1; i < n; i++) {
            for (Character c : map.get(digits.charAt(i))) {
                for (String s : arr[i - 1]) {
                    StringBuilder toAdd = new StringBuilder();
                    toAdd.append(s);
                    toAdd.append(c);
                    arr[i].add(toAdd.toString());
                }
            }
        }

        return arr[n - 1];
    }

    // Alternate Solution
    static void generateCombinations(int index, String digits, StringBuilder cur, Map<Character, List<Character>> map, List<String> result) {
        if (index == digits.length()) {
            result.add(cur.toString());
            return;
        }

        for (Character c : map.get(digits.charAt(index))) {
            cur.append(c);
            generateCombinations(index + 1, digits, cur, map, result);
            cur.deleteCharAt(index);
        }
    }

    static private Map<Character, List<Character>> constructMap() {
        Map<Character, List<Character>> map = new HashMap<>();
        for (char c = '2'; c <= '9'; c++) map.put(c, new ArrayList<>());

        char currentChar = 'a';
        char digit = '2';

        while (digit <= '9') {
            int loop = 3;
            if (digit == '7' || digit == '9') loop = 4;

            for (int i = 0; i < loop; i++) {
                map.get(digit).add(currentChar);
                currentChar++;
            }
            digit++;
        }

        return map;
    }
}
