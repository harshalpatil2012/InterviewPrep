package com.practice.programs.palindrome;

import java.util.ArrayList;
import java.util.List;

public class PalindromeNumbersFinder {

	public static boolean isPalindrome(int num) {
		// Convert the number to a string for easier comparison
		String numStr = Integer.toString(num);
		int left = 0;
		int right = numStr.length() - 1;

		// Check if the number is a palindrome
		while (left < right) {
			if (numStr.charAt(left) != numStr.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}

		return true;
	}

	public static List<Integer> findPalindromeNumbers(int[] arr) {
		List<Integer> palindromeNumbers = new ArrayList<>();

		for (int num : arr) {
			if (isPalindrome(num)) {
				palindromeNumbers.add(num);
			}
		}

		return palindromeNumbers;
	}

	public static void main(String[] args) {
		int[] numbers = { 121, 123, 232, 454, 12321, 345, 12121 };
		List<Integer> palindromeNumbers = findPalindromeNumbers(numbers);

		System.out.println("Palindrome numbers in the array:");
		for (int num : palindromeNumbers) {
			System.out.print(num + " ");
		}
	}
}
