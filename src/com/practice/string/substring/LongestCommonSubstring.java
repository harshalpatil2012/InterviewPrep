package com.practice.string.substring;

import java.util.HashSet;
import java.util.Set;

/**
 * Longest Common Substring Problem Statement Given two strings 's1' and 's2',
 * find the length of the longest substring which is common in both the strings.
 * 
 * Example 1:
 * 
 * Input: s1 = "abdca" s2 = "cbda" Output: 2 Explanation: The longest common
 * substring is "bd".
 * 
 * @author harshal
 *
 */
public class LongestCommonSubstring {

	public static int findLongestCommonSubstring(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] dp = new int[m + 1][n + 1];
		int maxLength = 0;

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					maxLength = Math.max(maxLength, dp[i][j]);
				}
			}
		}

		return maxLength;
	}

	public static void main(String[] args) {
		String s1 = "abdca";
		String s2 = "cbda";
		int longestCommonSubstring = findLongestCommonSubstring(s1, s2);
		System.out.println("Length of longest common substring: " + longestCommonSubstring);

		//without any input
		String s = "abcabcbb";
		int length = lengthOfLongestSubstring(s);
		System.out.println("Length of the longest substring in \"" + s + "\": " + length);

	}

	public static int findLongestCommonSubstringOptimized(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] dp = new int[m + 1][n + 1];
		int maxLength = 0;

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					maxLength = Math.max(maxLength, dp[i][j]);
				} else {
					dp[i][j] = 0; // Reset to 0 if characters don't match
				}
			}
		}

		return maxLength;
	}

	public static int lengthOfLongestSubstring(String s) {
		int maxLength = 0;
		int left = 0;
		int right = 0;
		Set<Character> uniqueChars = new HashSet<>();

		while (right < s.length()) {
			if (!uniqueChars.contains(s.charAt(right))) {
				uniqueChars.add(s.charAt(right));
				maxLength = Math.max(maxLength, right - left + 1);
				right++;
			} else {
				uniqueChars.remove(s.charAt(left));
				left++;
			}
		}

		return maxLength;
	}

}
