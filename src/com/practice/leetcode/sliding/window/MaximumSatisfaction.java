package com.practice.leetcode.sliding.window;

/**
 * 1402. Reducing Dishes
 * A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
 * Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes
 * multiplied by its satisfaction level i.e. time[i] * satisfaction[i].
 * Return the maximum sum of like-time coefficient that the chef can obtain after preparing some amount of dishes.
 * Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
 * Example 1:
 * Input: satisfaction = [-1,-8,0,5,-9]
 * Output: 14
 * Explanation: After Removing the second and last dish, the maximum total like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14).
 * Each dish is prepared in one unit of time.
 * Example 2:
 * Input: satisfaction = [4,3,2]
 * Output: 20
 * Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
 * Example 3:
 * Input: satisfaction = [-1,-4,-5]
 * Output: 0
 * Explanation: People do not like the dishes. No dish is prepared.
 * <p>
 * Approach
 * We start by sorting the satisfaction array in ascending order. Then,
 * we iterate over the array from right to left. For each dish, if adding it to the cooked dishes
 * increases the total satisfaction, we add it; otherwise, we stop since adding more dishes will
 * only decrease the total satisfaction.
 */


import java.util.Arrays;

public class MaximumSatisfaction {


    public static void main(String[] args) {
        MaximumSatisfaction solution = new MaximumSatisfaction();
        // Test case
        int[] satisfaction = {-1, -8, 0, 5, -7};
        System.out.println(solution.maxSatisfaction(satisfaction)); // Output: 14
    }

    private int maxSatisfaction(int[] arr) {
        Arrays.sort(arr);
        int current = 0;
        int total = 0;

        for (int i = arr.length - 1; i >= 0 && arr[i] + current > 0; i--) {
            current += arr[i];
            total += current;
        }
        return total;
    }
}

