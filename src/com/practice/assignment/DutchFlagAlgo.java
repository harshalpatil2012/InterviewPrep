package com.practice.assignment;

public class DutchFlagAlgo {

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 3, 2, 3, 2, 1, 3, 2 };
        arr = DFA(arr);

        System.out.println("arr::" + arr);
        /*
         * for (int i = 0; i < arr.length; i++) { System.out.println( ""); }
         */
    }

    public static void swap(int[] arr, int i1, int i2) {

        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static int[] DFA(int[] arr) {
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        // one pass through the array swapping
        // the necessary elements in place
        while (mid <= high) {
            if (arr[mid] == 0) {
                // swap(arr, low++, mid++);
                int temp = arr[low];
                arr[low] = arr[mid];
                low++;
                mid++;
            } else if (arr[mid] == 2) {
                swap(arr, mid, high--);
                int temp = arr[mid];
                arr[mid] = arr[high];
                high--;

            } else if (arr[mid] == 1) {
                mid++;
            }
        }
        return arr;
    }
}