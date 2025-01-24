package com.practice.string;

public class StringOccurrencesCounter {

	public static int countOccurrences(String bigString, String smallString) {
		int count = 0;
		int index = 0;

		// Loop until no more occurrences are found
		while ((index = bigString.indexOf(smallString, index)) != -1) {
			count++;
			index += smallString.length();
		}

		return count;
	}

	public static void main(String[] args) {
		String bigString = "abababababab";
		String smallString = "ab";

		int occurrences = countOccurrences(bigString, smallString);
		System.out.println("Number of occurrences: " + occurrences);
	}
}
