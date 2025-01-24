package com.practice.array;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TwoSumInArray {

	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> numMap = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (numMap.containsKey(complement)) {
				System.out.println("complement::" +  complement + " numMap.get(complement) ::"+ numMap.get(complement) + "  i:: " + i);
				System.out.println("numMap size "+ numMap.size()); 
				return new int[] { numMap.get(complement), i };
			}
			numMap.put(nums[i], i);
		}
		
		

		throw new IllegalArgumentException("No valid solution found.");
	}

	public static void main(String[] args) {
		int[] nums1 = { 2, 7, 11, 15, 1 };
		int target1 = 16;
		int[] result1 = twoSum(nums1, target1);
		System.out.println("Result 1: [" + result1[0] + ", " + result1[1] + "]");

		
	}
}