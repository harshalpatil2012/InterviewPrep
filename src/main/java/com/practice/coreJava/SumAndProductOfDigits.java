package com.practice.coreJava;

import java.util.Scanner;

/**
 * Java program to find sum and multplication of int inp ex: 123 return sum and
 * addition
 * 
 * @author harshal
 *
 */
public class SumAndProductOfDigits {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a positive integer: ");
		int n = sc.nextInt();
		sc.close();

		int sum = 0;
		int product = 1;
		int digit;

		while (n > 0) {
			digit = n % 10; // extract the last digit
			sum += digit; // add it to the sum
			product *= digit; // multiply it to the product
			n /= 10; // remove the last digit
		}

		System.out.println("Sum of digits: " + sum);
		System.out.println("Product of digits: " + product);
	}
}
