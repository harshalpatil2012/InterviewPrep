package com.practice.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In this program,
 * 
 * the groupAnagrams() method takes an array of strings as input and returns a
 * list of lists, where each inner list contains all the anagrams of a
 * particular group. The method first creates a HashMap to store the groups of
 * anagrams, with the key being a sorted version of the string and the value
 * being a list of strings that belong to that group. It then iterates over each
 * string in the input array, sorts its characters, and adds it to the
 * corresponding group in the map. Finally, it returns a list of the values in
 * the map, which represent all the anagram groups.
 * 
 * In the main() method, we create an example array of strings and call the
 * groupAnagrams() method to find all the anagram groups. The result is printed
 * to the console using System.out.println().
 * 
 * @author harshal
 *
 */
public class AnagramFinder {
	public static void main(String[] args) {
		String[] arr = { "eat", "tea", "tan", "ate", "nat", "bat" };

		List<List<String>> anagramGroups = groupAnagrams(arr);

		System.out.println(anagramGroups);

		List<List<String>> anagramGroups2 = groupAnagramsWithoutCustomSort(arr);

		System.out.println(anagramGroups2);
	}

	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();

		for (String str : strs) {
			char[] charArray = str.toCharArray();
			Arrays.sort(charArray);
			String sortedStr = new String(charArray);

			if (!map.containsKey(sortedStr)) {
				map.put(sortedStr, new ArrayList<>());
			}

			map.get(sortedStr).add(str);
		}

		return new ArrayList<>(map.values());
	}

	public static List<List<String>> groupAnagramsWithoutCustomSort(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();

		for (String str : strs) {
			int[] charCounts = new int[26];

			for (int i = 0; i < str.length(); i++) {
				charCounts[str.charAt(i) - 'a']++;
			}

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < charCounts.length; i++) {
				for (int j = 0; j < charCounts[i]; j++) {
					sb.append((char) ('a' + i));
				}
			}

			String sortedStr = sb.toString();

			if (!map.containsKey(sortedStr)) {
				map.put(sortedStr, new ArrayList<>());
			}

			map.get(sortedStr).add(str);
		}

		return new ArrayList<>(map.values());
	}
}
