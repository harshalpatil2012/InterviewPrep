package com.practice.arrStrings;
/*
 * Assume you have a method isSubstring which checks if one word is a substring of another.
 * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call.
 * eg given s1 = ABCD and s2 = CDAB, return true, given s1 = ABCD, and s2 = ACBD , return false 
*/

//simply do isSubstring(s1s1, s2)
public class StringRotation {
	/*
	 * Function checks if passed strings (str1 and str2) are rotations of each other
	 */
	static boolean areRotations(String str1, String str2) {
		// There lengths must be same and str2 must be
		// a substring of str1 concatenated with str1.
		return (str1.length() == str2.length()) && ((str1 + str1).indexOf(str2) != -1);
	}

	// Driver method
	public static void main(String[] args) {
		String str1 = "AACDA";
		String str2 = "ACDAA";

		if (areRotations(str1, str2))
			System.out.println("Strings are rotations of each other");
		else
			System.out.printf("Strings are not rotations of each other");
	}
}
