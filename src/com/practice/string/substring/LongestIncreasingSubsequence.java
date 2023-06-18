package com.practice.string.substring;

import java.util.Arrays;

/**
 * ----------------------Longest Increasing Subsequence----------------------
 * Problem Statement Given a number sequence, find the length of its Longest
 * Increasing Subsequence (LIS). In an increasing subsequence, all the elements
 * are in increasing order (from lowest to highest).
 * 
 * Example 1:
 * 
 * Input: {4,2,3,6,10,1,12} Output: 5 Explanation: The LIS is {2,3,6,10,12}.
 * Example 1:
 * 
 * Input: {-4,10,3,7,15} Output: 4 Explanation: The LIS is {-4,3,7,15}.
 * 
 * @author harshal
 *
 */
public class LongestIncreasingSubsequence {

	/**
	 * The method follows these steps:
	 * 
	 * Create a dp array of the same length as the input array to store the lengths
	 * of the LIS. Initialize each element of the dp array with 1, as the minimum
	 * LIS length for each element is 1. Iterate over the array from the second
	 * element. For each element, compare it with all previous elements and if the
	 * current element is greater than the previous element, update the LIS length
	 * at the current index by taking the maximum of the current LIS length and the
	 * LIS length of the previous element plus 1. Track the maximum LIS length
	 * encountered so far. Finally, return the maximum LIS length.
	 * 
	 * @param nums
	 * @return
	 */
	public static int findLISLength(int[] nums) {
		int[] dp = new int[nums.length];
		int maxLength = 1;

		// Initialize the dp array with 1, as the minimum LIS length for each element is
		// 1
		Arrays.fill(dp, 1);

		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					maxLength = Math.max(maxLength, dp[i]);
				}
			}
		}

		return maxLength;
	}

	public static void main(String[] args) {
		int[] nums = { 4, 2, 3, 6, 10, 1, 12 };
		int lisLength = findLISLength(nums);
		System.out.println("Longest Increasing Subsequence Length: " + lisLength);
	}

	/**
	 * In this optimized version, we use the "Patience Sorting" algorithm, which is
	 * based on the idea of organizing the numbers into piles. The length of the LIS
	 * will be equal to the number of piles.
	 * 
	 * The findLISLength method takes an integer array nums as input and returns the
	 * length of the Longest Increasing Subsequence.
	 * 
	 * The method follows these steps:
	 * 
	 * Create an array called piles to store the top cards of each pile. Initialize
	 * it with 0s. Iterate over each number in the input array. For each number, use
	 * binary search to find the correct pile to place it. We perform a binary
	 * search on the piles array to find the leftmost index left such that
	 * piles[left] is greater than or equal to the current number. This represents
	 * the pile where the number should be placed or inserted. Update the pile at
	 * index left with the current number. If left is equal to the current length
	 * len of the piles, it means we have created a new pile, so we increment len.
	 * Repeat these steps for all numbers in the input array. Finally, return the
	 * length of the piles, which represents the length of the Longest Increasing
	 * Subsequence. The main method demonstrates the usage of the findLISLength
	 * method by finding the length of the Longest Increasing Subsequence for the
	 * given input array and prints the result.
	 * 
	 * When you run the program, it will output the length of the Longest Increasing
	 * Subsequence. In the example you provided, the output would be 5.
	 * 
	 * The optimized version of the code has a time complexity of O(n log n), where
	 * n is the length of the input array. This is due to the binary search
	 * operation performed for each number.
	 */

	public static int findLISLengthOptimized(int[] nums) {
		int[] piles = new int[nums.length];
		int len = 0;

		for (int num : nums) {
			int left = 0, right = len;

			while (left < right) {
				int mid = left + (right - left) / 2;
				if (piles[mid] < num) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}

			piles[left] = num;
			if (left == len) {
				len++;
			}
		}

		return len;
	}
}
