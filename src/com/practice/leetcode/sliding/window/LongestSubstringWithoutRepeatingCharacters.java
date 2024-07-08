package com.practice.leetcode.sliding.window;


import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring
 * without repeating characters.
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String str1 = "abcabcbb";
        String str2 = "bbbbb";
        String str3 = "pwwkew";

        System.out.println("Length of longest substring: " + lengthOfLongestSubstring(str1)); // Output: 3 ("abc")
        System.out.println("Length of longest substring: " + lengthOfLongestSubstring(str2)); // Output: 1 ("b")
        System.out.println("Length of longest substring: " + lengthOfLongestSubstring(str3)); // Output: 3 ("wke")
    }

    private static int lengthOfLongestSubstring(String str1) {

        int maxLength = 0;
        int start = 0;
        Map<Character, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            if (indexMap.containsKey(ch)) {
                start = Math.max(start, indexMap.get(ch) + 1);
            }
            indexMap.put(ch, i);
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }
}
