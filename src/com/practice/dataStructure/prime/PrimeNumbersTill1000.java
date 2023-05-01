package com.practice.dataStructure.prime;

import java.util.Arrays;

/**
 * Java program to find all prime numbers till 1000 numbers:
 * 
 * In this program, we start with 2 and iterate till 1000. For each number, we
 * call the isPrime() method to check if it's a prime number or not. To check if
 * a number is prime, we start iterating from 2 till the square root of the
 * number. If we find any factor of the number in this range, we know that the
 * number is not prime. Otherwise, the number is prime. We return true if the
 * number is prime and false otherwise.
 * 
 * @author harshal
 *
 */
public class PrimeNumbersTill1000 {

	public static void main(String[] args) {
		int limit = 1000;
		System.out.println("Prime numbers till " + limit + ":");
		for (int i = 2; i <= limit; i++) {
			if (isPrime(i)) {
				System.out.print(i + " ");
			}
		}

		isPrimeOptimal();

	}

	private static boolean isPrime(int number) {
		if (number <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * One way to optimize the program to find prime numbers till 1000 is to use the
	 * Sieve of Eratosthenes algorithm. The algorithm works as follows:
	 * 
	 * Create a boolean array isPrime of size limit + 1 and initialize all the
	 * values to true. Iterate from 2 to the square root of limit. For each number i
	 * in this range: If isPrime[i] is true, mark all the multiples of i as false in
	 * the isPrime array. Iterate from 2 to limit. For each number i in this range:
	 * If isPrime[i] is true, print i. Here's the optimized Java program to find all
	 * prime numbers till 1000 using the Sieve of Eratosthenes algorithm:
	 */

	public static void isPrimeOptimal() {
		int limit = 1000;
		System.out.println("Prime numbers till " + limit + ":");
		boolean[] isPrime = new boolean[limit + 1];
		Arrays.fill(isPrime, true);
		for (int i = 2; i * i <= limit; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j <= limit; j += i) {
					isPrime[j] = false;
				}
			}
		}
		for (int i = 2; i <= limit; i++) {
			if (isPrime[i]) {
				System.out.print(i + " ");
			}
		}
	}
}