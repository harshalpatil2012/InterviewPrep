package com.practice.programs.palindrome;

/**
 * Given a string, find the length of its Longest Palindromic Substring (LPS).
 * In a palindromic string, elements read the same backward and forward.
 * 
 * Example 1:
 * 
 * Input: "abdbca" Output: 3 Explanation: LPS is "bdb".
 * 
 * @author harshal
 *
 */

public class LongestPalindromicSubstring {

	/**
	 * In this program, the findLPSLength method takes a string s as input and
	 * returns the length of the Longest Palindromic Substring (LPS) in that string.
	 * 
	 * The method follows these steps:
	 * 
	 * Create a boolean table isPalindrome to store the palindrome information for
	 * substrings. The cell isPalindrome[i][j] will be true if the substring from
	 * index i to index j is a palindrome. Initialize the table for single
	 * characters (length 1 substrings) as palindromes. Iterate over the string to
	 * check for palindromic substrings of length 2 or more. For each length,
	 * iterate over the starting index i from 0 to n - length (where n is the length
	 * of the string). Calculate the ending index j based on the starting index i
	 * and the length of the substring. Check if the substring from index i to index
	 * j is a palindrome by comparing the characters at both ends. If the substring
	 * has only 2 characters or if the remaining substring is a palindrome, mark it
	 * as a palindrome and update the maximum length (maxLength). After iterating
	 * over all possible substrings, return the maximum length of the palindromic
	 * substring (maxLength). The main method demonstrates the usage of the
	 * findLPSLength method by finding the length of the Longest Palindromic
	 * Substring for the given input string and prints the result.
	 * 
	 * @param s
	 * @return
	 */
	public static int findLPSLength(String s) {
		int n = s.length();

		// Create a boolean table to store the palindrome information for substrings.
		boolean[][] isPalindrome = new boolean[n][n];

		// Initialize the table for single characters (length 1 substrings) as
		// palindromes.
		for (int i = 0; i < n; i++) {
			isPalindrome[i][i] = true;
		}

		int maxLength = 1; // Length of the longest palindromic substring found so far.

		// Check for palindromic substrings of length 2 or more.
		for (int length = 2; length <= n; length++) {
			for (int i = 0; i <= n - length; i++) {
				int j = i + length - 1; // Ending index of the substring.

				// Check if the substring is a palindrome by comparing its characters at both
				// ends.
				if (s.charAt(i) == s.charAt(j)) {
					// If the substring has only 2 characters or if the remaining substring is a
					// palindrome,
					// then mark it as a palindrome and update the maximum length.
					if (length == 2 || isPalindrome[i + 1][j - 1]) {
						isPalindrome[i][j] = true;
						maxLength = Math.max(maxLength, length);
					}
				}
			}
		}

		return maxLength;
	}

	public static void main(String[] args) {
		String s = "abdbca";
		int lpsLength = findLPSLength(s);
		System.out.println("Length of the Longest Palindromic Substring: " + lpsLength);
	}

	/**
	 * To optimize the solution for finding the length of the Longest Palindromic
	 * Substring (LPS), you can use a technique called "Expand Around Center." This
	 * approach eliminates the need for creating a boolean table and reduces the
	 * time and space complexity. Here's an optimized Java program that implements
	 * this method:
	 * 
	 * @param s
	 * @return
	 */
	/**
	 * In this optimized program, the findLPSLength method and the
	 * expandAroundCenter method are used.
	 * 
	 * The findLPSLength method follows these steps:
	 * 
	 * Initialize the maximum length (maxLength) to 1 and the starting index (start)
	 * to 0. Iterate over each character in the string. For each character, expand
	 * around the center by calling the expandAroundCenter method twice: Once for
	 * odd-length palindromes, with the center at the current character. Once for
	 * even-length palindromes, with the center between the current character and
	 * the next character. Compare the lengths of the palindromes obtained from the
	 * two calls and find the maximum length (currLength). If the current palindrome
	 * is longer than the previous longest, update the maximum length and the
	 * starting index. After iterating through all characters, return the maximum
	 * length of the palindromic substring (maxLength). The expandAroundCenter
	 * method expands around the center indices left and right until the characters
	 * at those indices are not equal or the boundaries of the string are reached.
	 * It returns the length of the palindrome (right - left - 1), excluding the
	 * additional increments and decrements.
	 * 
	 */
	public static int findLPSLengthOptimized(String s) {
		int maxLength = 1; // Length of the longest palindromic substring found so far.
		int start = 0; // Starting index of the longest palindromic substring.

		for (int i = 0; i < s.length(); i++) {
			// Find the length of the palindrome by expanding around the center.
			int length1 = expandAroundCenter(s, i, i); // For odd-length palindromes.
			int length2 = expandAroundCenter(s, i, i + 1); // For even-length palindromes.

			int currLength = Math.max(length1, length2);

			// If the current palindrome is longer than the previous longest, update the
			// maximum length and starting index.
			if (currLength > maxLength) {
				maxLength = currLength;
				start = i - (currLength - 1) / 2;
			}
		}

		return maxLength;
	}

	private static int expandAroundCenter(String s, int left, int right) {
		// Expand around the center until the characters at left and right indices are
		// not equal or the boundaries are reached.
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}

		// Return the length of the palindrome (right - left - 1), excluding the
		// additional increments and decrements.
		return right - left - 1;
	}
}
