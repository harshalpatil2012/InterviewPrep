package com.practice.leetcode.sliding.window;

import java.util.HashMap;
import java.util.Map;

/**
 * 904. Fruit Into Baskets
 * You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 * <p>
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 * <p>
 * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 * <p>
 * Example 1:
 * <p>
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 * Example 2:
 * <p>
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 * Example 3:
 * <p>
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 * <p>
 * Constraints:
 * <p>
 * 1 <= fruits.length <= 105
 * 0 <= fruits[i] < fruits.length
 */
public class FruitsIntoBaskets {

    public static void main(String[] args) {
        int[] fruits1 = {1, 2, 1};
        int[] fruits2 = {0, 1, 2, 2};
        int[] fruits3 = {1, 2, 3, 2, 2};
        int[] fruits4 = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};

        System.out.println("Maximum number of fruits: " + totalFruit(fruits1)); // Output: 3
        System.out.println("Maximum number of fruits: " + totalFruit(fruits2)); // Output: 3
        System.out.println("Maximum number of fruits: " + totalFruit(fruits3)); // Output: 4
        System.out.println("Maximum number of fruits: " + totalFruit(fruits4)); // Output: 5

        String str1 = "eceba";
        String str2 = "ccaabbb";
        String str3 = "abcbbbbcccbdddadacb";

        System.out.println("Length of longest substring: " + lengthOfLongestSubstringTwoDistinct(str1)); // Output: 3 ("ece")
        System.out.println("Length of longest substring: " + lengthOfLongestSubstringTwoDistinct(str2)); // Output: 5 ("aabbb")
        System.out.println("Length of longest substring: " + lengthOfLongestSubstringTwoDistinct(str3)); // Output: 10 ("bcbbbbcccb")
    }

    private static int lengthOfLongestSubstringTwoDistinct(String str1) {

        int start = 0;
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {

            char c = str1.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.size() > 2) {
                char left = str1.charAt(start);
                map.put(left, map.getOrDefault(left, 0) - 1);
                if (map.get(left) == 0) {
                    map.remove(left);
                }
                start++;
            }
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }

    private static int totalFruit(int[] fruits) {
        int maxFruits = 0;
        int start = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < fruits.length; i++) {

            int fruit = fruits[i];
            countMap.put(fruit, countMap.getOrDefault(fruit, 0) + 1);

            while (countMap.size() > 2) {

                int leftFruit = fruits[start];
                countMap.put(leftFruit, countMap.getOrDefault(leftFruit, 0) - 1);
                if (countMap.get(leftFruit) == 0) {
                    countMap.remove(leftFruit);
                }
                start++;
            }
            maxFruits = Math.max(maxFruits, i - start + 1);
        }

        return maxFruits;
    }


}
