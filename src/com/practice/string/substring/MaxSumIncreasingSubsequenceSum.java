package com.practice.string.substring;

import java.util.Arrays;

/**
 * ------------------Maximum Sum Increasing Subsequence------------------
 * Problem Statement Given a number sequence, find the increasing subsequence
 * with the highest sum. Write a method that returns the highest sum.
 * 
 * Example 1:
 * 
 * Input: {4,1,2,6,10,1,12} Output: 32 Explanation: The increaseing sequence is
 * {4,6,10,12}. Please note the difference, as the LIS is {1,2,6,10,12} which
 * has a sum of '31'. Example 2:
 * 
 * Input: {-4,10,3,7,15} Output: 25 Explanation: The increaseing sequences are
 * {10, 15} and {3,7,15}.
 * 
 * @author harshal
 *
 */

public class MaxSumIncreasingSubsequenceSum {

	/**
	 * The method follows these steps:
	 * 
	 * Create a new array dp and initialize it with the same values as the input
	 * array nums. Initialize maxSum with the value of the first element in nums.
	 * Iterate over the array from the second element. For each element, compare it
	 * with all previous elements and if the current element is greater than the
	 * previous element, update the sum at the current index by taking the maximum
	 * of the current sum and the sum of the previous element plus the current
	 * element. Track the maximum sum encountered so far. Finally, return the
	 * maximum sum.
	 * 
	 * @param nums
	 * @return
	 */
	public static int findMaxSum(int[] nums) {
		int[] dp = Arrays.copyOf(nums, nums.length);
		int maxSum = nums[0];

		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + nums[i]);
					maxSum = Math.max(maxSum, dp[i]);
				}
			}
		}

		return maxSum;
	}

	public static void main(String[] args) {
		int[] nums = { 4, 1, 2, 6, 10, 1, 12 };
		int maxSum = findMaxSum(nums);
		System.out.println("Maximum Sum of Increasing Subsequence: " + maxSum);
	}

	/**
	 * optimized version method follows these steps:
	 * 
	 * Create a new array dp of the same length as nums to store the maximum sum at
	 * each index. Initialize dp[0] with the value of the first element in nums.
	 * Initialize maxSum with the value of dp[0]. Iterate over the array starting
	 * from the second element. For each element, set dp[i] to the value of nums[i].
	 * Iterate over the previous elements from 0 to i-1 and check if nums[i] is
	 * greater than the previous element. If so, update dp[i] to the maximum value
	 * between dp[i] and dp[j] + nums[i]. Track the maximum sum encountered so far
	 * by updating maxSum to the maximum value between maxSum and dp[i]. Finally,
	 * return maxSum.
	 * 
	 * @param nums
	 * @return
	 */
	public static int findMaxSumOptimized(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int maxSum = dp[0];

		for (int i = 1; i < nums.length; i++) {
			dp[i] = nums[i];
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + nums[i]);
				}
			}
			maxSum = Math.max(maxSum, dp[i]);
		}

		return maxSum;
	}
}
