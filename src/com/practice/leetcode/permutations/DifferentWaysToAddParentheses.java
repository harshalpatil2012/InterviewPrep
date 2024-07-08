package com.practice.leetcode.permutations;

/**
 * 241. Different Ways to Add Parentheses
 * Given a string expression of numbers and operators, return all possible results from computing
 * all the different possible ways to group numbers and operators. You may return the answer in any order.
 * The test cases are generated such that the output values fit in a 32-bit integer and the number
 * of different results does not exceed 104.
 * Example 1:
 * Input: expression = "2-1-1"
 * Output: [0,2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 * Input: expression = "2*3-4*5"
 * Output: [-34,-14,-10,-10,10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Algorithm High-Level Summary
 * Initialization:
 * Start with a memoization map (memo) to store results of previously computed expressions.
 * Recursive Computation:
 * For a given expression, check if the result is already in memo.
 * If yes, return the memoized result.
 * If not, initialize an empty list (result) to store possible results.
 * Splitting the Expression:
 * Loop through each character in the expression.
 * When an operator ('+', '-', '*') is found, split the expression into left and right parts.
 * Recursive Calls for Sub-expressions:
 * Recursively compute results for the left and right parts of the expression.
 * Combining Results:
 * Combine results from the left and right parts using the current operator.
 * Add these combined results to the result list.
 * Handling Base Case:
 * If no operators are found (expression is a single number), convert it to an integer and add to result.
 * Storing Results:
 * Store the computed result for the current expression in the memo map.
 * Return the Result:
 * Return the result list containing all possible results for the given expression.
 */
public class DifferentWaysToAddParentheses {

    public List<Integer> diffWaysToCompute(String expression) {
        Map<String, List<Integer>> memo = new HashMap<>();
        return compute(expression, memo);
    }

    private List<Integer> compute(String expression, Map<String, List<Integer>> memo) {
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String left = expression.substring(0, i);
                String right = expression.substring(i + 1);

                List<Integer> leftResults = compute(left, memo);
                List<Integer> rightResults = compute(right, memo);

                for (int l : leftResults) {
                    for (int r : rightResults) {
                        if (c == '+') {
                            result.add(l + r);
                        } else if (c == '-') {
                            result.add(l - r);
                        } else if (c == '*') {
                            result.add(l * r);
                        }
                    }
                }
            }
        }
        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }
        memo.put(expression, result);
        return result;
    }

    public static void main(String[] args) {
        DifferentWaysToAddParentheses solution = new DifferentWaysToAddParentheses();

        String expression1 = "2-1-1";
        System.out.println("Expression: " + expression1);
        System.out.println("Output: " + solution.diffWaysToCompute(expression1));

        String expression2 = "2*3-4*5";
        System.out.println("Expression: " + expression2);
        System.out.println("Output: " + solution.diffWaysToCompute(expression2));
    }
}

