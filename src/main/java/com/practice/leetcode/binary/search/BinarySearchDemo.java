package com.practice.leetcode.binary.search;

import java.util.Arrays;

public class BinarySearchDemo {

    // Iterative Binary Search
    public static int binarySearchIterative(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1; // Target not found
    }

    // Recursive Binary Search
    public static int binarySearchRecursive(int[] arr, int target, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                return binarySearchRecursive(arr, target, mid + 1, high);
            } else {
                return binarySearchRecursive(arr, target, low, mid - 1);
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        // Hardcoded input
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 7;

        // Iterative Search
        int indexIterative = binarySearchIterative(sortedArray, target);
        if (indexIterative != -1) {
            System.out.println("Iterative: Target found at index " + indexIterative);
        } else {
            System.out.println("Iterative: Target not found");
        }

        // Recursive Search
        int indexRecursive = binarySearchRecursive(sortedArray, target, 0, sortedArray.length - 1);
        if (indexRecursive != -1) {
            System.out.println("Recursive: Target found at index " + indexRecursive);
        } else {
            System.out.println("Recursive: Target not found");
        }

        // Using the built-in Arrays.binarySearch()
        int indexBuiltin = Arrays.binarySearch(sortedArray, target);
        if (indexBuiltin >= 0) { // Note the condition for built-in search
            System.out.println("Built-in: Target found at index " + indexBuiltin);
        } else {
            System.out.println("Built-in: Target not found");
        }
    }
}
