package com.practice.leetcode.sliding.window;

import java.util.HashMap;

/**
 * 76. Minimum Window Substring : https://leetcode.com/problems/minimum-window-substring/description/
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring
 * of s such that every character in t (including duplicates) is included in the window. If there is no
 * such substring, return the empty string "".The testcases will be generated such that the answer is unique.
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 */
import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public static String minWindow1(String s, String t) {
        if (s == null || t == null || s.length()
                == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        HashMap<Character, Integer> tFreqMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tFreqMap.put(c, tFreqMap.getOrDefault(c, 0) + 1);
        }

        int left = 0, minLen = Integer.MAX_VALUE, matched = 0;
        String minWindow = "";
        HashMap<Character, Integer> windowFreqMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            windowFreqMap.put(rightChar, windowFreqMap.getOrDefault(rightChar, 0) + 1);

            if (tFreqMap.containsKey(rightChar) && windowFreqMap.get(rightChar) <= tFreqMap.get(rightChar)) {
                matched++;
            }

            while (matched == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minWindow = s.substring(left, right + 1); // +1 to include the right character
                }

                char leftChar = s.charAt(left);
                windowFreqMap.put(leftChar, windowFreqMap.get(leftChar) - 1);

                if (tFreqMap.containsKey(leftChar) && windowFreqMap.get(leftChar) < tFreqMap.get(leftChar)) {
                    matched--;
                }
                left++;
            }
        }
        return minWindow;
    }


    public static void main(String[] args) {
        String s1 = "ADOBECODEBANC", t1 = "ABC";
        System.out.println("Minimum window for s1, t1: " + minWindow(s1, t1)); // Output: "BANC"

        String s2 = "atbc", t2 = "ab";
        System.out.println("Minimum window for s2, t2: " + minWindow(s2, t2)); // Output: "atb"

        String s3 = "a", t3 = "aa";
        System.out.println("Minimum window for s3, t3: " + minWindow(s3, t3)); // Output: ""
    }

    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        int left = 0, minLen = Integer.MAX_VALUE, matched = 0;
        String minWindow = "";
        Map<Character, Integer> windowFreqMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            windowFreqMap.put(rightChar, windowFreqMap.getOrDefault(rightChar, 0) + 1);
            if (tMap.containsKey(rightChar) && windowFreqMap.get(rightChar) <= tMap.get(rightChar)) {
                matched++;
            }

            while (matched == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minWindow = s.substring(left, right + 1);
                }
                char leftChar = s.charAt(left);
                windowFreqMap.put(leftChar, windowFreqMap.get(leftChar) - 1);
                if (tMap.containsKey(leftChar) && windowFreqMap.get(leftChar) < tMap.get(leftChar)) {
                    matched--;
                }
                left++;
            }
        }
        return minWindow;
    }
}
