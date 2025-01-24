package com.practice.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Return the 2 most occurred strings in array
 * 
 * Explanation the findMostOccurredStrings() method takes an array of strings as
 * input and returns an array of the two most occurred strings in the array. It
 * first creates a HashMap to keep track of the count of each string in the
 * array. It then iterates over each string in the array and updates the count
 * in the map. After that, the method initializes a String array to store the
 * two most occurred strings and two int variables to keep track of the maximum
 * and second maximum counts.
 * 
 * Next, the method iterates over the entries in the map and updates the
 * mostOccurred and maxCount variables if the current count is greater than the
 * current maximum count. If the current count is between the maximum and second
 * maximum counts, the method updates the mostOccurred and secondMaxCount
 * variables.
 * 
 * Finally, the method returns the mostOccurred array, which should contain the
 * two most occurred strings in the original array.
 * 
 * In
 * 
 * the main() method, we create an example array of strings and call the
 * findMostOccurredStrings() method to find the two most occurred strings. The
 * result is printed to the console using System.out.println().
 * 
 * @author harshal
 *
 */
public class MostOccurredStrings {
	public static void main(String[] args) {
		String[] arr = { "cat", "dog", "cat", "bird", "dog", "cat", "dog", "horse" };

		String[] mostOccurred = findMostOccurredStrings(arr);

		System.out.println("Most occurred string: " + mostOccurred[0]);
		System.out.println("Second most occurred string: " + mostOccurred[1]);
	}

	public static String[] findMostOccurredStrings(String[] arr) {
		Map<String, Integer> stringCounts = new HashMap<>();

		for (String str : arr) {
			if (stringCounts.containsKey(str)) {
				stringCounts.put(str, stringCounts.get(str) + 1);
			} else {
				stringCounts.put(str, 1);
			}
		}

		String[] mostOccurred = new String[2];
		int maxCount = 0, secondMaxCount = 0;

		for (Map.Entry<String, Integer> entry : stringCounts.entrySet()) {
			int count = entry.getValue();

			if (count > maxCount) {
				mostOccurred[1] = mostOccurred[0];
				secondMaxCount = maxCount;
				mostOccurred[0] = entry.getKey();
				maxCount = count;
			} else if (count > secondMaxCount) {
				mostOccurred[1] = entry.getKey();
				secondMaxCount = count;
			}
		}

		return mostOccurred;
	}

}