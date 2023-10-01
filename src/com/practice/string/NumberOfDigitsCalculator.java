package com.practice.string;

public class NumberOfDigitsCalculator {

	public static boolean isDigit(char ch) {
		return ch >= '0' && ch <= '9';
	}

	public static int countNumericalDigits(String inputString) {
		int count = 0;

		for (int i = 0; i < inputString.length(); i++) {
			char ch = inputString.charAt(i);
			if (isDigit(ch)) {
				count++;
			}
		}

		return count;
	}

	public static int countNumericalDigitsUsingCharacter(String inputString) {
		int count = 0;

		for (int i = 0; i < inputString.length(); i++) {
			if (Character.isDigit(inputString.charAt(i))) {
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		String inputString = "Hello123World456";
		int numDigits = countNumericalDigits(inputString);
		System.out.println("Number of numerical digits in the string: " + numDigits);
	}
}
