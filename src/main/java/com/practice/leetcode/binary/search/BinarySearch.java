package com.practice.leetcode.binary.search;

public class BinarySearch {
    // Test Cases
    public static void main(String[] args) {
        BinarySearch searcher = new BinarySearch();
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int[] nums2 = {-1, 0, 3, 5, 9, 12};
        int[] nums3 = {5}; // Single element array

        System.out.println("Test 1: " + searcher.search(nums1, 9));  // Expected: 4
        System.out.println("Test 2: " + searcher.search(nums2, 2));  // Expected: -1
        System.out.println("Test 3: " + searcher.search(nums3, 5));  // Expected: 0
    }

    private int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
