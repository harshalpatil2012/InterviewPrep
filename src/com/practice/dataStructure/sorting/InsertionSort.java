package com.practice.dataStructure.sorting;

import java.util.Arrays;

class InsertionSort {
    public static int[] doInsertionSort(int[] arr) {

        int temp;
        /* Function to sort array using insertion sort */
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    // Driver method
    public static void main(String args[]) {
        int arr[] = { 12, 11, 13, 5, 6, 1 };

        InsertionSort ob = new InsertionSort();
        doInsertionSort(arr);

        printArray(arr);
    }
}