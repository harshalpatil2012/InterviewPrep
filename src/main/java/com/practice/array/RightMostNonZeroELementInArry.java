package com.practice.array;

import java.util.*;

/**
 * Find right most non-zero digit in multiplication of array elements
 * 
 * @author harshal
 * 
 *         Given an array arr[] of N non-negative integers. The task is to find
 *         the rightmost non-zero digit in the product of array elements.
 *         Examples:
 * 
 * 
 *         Input: arr[] = {3, 5, 6, 90909009} Output: 7 Input: arr[] = {7, 42,
 *         11, 64} Output: 6 Result of multiplication is 206976 So the rightmost
 *         digit is 6
 *
 */
class RightMostNonZeroELementInArry {

	// Function to return the rightmost non-zero
	// digit in the multiplication
	// of the array elements
	static int rightmostNonZero(int a[], int n) {
		// To store the count of times 5 can
		// divide the array elements
		int c5 = 0;

		// Divide the array elements by 5
		// as much as possible
		for (int i = 0; i < n; i++) {
			while (a[i] > 0 && a[i] % 5 == 0) {
				a[i] /= 5;

				// increase count of 5
				c5++;
			}
		}

		// Divide the array elements by
		// 2 as much as possible
		for (int i = 0; i < n; i++) {
			while (c5 != 0 && a[i] > 0 && (a[i] & 1) == 0) {
				a[i] >>= 1;

				// Decrease count of 5, because a '2' and
				// a '5' makes a number with last digit '0'
				c5--;
			}
		}

		int ans = 1;
		for (int i = 0; i < n; i++) {
			ans = (ans * a[i] % 10) % 10;
		}

		// If c5 is more than the multiplier
		// should be taken as 5
		if (c5 != 0)
			ans = (ans * 5) % 10;

		if (ans != 0)
			return ans;

		return -1;
	}

	// Driver code
	public static void main(String args[]) {
		int a[] = { 7, 42, 11, 64 };
		int n = a.length;

		System.out.println(rightmostNonZero(a, n));
	}
}
