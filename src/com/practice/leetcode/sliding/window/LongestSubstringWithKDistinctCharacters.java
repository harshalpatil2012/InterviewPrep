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
        System.out.println("longestSubstring with 4 distinct chars ::" + longestSubstring(input1, 4)); // 8

        String input2 = "araaci";
        System.out.println("longestSubstring with 2 distinct chars ::" + longestSubstring(input2, 2)); // 4
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba")); // Output: 3 ("ece")
        System.out.println(lengthOfLongestSubstringTwoDistinct("ccaabbb")); // Output: 5 ("aabbb")
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



    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Initialize variables
        Map<Character, Integer> map = new HashMap<>(); // To store the count of each character in the current window
        int maxLength = 0; // To store the maximum length of substring found
        int left = 0; // Left pointer of the sliding window

        // Traverse through the string with the right pointer
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

            // If the window contains more than two distinct characters, shrink the window from the left
            while (map.size() > 2) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            // Update the maximum length of substring
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }


}
