package com.practice.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqeSmallestString {

    public static void main(String[] args) {

        String input = "BCDAAABCCDABCCDDAC";

        System.out.println("smallestSubString is :: " + smallestSubString(input));

    }

    public static String smallestSubString(String s) {
        Set<Character> uniqueChars = new HashSet<Character>();
        for (char c : s.toCharArray()) {
            uniqueChars.add(c);
        }

        Map<Character, Integer> charCount = new HashMap<Character, Integer>();
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        String minSubString = "";

        for (int end = 0; end < s.length(); end++) {
            char endChar = s.charAt(end);
            charCount.put(endChar, charCount.getOrDefault(endChar, 0) + 1);
            while (charCount.size() == uniqueChars.size()) {

                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    minSubString = s.substring(start, end + 1);
                }

                char startChar = s.charAt(start);
                if (charCount.containsKey(startChar)) {
                    charCount.put(startChar, charCount.get(startChar) - 1);
                    if (charCount.get(startChar) == 0) {
                        charCount.remove(startChar);
                    }
                }
                start++;
            }
        }
        return minSubString;
    }
}
