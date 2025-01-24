package com.practice.array;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Replace each element of the array by its corresponding rank in the array.
 * 
 * The minimum element in the array has rank 1, the second minimum element has
 * rank of 2 and so on.. For example, Input: { 10, 8, 15, 12, 6, 20, 1 } Output:
 * { 4, 3, 6, 5, 2, 7, 1 }
 * 
 * @author harshal
 *
 */
public class ArrayRank {

	// Function to replace each element of the array by its rank in the array
	public static void transform(int[] arr) {
		// create an empty TreeMap
		Map<Integer, Integer> map = new TreeMap<>();

		// store (element, index) pair in TreeMap
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], i);
		}

		// keys are stored in sorted order in TreeMap

		// rank starts from 1
		int rank = 1;

		// iterate through the map and replace each element by its rank
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			arr[entry.getValue()] = rank++;
		}
	}

	// Find all distinct combinations of given length
	public static void main(String[] args) {
		int[] A = { 10, 8, 15, 12, 6, 20, 1 };

		// transform the array
		transform(A);

		// print the transformed array
		System.out.println(Arrays.toString(A));
	}
}
