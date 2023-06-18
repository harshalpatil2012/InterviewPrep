package com.practice.string.substring;

import java.util.Arrays;

public class CheckStringSubSequence {

	static boolean isSubSeq(String s1, String s2, int n, int m) {
		int j = 0;
		for (int i = 0; i < n && j < m; i++) {
			if (s1.charAt(i) == s2.charAt(j))
				j++;
		}

		return j == m;
	}

	public static void main(String[] args) {
		String s1 = "ABCDEF";
		String s2 = "ABD";

		System.out.println("Length of longest common subsequence: " + isSubSeq(s1, s2, s1.length(), s2.length()));
		
		System.out.println("Length of longest common subsequence: " + isSubSeqOptimized(s1, s2, s1.length(), s2.length()));

	}

	// optimized version
	static boolean isSubSeqOptimized(String s1, String s2, int n, int m) {
		if (m == 0)
			return true;

		if (n == 0)
			return false;

		if (s1.charAt(n - 1) == s2.charAt(m - 1))
			return isSubSeq(s1, s2, n - 1, m - 1);

		else
			return isSubSeq(s1, s2, n - 1, m);
	}

}
