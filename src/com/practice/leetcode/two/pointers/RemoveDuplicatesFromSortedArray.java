package com.practice.leetcode.two.pointers;

/**
 * Remove Duplicates (easy)https://leetcode.com/problems/remove-duplicates-from-sorted-array/Given an
 * array of sorted numbers, remove all duplicates from it. You should not use anyextra space; after
 * removing the duplicates in-place return the length of the subarray that hasno duplicate in it.
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray solution = new RemoveDuplicatesFromSortedArray();
        int[] nums = {1, 1, 2};
        int newLength = solution.removeDuplicates(nums);
        System.out.println("The length of the array after removing duplicates is: " + newLength);
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    private int removeDuplicates(int[] arr) {

        int unqIndex = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[unqIndex] = arr[i];
                unqIndex++;
            }
        }
        return unqIndex;
    }
}
