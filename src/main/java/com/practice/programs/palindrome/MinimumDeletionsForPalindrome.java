package com.practice.programs.palindrome;

/**
 * Minimum Deletions in a String to make it a Palindrome Problem Statement Given
 * a string, find the minimum number of characters that we can remove to make it
 * a palindrome.
 * 
 * Example 1:
 * 
 * Input: "abdbca" Output: 1 Explanation: By removing "c", we get a palindrome
 * "abdba".
 * 
 * @author harshal
 *
 */
public class MinimumDeletionsForPalindrome {

	/**
	 * In this program, the findMinimumDeletions method takes a string s as input
	 * and returns the minimum number of characters that need to be removed from the
	 * string to make it a palindrome.
	 * 
	 * The method follows these steps:
	 * 
	 * Create a dynamic programming table dp with dimensions n x n, where n is the
	 * length of the string. Initialize the diagonal of the table with 1, as the
	 * longest palindromic subsequence of a single character is 1. Fill the table in
	 * a bottom-up manner, considering substrings of increasing lengths. For each
	 * substring, if the characters at both ends are equal, increment the length of
	 * the Longest Palindromic Subsequence (LPS) by 2. If the characters at both
	 * ends are not equal, consider two cases: removing the character at the start
	 * index or removing the character at the end index. Choose the maximum LPS
	 * length among these two cases. After filling the table, the minimum number of
	 * deletions required is the difference between the length of the string and the
	 * length of its LPS. Return the minimum number of deletions.
	 * 
	 * @param s
	 * @return
	 */
	public static int findMinimumDeletions(String s) {
		int n = s.length();
		int[][] dp = new int[n][n];

		// The longest palindromic subsequence of a single character is 1.
		for (int i = 0; i < n; i++) {
			dp[i][i] = 1;
		}

		// Fill the dp table in a bottom-up manner.
		for (int start = n - 1; start >= 0; start--) {
			for (int end = start + 1; end < n; end++) {
				if (s.charAt(start) == s.charAt(end)) {
					// If the characters at both ends are equal, increment the LPS length by 2.
					dp[start][end] = dp[start + 1][end - 1] + 2;
				} else {
					// If the characters at both ends are not equal, consider two cases:
					// 1. Remove the character at the start index and find the LPS length of the
					// remaining substring.
					// 2. Remove the character at the end index and find the LPS length of the
					// remaining substring.
					// Take the maximum length among these two cases.
					dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
				}
			}
		}

		// The minimum number of deletions required is the difference between the length
		// of the string
		// and the length of its Longest Palindromic Subsequence.
		return n - dp[0][n - 1];
	}

	public static void main(String[] args) {
		String s = "abdbca";
		int minDeletions = findMinimumDeletions(s);
		System.out.println("Minimum number of deletions: " + minDeletions);
	}
}
