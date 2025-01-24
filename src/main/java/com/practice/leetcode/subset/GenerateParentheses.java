package com.practice.leetcode.subset;
/**
 * 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/description/
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 */

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", n, n);
        return res;
    }

    private void backtrack(List<String> res, String currString, int open, int close) {

        if (open == 0 && close == 0) {
            res.add(currString);
            return;
        }
        if (open > 0) {
            backtrack(res, currString + "(", open - 1, close);
        }
        if (open < close) {
            backtrack(res, currString + ")", open, close - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses solver = new GenerateParentheses();
        int n = 3;
        System.out.println(solver.generateParenthesis(n));
    }
}
