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

    /**
     * The Two Recursive Choices
     * At each position (pos) in the word, we face a decision:
     * Abbreviate (Line 1):
     * backtrack(res, word, pos + 1, cur, count + 1);
     * Here's what this line does:
     * pos + 1: We move to the next position in the word.
     * cur: We keep the current abbreviation as it is (don't add the letter at the current position).
     * count + 1: We increase the count of consecutive letters we're potentially abbreviating.
     * This call explores the path where we decide to abbreviate the current letter.
     * Don't Abbreviate (Line 2):
     * backtrack(res, word, pos + 1, cur + (count > 0 ? count : "") + word.charAt(pos), 0);
     * This line is denser, so let's break it down:
     * pos + 1: We move to the next position in the word.
     * cur + (count > 0 ? count : ""):
     * If count > 0 (we were abbreviating before), we add the current count to the abbreviation cur.
     * If count == 0 (we weren't abbreviating), we add nothing.
     * + word.charAt(pos): We add the letter at the current position to the abbreviation cur.
     * 0: We reset the abbreviation count back to zero since we're no longer abbreviating consecutively.
     * This call explores the path where we decide to keep the current letter as it is.
     * Why the Conditional Expression (count > 0 ? count : "")
     * The conditional expression is a shorthand way of saying:
     * "If we were abbreviating before (count > 0), then add the current count (count) to the abbreviation."
     * "Otherwise, add nothing (an empty string "")."
     * This ensures that we only include the count in the abbreviation when we've actually been abbreviating a sequence of letters. If we weren't abbreviating, there's no need to add a count.
     *
     * @param res
     * @param word
     * @param pos
     * @param cur
     * @param count
     */
    private void backtrack(List<String> res, String word, int pos, String cur, int count) {
        // System.out.println("Inside backtrack loopCount:: " + loopCount + " Result Until :: " + res.toString() +  "  word :: " + word + "     pos :: " + pos + "  cur :: " + cur + "  count :: " + count);
        if (pos == word.length()) {
            if (count > 0) cur += count;
            res.add(cur);
        } else {
            backtrack(res, word, pos + 1, cur, count + 1);
            backtrack(res, word, pos + 1, cur + (count > 0 ? count : "") + word.charAt(pos), 0);
        }
    }

    public List<String> generateAbbreviations1(String word) {

        List<String> res = new ArrayList<>();
        backtrack1(res, word, 0, "", 0);
        return res;
    }

    private void backtrack1(List<String> res, String word, int pos, String curr, int count) {

        if (pos == word.length()) {
            if (count > 0) curr += count;
            res.add(curr);
        } else {
            backtrack1(res, word, pos + 1, curr, count + 1);
            backtrack1(res, word, pos + 1, curr + (count > 0 ? count : "") + word.charAt(pos), 0);
        }

    }


    public static void main(String[] args) {
        /*GeneralizedAbbreviation solver2 = new GeneralizedAbbreviation();
        String word2 = "word";
        System.out.println(solver2.generateAbbreviations1(word2));*/

        GeneralizedAbbreviation solver = new GeneralizedAbbreviation();
        String word = "word";
        System.out.println(solver.generateAbbreviations1(word));


    }
}
