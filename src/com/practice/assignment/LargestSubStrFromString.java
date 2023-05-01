package com.practice.assignment;

import java.util.LinkedHashMap;

/**
 * Write a java program or function to find the longest substring without
 * repeating characters in a given string. For example, if
 * “javaconceptofthedayy” is the input string, then the longest substring
 * without repeating or duplicate characters is “oftheday” and its length is 8.
 * 
 * @author harshal
 */
public class LargestSubStrFromString {

    static void longestSubstring(String inputString) {
        // Convert inputString to charArray

        char[] charArray = inputString.toCharArray();

        // Initialization

        String longestSubstring = null;

        int longestSubstringLength = 0;

        // Creating LinkedHashMap with characters as keys and their position as
        // values.

        LinkedHashMap<Character, Integer> charPosMap = new LinkedHashMap<Character, Integer>();

        // Iterating through charArray

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];

            // If ch is not present in charPosMap, adding ch into charPosMap
            // along with its position
            System.out.println("If I::" + i);
            if (!charPosMap.containsKey(ch)) {
                charPosMap.put(ch, i);
            } else {
                // If ch is already present in charPosMap, repositoning the
                // cursor i
                // to the position of ch and clearing the charPosMap
                // Updating longestSubstring and longestSubstringLength

                if (charPosMap.size() > longestSubstringLength) {
                    longestSubstringLength = charPosMap.size();
                    longestSubstring = charPosMap.keySet()
                        .toString();
                }
                i = charPosMap.get(ch);
                System.out.println("else I::" + i);
                charPosMap.clear();
            }

        }

        System.out.println("Input String : " + inputString);

        System.out.println("The longest substring : " + longestSubstring);

        System.out.println("The longest Substring Length : " + longestSubstringLength);
    }

    public static void main(String[] args) {
        longestSubstring("javaconceptofthedayy");
    }
}