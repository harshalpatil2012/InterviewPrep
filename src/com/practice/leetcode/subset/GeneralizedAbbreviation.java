package com.practice.leetcode.subset;

/**
 * 320. Generalized Abbreviation
 * Write a function to generate the generalized abbreviations of a word.
 * Note:  The order of the output does not matter.
 * Example:
 * Input: "word"
 * Output:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Java optimal solution with approach intuition, algorithm and time and space complexity and visualization.
 * Give complete code including main method and all inputs
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
    int loopCount = 0;

    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<String>();
        backtrack(res, word, 0, "", 0);
        return res;
    }

    private void backtrack(List<String> res, String word, int pos, String cur, int count) {
        loopCount++;
        System.out.println("Inside backtrack loopCount:: " + loopCount + " Result Until :: " + res.toString() +  "  word :: " + word + "     pos :: " + pos + "  cur :: " + cur + "  count :: " + count);
        if (pos == word.length()) {
            System.out.println("Inside backtrack IF and loopCount:: " + "" + cur);
            if (count > 0) cur += count;
            res.add(cur);
        } else {
            backtrack(res, word, pos + 1, cur, count + 1);
            backtrack(res, word, pos + 1, cur + (count > 0 ? count : "") + word.charAt(pos), 0);
        }
    }

    public static void main(String[] args) {
        GeneralizedAbbreviation solver = new GeneralizedAbbreviation();
        String word = "word";
        System.out.println(solver.generateAbbreviations(word));
    }
}
