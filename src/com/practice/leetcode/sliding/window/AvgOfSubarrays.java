package com.practice.leetcode.sliding.window;

import java.util.ArrayList;
import java.util.List;

public class AvgOfSubarrays {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        List<Integer> result = avgOfSubarrays(arr, 5);
        result.forEach(System.out::println);
        // O/P: 3 4  5  6  7  8
    }

    private static List<Integer> avgOfSubarrays(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        int wsum = 0;
        int start = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {

            wsum += arr[windowEnd];

            if (windowEnd >= k - 1) {
                result.add(wsum / k);
                wsum -= arr[start];
                start++;
            }
        }
        return result;
    }
}
