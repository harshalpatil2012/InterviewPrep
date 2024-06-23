package com.practice.leetcode.sliding.window;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Substring with K Distinct Characters (medium)
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * Given a string, find the length of the longest substring in it with no more than K distinct
 * characters.
 * You can assume that K is less than or equal to the length of the given string.
 */
public class LongestSubstringWithKDistinctCharacters {

    public static void main(String[] args) {

        String input1 = "abcabcbb";
        System.out.println("longestSubstring with 4 distinct chars ::" + longestSubstring(input1, 4));

        String input2 = "araaci";
        System.out.println("longestSubstring with 2 distinct chars ::" + longestSubstring(input2, 2));
    }

    public static int longestSubstring(String inputStr, int k) {

        if (inputStr == null || inputStr.isEmpty() || k == 0) {
            return 0;
        }
        Map<Character, Integer> charMap = new HashMap<>();
        int maxLen = 0;
        int windowStart = 0;
        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);

            while (charMap.size() > k) {
                char leftChar = inputStr.charAt(windowStart);
                charMap.put(leftChar, charMap.getOrDefault(leftChar, 0) - 1);
                if (charMap.get(leftChar) == 0) {
                    charMap.remove(leftChar);
                }
                windowStart++;
            }
            maxLen = Math.max(maxLen, i - windowStart + 1);
        }
        return maxLen;
    }
}
