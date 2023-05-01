package com.practice.arrStrings;
/*Write a function to check whether two given strings are Permutation of each other or not. 
 * A Permutation of a string is another string that contains same characters, 
 * only the order of characters can be different. For example, “abcd” and “dabc” are Permutation of each other.
 * 
*/

import java.util.Arrays;

//case sensitive or not?
public class StringPermutation {

	public static void main(String[] args) {

		System.out.println("are string purmation of each other:: " + permutation("abcd", "dabc"));
		System.out.println(
				"are string purmation of each other using arrays:: " + permutationUsingArrSorting("abcd", "dabc"));
	}

	// sorting the strings will put the characters from two permutations in the same
	// order
	static String sort(String s) {
		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}

	static boolean permutationUsingArrSorting(String s, String t) {
		// if they are different lengths, they cannot be permutations
		if (s.length() != t.length())
			return false;
		return sort(s).equals(sort(t));
	}

	// check if the two strings have identical characters counts
	static boolean permutation(String s, String t) {
		if (s.length() != t.length())
			return false;
		int[] letters = new int[256];

		char[] s_array = s.toCharArray();
		for (char c : s_array) {
			letters[c]++;
		}

		for (int i = 0; i < t.length(); i++) {
			int c = (int) t.charAt(i);
			if (--letters[c] < 0)
				return false;
		}
		return true;
	}
}