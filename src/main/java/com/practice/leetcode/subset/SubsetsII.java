package com.practice.leetcode.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 * Medium
 * Topics
 * Companies
 * Given an integer array  nums  that may contain duplicates, return  all possible
 * subsets
 * (the power set).The solution set  must not  contain duplicate subsets. Return the solution in  any order.
 * <p>
 * Example 1:
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class SubsetsII {

    public static void main(String[] args) {
        SubsetsII solver = new SubsetsII();

        int[] nums1 = {1, 2, 2};
        System.out.println(solver.subsetsWithDup(nums1));

        int[] nums2 = {0};
        System.out.println(solver.subsetsWithDup(nums2));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        subsetGenerator(nums, resultList, new ArrayList<>(), 0);
        return resultList;
    }

    private void subsetGenerator(int[] nums, List<List<Integer>> resultList, ArrayList<Integer> subset, int start) {

        resultList.add(new ArrayList<>(subset));

        for (int j = start; j < nums.length; j++) {
            if (j > start && nums[j] == nums[j - 1]) {
                continue;
            }
            subset.add(nums[j]);
            subsetGenerator(nums, resultList, subset, j + 1);
            subset.remove(subset.size() - 1);

        }
    }


}
