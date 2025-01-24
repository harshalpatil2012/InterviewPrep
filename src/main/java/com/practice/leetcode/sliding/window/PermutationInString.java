package com.practice.leetcode.sliding.window;

/**
 * 567. Permutation in String : https://leetcode.com/problems/permutation-in-string/description/
 * Medium
 * Topics
 * Companies
 * Hint
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * In other words, return true if one of s1's permutations is the substring of s2.
 * <p>
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 */

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {

    public static void main(String[] args) {
        System.out.println("Example 1: " + checkInclusion("ab", "eidbaooo")); // true
        System.out.println("Example 2: " + checkInclusion("ab", "eidboaoo")); // false
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false; // Permutation impossible if s1 is longer
        }

        Map<Character, Integer> s1Freq = new HashMap<>(); // Frequency map for s1
        for (char c : s1.toCharArray()) {
            s1Freq.put(c, s1Freq.getOrDefault(c, 0) + 1);
        }

        int matched = 0; // Count of matched characters
        Map<Character, Integer> windowFreq = new HashMap<>();

        for (int right = 0; right < s2.length(); right++) {
            char rightChar = s2.charAt(right); // Expand window
            windowFreq.put(rightChar, windowFreq.getOrDefault(rightChar, 0) + 1);

            if (s1Freq.containsKey(rightChar) && windowFreq.get(rightChar).equals(s1Freq.get(rightChar))) {
                matched++; // Increment if character matches frequency in s1
            }
            if (right >= s1.length()) { // Shrink window if it gets too big
                char leftChar = s2.charAt(right - s1.length());
                if (s1Freq.containsKey(leftChar) && windowFreq.get(leftChar).equals(s1Freq.get(leftChar))) {
                    matched--;
                }
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);
                if (windowFreq.get(leftChar) == 0) {
                    windowFreq.remove(leftChar);
                }
            }
            if (matched == s1Freq.size()) { // All characters matched
                return true;
            }
        }
        return false;
    }

}

