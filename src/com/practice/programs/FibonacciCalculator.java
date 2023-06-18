package com.practice.programs;

import java.util.Scanner;

public class FibonacciCalculator {

	public static void main(String args[]) {

		// input to print Fibonacci series upto how many numbers
		System.out.println("Enter number upto which Fibonacci series to print: ");
		int number = 5;

		System.out.println("Fibonacci series upto " + number + " numbers : ");
		// printing Fibonacci series upto number
		for (int i = 1; i <= number; i++) {
			System.out.print(fibonacci(i) + " # ");
		}

		System.out.print(" fibonacci ::" + fibonacci(5) + " ");

		int n = 5;
		System.out.println(" MY fibonacci: " + myFibonacci(n));
	}

	/*
	 * Java program for Fibonacci number using recursion. This program uses tail
	 * recursion to calculate Fibonacci number for a given number
	 * 
	 * @return Fibonacci number
	 */
	public static int fibonacci(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}

		return fibonacci(number - 1) + fibonacci(number - 2); // tail recursion
	}

	private static int myFibonacci(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		return myFibonacci(n - 1) + myFibonacci(n - 2);

	}

	/*
	 * Java program to calculate Fibonacci number using loop or Iteration.
	 * 
	 * @return Fibonacci number
	 */
	public static int fibonacci2(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}
		int f1 = 1, f2 = 1, fibonacci = 1;
		for (int i = 3; i <= number; i++) {

			// Fibonacci number is sum of previous two Fibonacci number
			fibonacci = f1 + f2;
			f1 = f2;
			f2 = fibonacci;

		}
		return fibonacci; // Fibonacci number

	}

}