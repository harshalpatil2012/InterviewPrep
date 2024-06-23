package com.practice.leetcode;

/**
 * Problem statement
 * You are given an array/list of ‘N’ integers. You are supposed to return the maximum sum of the subsequence with the
 * constraint that no two elements are adjacent in the given array/list.
 * <p>
 * Note:
 * A subsequence of an array/list is obtained by deleting some number of elements (can be zero)
 * from the array/list, leaving the remaining elements in their original order.
 */
public class MaxNonAdjacentSum {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("max sum of subsequence 1 ::" + maxAdjcentSum(arr));

        arr = new int[]{2, 7, 9, 3, 1};

        System.out.println("max sum of subsequence 2 ::" + maxAdjcentSum(arr));
    }

    private static int maxAdjcentSum(int[] arr) {

        int incl = arr[0];
        int excl = 0;

        for (int i = 1; i < arr.length; i++) {

            int newex = Math.max(incl, excl);
            incl = excl + arr[i];
            excl = newex;
        }

        return Math.max(incl, excl);
    }
}
