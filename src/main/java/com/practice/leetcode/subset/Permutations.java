package com.practice.leetcode.subset;
/**
 * Leetcode 46. Permutations
 * https://leetcode.com/problems/permutations/description/
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: [[1]]
 */

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, ArrayList<Integer> currPermutation, List<List<Integer>> result) {

        if (currPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currPermutation));
            return;
        }

        for (int num : nums) {
            if (!currPermutation.contains(num)) {
                currPermutation.add(num);
                backtrack(nums, currPermutation, result);
                currPermutation.remove(currPermutation.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        Permutations solver = new Permutations();

        int[] nums1 = {1, 2, 3};
        System.out.println(solver.permute(nums1));  // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

        int[] nums2 = {0, 1};
        System.out.println(solver.permute(nums2));  // [[0, 1], [1, 0]]
    }
}

