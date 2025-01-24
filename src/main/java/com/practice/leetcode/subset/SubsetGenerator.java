package com.practice.leetcode.subset;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * Medium
 * Topics
 * Companies
 * Given an integer array  nums  of  unique  elements, return  all possible
 * subsets
 * (the power set).The solution set  must not  contain duplicate subsets. Return the solution in  any order.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]] Java optimal solution with approach intuition, algorithm and time and space complexity and visualization.
 * Give complete code including main method and all inputs
 */
public class SubsetGenerator {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> subset, int[] nums, int start) {
        result.add(new ArrayList<>(subset)); // Add a copy of the current subset to the result

        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);         // Include the current element
            backtrack(result, subset, nums, i + 1); // Recurse
            subset.remove(subset.size() - 1);    // Exclude (backtrack) and recurse
        }
    }

    public static void main(String[] args) {
        SubsetGenerator solver = new SubsetGenerator();

        int[] nums1 = {1, 2, 3};
        System.out.println(solver.subsets(nums1));

        int[] nums2 = {0};
        System.out.println(solver.subsets(nums2));
    }
}