package com.practice.array;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 */
public class StartEndPosition {
    static int[] nums = {5, 7, 7, 8, 8, 10};
    static int target = 8;

    public static void main(String[] args) {
        int[] range = searchRange(nums, target);

        for (int j : range) {
            System.out.print(j + " ");
        }
    }

    private static int[] searchRange(int[] nums, int target) {

        int start = findPosition(nums, target, true);
        if (start == -1) {
            return new int[]{-1, -1};
        }
        int end = findPosition(nums, target, false);
        return new int[]{start, end};
    }

    private static int findPosition(int[] arr, int target, boolean findFirst) {
        int index = -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                index = mid;
                if (findFirst) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return index;
    }
}
