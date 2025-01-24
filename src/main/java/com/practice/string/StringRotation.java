package com.practice.string;

/**
 * Check if Strings are Rotations
 * 
 * @author harshal
 *
 */
public class StringRotation {

	public static boolean arerotations(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		String temp = s1 + s1;
		return temp.indexOf(s2) != -1;
	}

	public static void main(String[] args) {
		String s1 = "ABCD";
		String s2 = "CDAB";
		System.out.println(arerotations(s1, s2));
	}
}
