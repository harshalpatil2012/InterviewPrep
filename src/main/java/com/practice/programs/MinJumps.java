package com.practice.programs;

/**
 * Given an array of positive numbers, where each element represents the max
 * number of jumps that can be made forward from that element, write a program
 * to find the minimum number of jumps needed to reach the end of the array
 * (starting from the first element). If an element is 0, then we cannot move
 * through that element.
 * 
 * Example 1:
 * 
 * Input = {2,1,1,1,4} Output = 3 Explanation: Starting from index '0', we can
 * reach the last index through: 0->2->3->4
 * 
 * @author harshal
 *
 */
public class MinJumps {

	public static int findMinJumps(int[] arr) {
		int n = arr.length;

		// Create an array to store the minimum jumps required to reach each position.
		int[] jumps = new int[n];
		jumps[0] = 0; // Minimum jumps required to reach the first position is 0.

		// Traverse the array to fill in the minimum jumps for each position.
		for (int i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE; // Initialize the minimum jumps to a large value.

			// Check all previous positions and update the minimum jumps if a better
			// solution is found.
			for (int j = 0; j < i; j++) {
				// If the previous position is reachable and can jump to the current position.
				if (j + arr[j] >= i && jumps[j] != Integer.MAX_VALUE) {
					// Update the minimum jumps for the current position.
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
				}
			}
		}

		return jumps[n - 1]; // Return the minimum jumps required to reach the last position.
	}

	public static void main(String[] args) {
		int[] arr = { 2, 1, 1, 1, 4 };
		int minJumps = findMinJumps(arr);
		System.out.println("Minimum number of jumps required: " + minJumps);

		minJumps = findMinJumpsGreedyApp(arr);
		System.out.println("Minimum number of jumps required: " + minJumps);

	}

	public static int findMinJumpsGreedyApp(int[] arr) {
		int n = arr.length;
		if (n <= 1) {
			return 0; // No jumps needed for an empty array or an array with only one element.
		}

		int maxReach = arr[0]; // Maximum index that can be reached in the current jump.
		int steps = arr[0]; // Number of steps remaining in the current jump.
		int jumps = 1; // Minimum number of jumps required to reach the end.

		// Traverse the array from the second position onwards.
		for (int i = 1; i < n; i++) {
			if (i == n - 1) {
				return jumps; // Reached the last index, return the minimum jumps.
			}

			// Update the maximum reach in the current jump.
			maxReach = Math.max(maxReach, i + arr[i]);

			// Consume a step in the current jump.
			steps--;

			// If no steps remaining, make a jump to the farthest reachable position.
			if (steps == 0) {
				jumps++; // Increase the jump count.
				if (i >= maxReach) {
					return -1; // Cannot reach the end, return -1.
				}
				steps = maxReach - i; // Reset the number of steps remaining.
			}
		}

		return -1; // Cannot reach the end, return -1.
	}
	/**
	 * In this optimized version, we use a greedy approach that iterates through the
	 * array in a single traversal. The idea is to always make the jump to the
	 * farthest reachable position.
	 * 
	 * The method follows these steps:
	 * 
	 * Initialize maxReach to arr[0], which represents the maximum index that can be
	 * reached in the current jump. Initialize steps to arr[0], which represents the
	 * number of steps remaining in the current jump. Initialize jumps to 1, as we
	 * start with the first jump. Traverse the array from the second position
	 * onwards. If we reach the last index, we return the minimum jumps. Update
	 * maxReach if the current index plus its value is greater than the current
	 * maxReach. Consume a step in the current jump. If no steps remain, make a jump
	 * to the farthest reachable position. If the current index is greater than or
	 * equal to maxReach, it means we cannot reach the end, so we return -1. Reset
	 * the number of steps remaining (steps) to the difference between maxReach and
	 * the current index. Increase the jump count (jumps). The main method
	 * demonstrates the usage of the findMinJumps method by finding the minimum
	 * number of jumps required for the given input array and prints the result.
	 * 
	 * When you run the program, it will output the minimum number of jumps needed
	 * to reach the end of the array, based on the given input. In the example you
	 * provided, the output will be 3, as it takes three jumps to reach the end: 0
	 * -> 2 -> 3 -> 4
	 */
}
