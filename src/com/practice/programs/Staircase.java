package com.practice.programs;

/**
 * Given a stair with 'n' steps, implement a method to count how many possible
 * ways are there to reach the top of the staircase, given that, at every step
 * you can either take 1 step, 2 steps, or 3 steps. write java program
 * 
 * @author harshal
 * 
 *         In bwlow program, the countWays method takes the number of steps (n)
 *         as input and returns the number of possible ways to reach the top of
 *         the staircase. It uses dynamic programming to calculate the number of
 *         ways for each step based on the counts of previous steps.
 * 
 *         The main method demonstrates the usage of the countWays method by
 *         counting the number of ways to reach the top of a staircase with 5
 *         steps and printing the result. You can modify the numSteps variable
 *         to test different input values.
 * 
 *         When you run the program, it will output the number of possible ways
 *         to reach the top of the staircase with the given number of steps.
 *
 */
public class Staircase {

	public static int countWays(int n) {
		if (n == 0 || n == 1) {
			return 1; // Base case: There is only one way to reach 0 or 1 step.
		} else if (n == 2) {
			return 2; // Base case: There are two ways to reach 2 steps (1+1 or 2).
		}

		// Initialize an array to store the number of ways to reach each step.
		int[] ways = new int[n + 1];

		// Base cases
		ways[0] = 1;
		ways[1] = 1;
		ways[2] = 2;

		// Calculate the number of ways for each step up to 'n' using previous steps'
		// counts.
		for (int i = 3; i <= n; i++) {
			ways[i] = ways[i - 1] + ways[i - 2] + ways[i - 3];
		}

		return ways[n];
	}

	public static void main(String[] args) {
		int numSteps = 5;
		int ways = countWays(numSteps);
		System.out.println("Number of ways to reach the top of the staircase with " + numSteps + " steps: " + ways);
	}
}
