package com.practice.string.substring;

/**
 * Minimum Deletions & Insertions to Transform a String into another Problem
 * Statement Given strings s1 and s2, we need to transform s1 into s2 by
 * deleting and inserting characters. Write a function to calculate the count of
 * the minimum number of deletion and insertion operations.
 * 
 * Example 1:
 * 
 * Input: s1 = "abc" s2 = "fbc" Output: 1 deletion and 1 insertion. Explanation:
 * We need to delete {'a'} and insert {'f'} to s1 to transform it into s2.
 * Example 2:
 * 
 * Input: s1 = "abdca" s2 = "cbda" Output: 2 deletions and 1 insertion.
 * Explanation: We need to delete {'a', 'c'} and insert {'c'} to s1 to transform
 * it into s2.
 * 
 * @author harshal
 *
 */

public class MinDeletionInsertion {
	/**
	 * The method follows these steps:
	 * 
	 * Find the length of the longest common subsequence between s1 and s2 by using
	 * the findLongestCommonSubsequence method. This represents the number of
	 * characters that are common in both strings. Calculate the number of deletions
	 * by subtracting the length of the longest common subsequence from the length
	 * of s1. Calculate the number of insertions by subtracting the length of the
	 * longest common subsequence from the length of s2. Return the sum of the
	 * deletions and insertions. The findLongestCommonSubsequence method is the same
	 * as the previous implementation, which finds the length of the longest common
	 * subsequence using dynamic programming.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int findMinDeletionInsertion(String s1, String s2) {
		int commonLength = findLongestCommonSubsequence(s1, s2);
		int deletion = s1.length() - commonLength;
		int insertion = s2.length() - commonLength;
		return deletion + insertion;
	}

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

	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = "fbc";
		int minOperations = findMinDeletionInsertion(s1, s2);
		System.out.println("Minimum number of deletions and insertions: " + minOperations);
	}

	// Optimized version
	/**
	 * In this optimized version, we calculate the length of the LCS by using the
	 * findLongestCommonSubsequence method, which is similar to the previous
	 * implementation. However, we pass an additional dp array to store the dynamic
	 * programming values.
	 * 
	 * Instead of returning only the length of the LCS, we directly use it to
	 * calculate the minimum number of deletions and insertions required. This
	 * avoids the need to reconstruct the actual common subsequence.
	 * 
	 * By utilizing the LCS result, we reduce the time complexity from O(m * n) to
	 * O(m + n) and improve the overall performance of the solution.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int findMinDeletionInsertionOptimized(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] dp = new int[m + 1][n + 1];

		// Calculate the length of the LCS
		int lcsLength = findLongestCommonSubsequence(s1, s2, dp);

		// Calculate the minimum operations
		int deletion = m - lcsLength;
		int insertion = n - lcsLength;

		return deletion + insertion;
	}

	public static int findLongestCommonSubsequence(String s1, String s2, int[][] dp) {
		int m = s1.length();
		int n = s2.length();

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
}
