package com.practice.programs.palindrome;

/**
 * Problem Statement Given a string, we want to cut it into pieces such that
 * each piece is a palindrome. Write a function to return the minimum number of
 * cuts needed.
 * 
 * Example 1:
 * 
 * Input: "abdbca" Output: 3 Explanation: Palindrome pieces are "a", "bdb", "c",
 * "a".
 * 
 * @author harshal
 *
 */
public class MinimumPalindromeCuts {

	/**
	 * The method follows these steps:
	 * 
	 * Create a 2D array isPalindrome to store whether a substring is a palindrome.
	 * Create a 1D array cuts to store the minimum number of cuts needed. Initialize
	 * the arrays: mark single characters as palindromes and set the maximum number
	 * of cuts for each character. Calculate the isPalindrome array by checking if
	 * the characters at both ends are equal and the substring in between is a
	 * palindrome. Calculate the minimum cuts by considering two cases: a) If the
	 * entire substring is a palindrome, no cuts are needed. b) If the substring is
	 * not a palindrome, try cutting at different positions and update the minimum
	 * cuts accordingly. Return the minimum cuts needed to cut the entire string
	 * into palindromic pieces.
	 * 
	 * @param s
	 * @return
	 */

	public static int findMinimumCuts(String s) {
		int n = s.length();
		boolean[][] isPalindrome = new boolean[n][n];
		int[] cuts = new int[n];

		// Initialize the arrays.
		for (int i = 0; i < n; i++) {
			isPalindrome[i][i] = true;
			cuts[i] = i; // Maximum number of cuts is to cut at each character.
		}

		// Calculate the isPalindrome array.
		for (int start = n - 1; start >= 0; start--) {
			for (int end = start + 1; end < n; end++) {
				if (s.charAt(start) == s.charAt(end) && (end - start == 1 || isPalindrome[start + 1][end - 1])) {
					isPalindrome[start][end] = true;
				}
			}
		}

		// Calculate the minimum cuts.
		for (int i = 1; i < n; i++) {
			if (isPalindrome[0][i]) {
				cuts[i] = 0;
			} else {
				for (int j = 0; j < i; j++) {
					if (isPalindrome[j + 1][i]) {
						cuts[i] = Math.min(cuts[i], cuts[j] + 1);
					}
				}
			}
		}

		return cuts[n - 1];
	}

	public static void main(String[] args) {
		String s = "abdbca";
		int minCuts = findMinimumCuts(s);
		System.out.println("Minimum number of cuts: " + minCuts);
	}

	/**
	 * The optimized solution is similar to the previous one but with a slight
	 * improvement in the inner loop of the palindrome checking. Instead of
	 * iterating from the start of the string to the end, we iterate from the end to
	 * the start. This ensures that we only calculate the palindrome status for
	 * substrings that haven't been checked before. Additionally, we use a shorter
	 * condition to determine if a substring is a palindrome.
	 * 
	 * The overall time complexity of this optimized solution is O(n^2), where n is
	 * the length of the input string.
	 * 
	 * @param s
	 * @return
	 */
	public static int findMinimumCutsOptimized(String s) {
		int n = s.length();
		boolean[][] isPalindrome = new boolean[n][n];
		int[] cuts = new int[n];

		for (int i = 0; i < n; i++) {
			isPalindrome[i][i] = true;
		}

		for (int end = 1; end < n; end++) {
			for (int start = 0; start < end; start++) {
				if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || isPalindrome[start + 1][end - 1])) {
					isPalindrome[start][end] = true;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (isPalindrome[0][i]) {
				cuts[i] = 0;
			} else {
				cuts[i] = i;
				for (int j = 0; j < i; j++) {
					if (isPalindrome[j + 1][i]) {
						cuts[i] = Math.min(cuts[i], cuts[j] + 1);
					}
				}
			}
		}

		return cuts[n - 1];
	}
}
