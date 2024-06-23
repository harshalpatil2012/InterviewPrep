package com.practice.leetcode.sliding.window;

import java.util.HashMap;
import java.util.Map;

/**
 * 424. Longest Repeating Character Replacement
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 * Example 1:
 * <p>
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 * <p>
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 */
public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        int maxLen = 0;
        int maxFreq = 0;
        int left = 0;

        for (int i = 0; i < s.length(); i++) {
            char rightChar = s.charAt(i);
            charCountMap.put(rightChar, charCountMap.getOrDefault(rightChar, 0) + 1);
            maxFreq = Math.max(maxFreq, charCountMap.get(rightChar));

            // If the window size minus the count of the most frequent character is greater than k, shrink the window
            while (i - left + 1 - maxFreq > k) {
                char leftChar = s.charAt(left);
                charCountMap.put(leftChar, charCountMap.get(leftChar) - 1);
                left++;
            }

            maxLen = Math.max(maxLen, i - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println(characterReplacement(s1, k1)); // Output: 4

        String s2 = "AABABBA";
        int k2 = 1;
        System.out.println(characterReplacement(s2, k2)); // Output: 4
    }
}

