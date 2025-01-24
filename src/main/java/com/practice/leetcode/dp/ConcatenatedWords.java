package com.practice.leetcode.dp;

/**
 * 472. Concatenated Words
 * Given an array of stringswords(without duplicates), returnall theconcatenated wordsin the given list ofwords.
 * Aconcatenated wordis defined as a string that is comprised entirely of at least two shorter words (not necessarily distinct)in the given array.
 * Example 1:
 * Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Example 2:
 * Input: words = ["cat","dog","catdog"]
 * Output: ["catdog"]
 * Constraints:
 * 1 <= words.length <= 104
 * 1 <= words[i].length <= 30
 * words[i]consists of only lowercase English letters.
 * All the strings ofwordsareunique.
 * 1 <= sum(words[i].length) <= 105
 */

import java.util.*;

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        // Sort words by their lengths
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        Set<String> wordSet = new HashSet<>();
        List<String> concatenatedWords = new ArrayList<>();

        for (String word : words) {
            if (canForm(word, wordSet)) {
                concatenatedWords.add(word);
            }
            wordSet.add(word);
        }

        return concatenatedWords;
    }

    private boolean canForm(String word, Set<String> wordSet) {
        if (wordSet.isEmpty()) {
            return false;
        }

        int length = word.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;

        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[length];
    }

    public List<String> findAllConcatenatedWordsInADict1(String[] words) {

        Arrays.sort(words, (a, b) -> a.length() - b.length());

        Set<String> wordSet = new HashSet<>();
        List<String> concatenatedWords = new ArrayList<>();

        for (String word : words) {
            if (canForm1(word, wordSet)) {
                concatenatedWords.add(word);
            }
            wordSet.add(word);

        }

        return concatenatedWords;
    }

    private boolean canForm1(String word, Set<String> wordSet) {
        if (wordSet.isEmpty()) {
            return false;
        }

        int length = word.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }

    public static void main(String[] args) {
        ConcatenatedWords solution = new ConcatenatedWords();

        // Test cases
        String[][] testCases = {
                {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"},
                {"cat", "dog", "catdog"}
        };

        for (int i = 0; i < testCases.length; i++) {
            List<String> result = solution.findAllConcatenatedWordsInADict1(testCases[i]);
            System.out.println("Example " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            System.out.println("Output: " + result);
            System.out.println();
        }
    }
}

