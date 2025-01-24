package com.practice.programs.palindrome;

/**
 * Problem Statement Given a string, find the total number of palindromic
 * substrings in it. Please note we need to find the total number of substrings
 * and not subsequences.
 * 
 * Example 1:
 * 
 * Input: "abdbca" Output: 7 Explanation: Here are the palindromic substrings,
 * "a", "b", "d", "b", "c", "a", "bdb".
 * 
 * @author harshal
 *
 */
public class PalindromicSubstringsCount {

	public static int countPalindromicSubstrings(String s) {
		int n = s.length();
		int count = 0;

		// Create a boolean table to store the palindrome information for substrings.
		boolean[][] isPalindrome = new boolean[n][n];

		// Every single character is a palindrome by itself.
		for (int i = 0; i < n; i++) {
			isPalindrome[i][i] = true;
			count++;
		}

		// Check for palindromic substrings of length 2 or more.
		for (int length = 2; length <= n; length++) {
			for (int i = 0; i <= n - length; i++) {
				int j = i + length - 1; // Ending index of the substring.

				// Check if the substring is a palindrome by comparing its characters at both
				// ends.
				if (s.charAt(i) == s.charAt(j)) {
					// If the substring has only 2 characters or if the remaining substring is a
					// palindrome,
					// then mark it as a palindrome and increment the count.
					if (length == 2 || isPalindrome[i + 1][j - 1]) {
						isPalindrome[i][j] = true;
						count++;
					}
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		String s = "abdbca";
		int totalCount = countPalindromicSubstrings(s);
		System.out.println("Total number of palindromic substrings: " + totalCount);
	}
}
