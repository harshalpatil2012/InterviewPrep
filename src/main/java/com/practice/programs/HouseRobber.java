package com.practice.programs;

/**
 * There are n houses built in a line. A thief wants to steal the maximum possible money from these houses. 
 * The only restriction the thief has is that he can't steal from two consecutive houses, as that would alert the security 
 * system. How should the thief maximize his stealing?
 * Problem Statement
 * Given a number array representing the wealth of n houses, determine the maximum amount of money the thief can steal without alerting the security system.
 * Example 1:
 * Input: {2, 5, 1, 3, 6, 2, 4}
 * Output: 15
 * Explanation: The thief should steal from houses 5 + 6 + 4
 * @author harshal
 *
 */
/**
 * To solve the problem of maximizing the stolen money from a line of houses,you
 * can use a dynamic programming approach known as the"House
 * Robber"problem.Here'sa Java program that implements this method:
 */
public class HouseRobber {

	public static int maximizeRobbery(int[] houses) {
		int n = houses.length;
		if (n == 0) {
			return 0; // No houses to rob, so the maximum stolen money is 0.
		} else if (n == 1) {
			return houses[0]; // Only one house, so the maximum stolen money is from that house.
		}

		int[] dp = new int[n]; // Array to store the maximum stolen money at each house.

		// Initialize the first two houses' maximum stolen money.
		dp[0] = houses[0];
		dp[1] = Math.max(houses[0], houses[1]);

		// Iterate through the remaining houses to calculate the maximum stolen money.
		for (int i = 2; i < n; i++) {
			// For each house, consider whether to rob it or not.
			// If robbing the current house, add its wealth to the maximum stolen money from
			// two houses ago.
			// If not robbing the current house, take the maximum stolen money from the
			// previous house.
			dp[i] = Math.max(dp[i - 2] + houses[i], dp[i - 1]);
		}

		return dp[n - 1]; // Return the maximum stolen money from the last house.
	}

	public static void main(String[] args) {
		int[] houses = { 2, 5, 1, 3, 6, 2, 4 };
		int maxStolenMoney = maximizeRobbery(houses);
		System.out.println("Maximum stolen money: " + maxStolenMoney);
	}
}
/**
 * In this program, the maximizeRobbery method takes an array of house wealth
 * (houses) as input and returns the maximum amount of money the thief can steal
 * without alerting the security system.
 * 
 * The method follows these steps:
 * 
 * Check the base cases: If there are no houses to rob (empty array), the
 * maximum stolen money is 0. If there is only one house, the maximum stolen
 * money is the wealth of that house. Create an array dp to store the maximum
 * stolen money at each house. Initialize the maximum stolen money for the first
 * two houses (dp[0] and dp[1]). Iterate through the remaining houses, starting
 * from the third house. For each house, consider whether to rob it or not. If
 * robbing the current house, add its wealth to the maximum stolen money from
 * two houses ago (dp[i - 2]). If not robbing the current house, take the
 * maximum stolen money from the previous house (dp[i - 1]). After iterating
 * through all the houses, return the maximum stolen money from the last house
 * (dp[n - 1]). The main method demonstrates the usage of the maximizeRobbery
 * method by finding the maximum stolen money for the given input array of house
 * wealth and prints the result.
 * 
 * When you run the program, it will output the maximum amount of money the
 * thief can steal without alerting the security system, based on the given
 * input. In the example you provided, the output will be 15, as the thief
 * should steal from houses 5 + 6 + 4 to maximize the stolen money.
 */