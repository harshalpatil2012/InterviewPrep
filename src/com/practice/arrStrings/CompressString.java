package com.practice.arrStrings;

/**
 * Implement a method to perform basic string compression using the counts of
 * repeated characters. For example, the string aabcccccaaa would become
 * a2b1c5a3. If the compressed string would not become smaller than the original
 * string, your method should return the original string.
 */

import java.io.*;

import java.util.*;

public class CompressString {

	public static String compression1(String s) {
		String ans = "";
		for (int i = 0; i < s.length(); i++) {
			while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
				i++;
			}
			ans += s.charAt(i);
		}
		return ans;
	}

	public static String compression2(String s) {
		String ans = "";
		for (int i = 0; i < s.length(); i++) {
			int count = 1;
			while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
				count++;
				i++;
			}
			ans += s.charAt(i);
			if (count > 1) {
				ans += count;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		String s = "aaabbccdee";
		System.out.println("compression1:: "+compression1(s));
		System.out.println("compression2:: "+compression2(s));
	}
}