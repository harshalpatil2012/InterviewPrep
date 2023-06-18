package com.practice.string.substring;

import java.util.Arrays;

public class LongestCommonSubsequence {

	/**
	 * The method follows these steps:
	 * 
	 * Create a 2D array dp of size (m+1) x (n+1), where m and n are the lengths of
	 * s1 and s2, respectively. Iterate through s1 and s2 using nested loops. For
	 * each pair of characters, if they are equal, update dp[i][j] to be the value
	 * of dp[i-1][j-1] + 1, indicating that the length of the common subsequence has
	 * increased by 1. If the characters are not equal, take the maximum value
	 * between dp[i-1][j] and dp[i][j-1] and assign it to dp[i][j]. This represents
	 * the length of the longest common subsequence up to the current positions in
	 * s1 and s2. Finally, return dp[m][n], which represents the length of the
	 * longest common subsequence.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int findLongestCommonSubsequence(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[m][n];
	}

	/**
	 * In this optimized version, we use two arrays, previous and current, of size n
	 * + 1 to store the lengths of the common subsequences. Instead of using a 2D
	 * array, we keep track of only the previous row and the current row.
	 * 
	 * During each iteration, we update the current array based on the values in the
	 * previous array and the characters in s1 and s2. After each row iteration, we
	 * swap the previous and current arrays to prepare for the next row.
	 * 
	 * By using this optimized approach, we reduce the space complexity from O(m *
	 * n) to O(n), where m and n are the lengths of s1 and s2, respectively. The
	 * time complexity remains the same, O(m * n), as we still need to iterate
	 * through both strings.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int findLongestCommonSubsequenceOptimized(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[] previous = new int[n + 1];
		int[] current = new int[n + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					current[j] = previous[j - 1] + 1;
				} else {
					current[j] = Math.max(current[j - 1], previous[j]);
				}
			}
			int[] temp = previous;
			previous = current;
			current = temp;
		}

		return previous[n];
	}

	public static void main(String[] args) {
		String s1 = "abdca";
		String s2 = "cbda";
		int longestCommonSubsequence = findLongestCommonSubsequence(s1, s2);
		System.out.println("Length of longest common subsequence: " + longestCommonSubsequence);

	}

}
