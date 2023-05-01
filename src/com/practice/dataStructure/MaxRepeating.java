package com.practice.dataStructure;

import java.util.HashMap;

class MaxRepeating {

    // Returns maximum repeating element in arr[0..n-1].
    // The array elements are in range from 0 to k-1
    static int maxRepeating(int arr[], int n, int k) {
        // Iterate though input array, for every element
        // arr[i], increment arr[arr[i]%k] by k
        for (int i = 0; i < n; i++)
            arr[(arr[i] % k)] += k;

        // Find index of the maximum repeating element
        int max = arr[0], result = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
                result = i;
            }
        }

        /* Uncomment this code to get the original array back
        for (int i = 0; i< n; i++)
          arr[i] = arr[i]%k; */

        // Return index of the maximum element
        return result;
    }

    public static void maxRepeatingValue() {
        int arr[] = { 2, 2, 3, 2, 3, 1, 1, 1, 4, 2 };

        HashMap<String, Integer> map = new HashMap<>();

        int maxCount = 0;
        int value = 0;
        for (int i = 0; i < arr.length - 1; i++) {

            if (map.get(String.valueOf(arr[i])) != null) {
                map.put(String.valueOf(arr[i]), map.get(String.valueOf(arr[i])) + 1);
                if (maxCount < map.get(String.valueOf(arr[i]))) {
                    maxCount = map.get(String.valueOf(arr[i]));
                    value = arr[i];
                }
            } else {
                map.put(String.valueOf(arr[i]), 1);
            }

        }

        System.out.println(" maxCount :: " + maxCount + " value::" + value);
    }

    /*Driver function to check for above function*/
    public static void main(String[] args) {

        int arr[] = { 2, 3, 3, 5, 3, 4, 1, 7 };
        int n = arr.length;
        int k = 8;
        System.out.println("Maximum repeating element is: " + maxRepeating(arr, n, k));

        maxRepeatingValue();
    }
}