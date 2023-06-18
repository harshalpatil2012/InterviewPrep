package com.practice.programs;

import java.util.Arrays;
import java.util.*;
import java.io.*;
import java.lang.*;

public class AnagramCheck {
	static final int CHAR = 256;

	/*
	 * * One way to find if two Strings are anagram in Java. This method * assumes
	 * both arguments are not null and in lowercase. * * @return true, if both
	 * String are anagram
	 */ public static boolean isAnagram(String word, String anagram) {
		if (word.length() != anagram.length()) {
			return false;
		}
		// word rwdo
		// w -> rd
		char[] chars = word.toCharArray();
		for (char c : chars) {
			int index = anagram.indexOf(c);
			if (index != -1) {
				anagram = anagram.substring(0, index) + anagram.substring(index + 1, anagram.length());
			} else {
				return false;
			}
		}
		return anagram.isEmpty();
	}

	/*
	 * * Another way to check if two Strings are anagram or not in Java * This
	 * method assumes that both word and anagram are not null and lowercase
	 * * @return true, if both Strings are anagram.
	 */ public static boolean iAnagram(String word, String anagram) {
		char[] charFromWord = word.toCharArray();
		char[] charFromAnagram = anagram.toCharArray();
		Arrays.sort(charFromWord);
		Arrays.sort(charFromAnagram);
		return Arrays.equals(charFromWord, charFromAnagram);
	}

	public static boolean checkAnagram(String first, String second) {
		char[] charArr = first.toCharArray();
		StringBuilder sbSecond = new StringBuilder(second);
		for (char ch : charArr) {
			int index = sbSecond.indexOf("" + ch);
			if (index != -1) {
				sbSecond.deleteCharAt(index);
			} else {
				return false;
			}
		}
		return sbSecond.length() == 0 ? true : false;
	}

	static boolean areAnagram(String s1, String s2) {

		if (s1.length() != s2.length())
			return false;

		int[] count = new int[CHAR];
		for (int i = 0; i < s1.length(); i++) {
			count[s1.charAt(i)]++;
			count[s2.charAt(i)]--;
		}

		for (int i = 0; i < CHAR; i++) {
			if (count[i] != 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(" Inside main:: " + AnagramCheck.isAnagram("word", "rwdo"));
		System.out.println(" Inside main areAnagram:: " + AnagramCheck.areAnagram("word", "rwdo"));
	}
}
