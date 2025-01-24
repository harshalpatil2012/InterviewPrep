package com.practice.leetcode;

/***
 *
 * LeetCode as "Min Cost Climbing Stairs" (LeetCode #746).
 *
 * Problem: Min Cost Climbing Stairs (LeetCode #746)
 * Description:
 * You are given an integer array cost where cost[i] is the cost of the i-th step on a staircase. Once you pay the cost, you can either climb one or two steps. You need to find the minimum cost to reach the top of the floor. You can either start from the step with index 0, or the step with index 1.
 *
 * Example:
 *
 *
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: You will start at index 1. Pay 15 and climb two steps to reach the top.
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println("min cost " + minCost(cost));


        int[] cost2 = new int[]{10,15,20};
        System.out.println("min cost " + minCost(cost2));
    }

    private static int minCost(int[] cost) {

        int n = cost.length;
        int first = cost[0];
        int second = cost[1];

        int current = 0;
        for (int i = 2; i < n; i++) {

            current = cost[i] + Math.min(first, second);

            first = second;
            second = current;
        }
        return Math.min(first, second);
    }
}
