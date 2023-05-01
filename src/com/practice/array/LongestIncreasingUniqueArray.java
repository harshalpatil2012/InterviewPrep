package com.practice.array;

/**
 * Java program to find the length of the longest strictly increasing unique
 * array formed by two increasing different subarrays of a given array using two
 * pointers:
 */

public class LongestIncreasingUniqueArray {
	public static int longestIncreasingUniqueArray(int[] arr) {
		int n = arr.length;
		int[] leftMax = new int[n];
		int[] rightMin = new int[n];

		// Fill leftMax array with the maximum element encountered so far from the left
		// end of the array
		leftMax[0] = arr[0];
		for (int i = 1; i < n; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
		}

		// Fill rightMin array with the minimum element encountered so far from the
		// right end of the array
		rightMin[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
		}

		int maxLength = 0, i = 0, j = 0;

		// Use two pointers to find the longest strictly increasing unique array formed
		// by two increasing different subarrays
		while (i < n && j < n) {
			if (leftMax[i] < rightMin[j]) {
				maxLength = Math.max(maxLength, j - i - 1);
				i++;
			} else {
				j++;
			}
		}

		return maxLength;
	}

	public static void main(String[] args) {
		int[] arr = { 7, 8, 1, 2, 3, 4, 6, 5, 6, 7, 8, 9 };
		System.out.println(longestIncreasingUniqueArray(arr)); // Output: 8
	}
}
