package com.practice.leetcode.sliding.window;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * LAZY CHEF II
 * <p>
 * Description
 * Chef enters the kitchen which consists of N linear blocks where each block contains only 1 ingredient from a list of ingredients.
 * <p>
 * Chef has to prepare the dish consisting of 2 ingredients 11 and 12. To prepare the dish D, the first task for chef is to group blocks of all 11 ingredients and 12 ingredients together and also, group of 11 and 12 ingredients should be adjacent to each other. Chef needs to achieve this task by only swapping adjacent blocks with one another. One swap is equivalent to one valid move. Since the chef is lazy, can you help him determine the minimum number of moves in which he can complete this task.
 * <p>
 * Constraints
 * 1<=N<=100,000
 * <p>
 * There is at-least one occurrence of both 11 and 12 on the shelf.
 * <p>
 * One swap is equivalent to one valid move.
 * <p>
 * Output Format Print the minimum number of moves in which chef can complete this task.
 * <p>
 * Input Format
 * The first line contains N, denoting the number of blocks in the kitchen.
 * The second line contains N space-separated characters, where the ith character represents ingredient on ith block.
 * The third line contains 2 space-separated characters 11 and 12, denoting ingredients required to prepare dish D.
 * <p>
 * Output Format
 * Print the minimum number of moves in which chef can complete this task.
 * <p>
 * Sample Input
 * 7
 * H D H S A H S
 * H S
 * Sample Output
 * 4
 * Explanation
 * Here are the set of moves required: -
 * Move 1 -
 * Swapping element at index 0 and 1 DHHSAHS -
 * Move 2 -
 * Swapping element at index 4 and 5 DHHSHAS -
 * Move 3 -
 * Swapping element at index 5 and 6 DHHSHSA -
 * Move 4 -
 * Swapping element at index 3 and 4 DHHHSSA
 */


public class LazyChefII {

    public static int minMovesToGroup(String[] blocks, String ing1, String ing2) {
        int n = blocks.length;

        // Initialize variables to track the first and last positions of ing1 and ing2
        int firstIng1 = -1, lastIng1 = -1, firstIng2 = -1, lastIng2 = -1;

        // Initialize variables to track swaps
        int swaps1 = 0, swaps2 = 0;

        // Find the first and last occurrences of ing1 and ing2
        for (int i = 0; i < n; i++) {
            if (blocks[i].equals(ing1)) {
                if (firstIng1 == -1) {
                    firstIng1 = i;
                }
                lastIng1 = i;
            } else if (blocks[i].equals(ing2)) {
                if (firstIng2 == -1) {
                    firstIng2 = i;
                }
                lastIng2 = i;
            }
        }

        // Calculate swaps to bring ing1 elements together
        for (int i = firstIng1 + 1; i <= lastIng1; i++) {
            if (!blocks[i].equals(ing1)) {
                swaps1++;
            }
        }

        // Calculate swaps to bring ing2 elements together
        for (int i = firstIng2 + 1; i <= lastIng2; i++) {
            if (!blocks[i].equals(ing2)) {
                swaps2++;
            }
        }

        int totalSwaps = swaps1 + swaps2; // Calculate the total number of swaps needed

        // Handle the adjacent swap (if needed)
        if (lastIng1 < firstIng2) { // ing1 group before ing2 group
            // Swaps needed to move ing2 group next to ing1 group
            for (int i = lastIng1 + 1; i < firstIng2; i++) {
                if (!blocks[i].equals(ing2)) {
                    totalSwaps++;
                }
            }
        } else if (lastIng2 < firstIng1) { // ing2 group before ing1 group
            // Swaps needed to move ing1 group next to ing2 group
            for (int i = lastIng2 + 1; i < firstIng1; i++) {
                if (!blocks[i].equals(ing1)) {
                    totalSwaps++;
                }
            }
        }

        return totalSwaps;
    }

    public static void main(String[] args) {
        // Hardcoded input from the problem statement
        int n = 7;
        String[] kitchen = {"H", "D", "H", "S", "A", "H", "S"};
        String ing1 = "H";
        String ing2 = "S";

        int result = minMovesToGroup(kitchen, ing1, ing2);
        System.out.println(result); // Output: 4
    }
}
