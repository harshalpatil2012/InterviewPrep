package com.practice.programs;

/**
 * Given a number 'n', implement a method to count how many possible ways there
 * are to express 'n' as the sum of 1, 3, or 4. For example n : 5 Number of ways
 * = 6 Explanation: Following are the six ways we can express 'n' : {1,1,1,1,1},
 * {1,1,3}, {1,3,1}, {3,1,1}, {1,4}, {4,1}
 * 
 * @author harshal In this program, the countWays method takes the number (n) as
 *         input and recursively calculates the number of ways to express it as
 *         the sum of 1, 3, or 4.
 * 
 *         The method follows these rules:
 * 
 *         If 'n' is negative, it means an invalid case, so it returns 0. If 'n'
 *         is 0, it means a valid way to express 'n' is found, so it returns 1.
 *         Otherwise, it recursively explores all possible choices by
 *         subtracting 1, 3, or 4 from 'n' and sums up the counts of each
 *         choice. The main method demonstrates the usage of the countWays
 *         method by counting the number of ways to express the number 5 as the
 *         sum of 1, 3, or 4 and prints the result.
 * 
 *         When you run the program, it will output the number of possible ways
 *         to express the given number as the sum of 1, 3, or 4. In this case,
 *         the output will be 6, as mentioned in the example you provided.
 */
public class NumberSum {

	public static int countWays(int n) {
		if (n < 0) {
			return 0; // Invalid case: No ways to express negative numbers.
		} else if (n == 0) {
			return 1; // Base case: Found a valid way to express 'n'.
		} else {
			// Recursive case: Explore all possible choices and sum up the counts.
			return countWays(n - 1) + countWays(n - 3) + countWays(n - 4);
		}
	}

	public static void main(String[] args) {
		int num = 5;
		int ways = countWays(num);
		System.out.println("Number of ways to express " + num + " as the sum of 1, 3, or 4: " + ways);
	}
}
