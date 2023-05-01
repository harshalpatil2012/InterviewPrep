package com.practice.dataStructure.search;

public class BinarySearchImpl {

    public static void main1(String[] args) {
        int[] a = { 3, 7, 10, 15, 91, 110, 150 }; // a sorted array not
                                                  // containing duplicates
        int target = 91; // the element to be searched
        int left = 0;
        int middle;
        int right = a.length - 1;
        while (left <= right) {
            middle = (left + right) / 2;
            if (a[middle] == target) {
                System.out.println("Element found at index " + middle);
                break;
            } else if (a[middle] < target) {
                left = middle + 1;
            } else if (a[middle] > target) {
                right = middle - 1;
            }
        }
    }

    public int binarySearch(int[] arr, int key) {

        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key == arr[mid]) {
                return mid;
            }
            if (key < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    // Java implementation of recursive Binary Search
    // Returns index of x if it is present in arr[l..
    // r], else return -1
    int recrsiveBinarySearch(int arr[], int start, int len, int key) {
        if (len >= start) {
            int mid = start + (len - start) / 2;

            // If the element is present at the middle itself
            if (arr[mid] == key)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > key)
                return recrsiveBinarySearch(arr, start, mid - 1, key);

            // Else the element can only be present
            // in right subarray
            return recrsiveBinarySearch(arr, mid + 1, len, key);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    public static void main(String[] args) {

        BinarySearchImpl mbs = new BinarySearchImpl();
        int[] arr = { 2, 6, 6, 8, 10, 12, 14, 16 };
        System.out.println("Key 14's position: " + mbs.binarySearch(arr, 2));
        int[] arr1 = { 6, 34, 78, 123, 432, 900 };
        System.out.println("Key 432's position: " + mbs.binarySearch(arr1, 432));

        BinarySearchImpl ob = new BinarySearchImpl();
        int arr2[] = { 2, 3, 4, 10, 40 };
        int len = arr.length -1;
        int key = 10;
        int result = ob.recrsiveBinarySearch(arr2, 0, len, key);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at index " + result);
    }

}
