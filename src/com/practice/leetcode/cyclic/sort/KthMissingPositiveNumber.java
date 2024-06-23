package com.practice.leetcode.cyclic.sort;

/**
 * 1539. Kth Missing Positive Number
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * <p>
 * Return the kth positive integer that is missing from this array.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * Example 2:
 * <p>
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.'
 */
public class KthMissingPositiveNumber {

    public static int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int missingCount = arr[mid] - mid - 1;

            if (missingCount < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // At the end of the loop, left points to the first element in arr
        // such that arr[left] - left - 1 >= k.
        // The kth missing positive number will be arr[left] - (arr[left] - left - 1 - k) = left + k.
        return left + k;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 4, 7, 11};
        int k1 = 5;
        System.out.println("The " + k1 + "th missing positive integer is: " + findKthPositive(arr1, k1)); // Output: 9

        int[] arr2 = {1, 2, 3, 4};
        int k2 = 2;
        System.out.println("The " + k2 + "nd missing positive integer is: " + findKthPositive(arr2, k2)); // Output: 6
    }
}
