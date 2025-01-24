package com.practice.leetcode.subset;

import java.util.ArrayList;
import java.util.List;

/**
 * String Permutations by changing case
 * Problem:
 * <p>
 * Given a string s, you can transform every letter individually to be lowercase or
 * uppercase to create another string. The goal is to return a list of all possible strings we could create.
 */
public class LetterCasePermutation {
    public static void main(String[] args) {
        LetterCasePermutation solver = new LetterCasePermutation();
        String s = "a1b";
        System.out.println(solver.letterCasePermutation(s)); // [a1b, a1B, A1b, A1B]
    }

    private List<String> letterCasePermutation(String s) {

        List<String> result = new ArrayList<>();
        backtrack(result, s.toCharArray(), 0);
        return result;
    }

    private void backtrack(List<String> result, char[] charArray, int index) {

        if (index == charArray.length) {
            result.add(new String(charArray));
            return;
        }

        if(Character.isLetter(charArray[index])) {
            charArray[index] = Character.toLowerCase(charArray[index]);
            backtrack(result, charArray, index + 1);
            charArray[index] = Character.toUpperCase(charArray[index]);
        }
        backtrack(result, charArray, index + 1);
    }
}
