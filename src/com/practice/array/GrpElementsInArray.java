package com.practice.array;

import java.util.HashMap;
import java.util.Map;

/**
 * From given array which contains duplicate elements write function to group
 * those elements based on first occurrence of each element.
 * 
 * @author harshal
 *
 */

public class GrpElementsInArray {
	// Function to group elements of the given array based on first
	// occurrence of each element
	public static void rearrange(int[] arr) {
		// create an empty map to store frequency of each element present in the input
		// array
		Map<Integer, Integer> freqMap = new HashMap<>();

		// traverse the input array and update frequency of each element
		for (int i : arr) {
			freqMap.putIfAbsent(i, 0);
			freqMap.put(i, freqMap.get(i) + 1);
		}

		// System.out.println(" freq map keys" + freqMap.keySet());
		// System.out.println(" freq map values" + freqMap.values());

		for (int i : arr) {
			// if i exists in the map (first occurrence of i)
			if (freqMap.containsKey(i)) {
				// print i n times where n = freq[i]
				int n = freqMap.get(i);
				while (n-- != 0) {
					System.out.print(i + " ");
				}

				// delete the element from the map so it would not
				// get processed again
				freqMap.remove(i);
			}
		}
	}

	// main function
	public static void main(String[] args) {
		int[] A = { 5, 4, 5, 5, 33, 1, 2, 2, 4, 10, 15 };

		rearrange(A);
	}
}
