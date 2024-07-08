package com.practice.leetcode.two.pointers;

public class BackspaceStringCompare {

    public static void main(String[] args) {
        // Test Cases with hardcoded inputs
        String[] testCases = {
                "ab#c", "ad#c", // True
                "ab##", "c#d#", // True
                "a#c", "b",     // False
                "a##c", "#a#c"  // True
        };

        for (int i = 0; i < testCases.length; i += 2) {
            String s = testCases[i];
            String t = testCases[i + 1];
            boolean isEqual = backspaceCompare(s, t);
            System.out.printf("Test Case %d: s = \"%s\", t = \"%s\" -> %b\n",
                    i / 2 + 1, s, t, isEqual);
        }
    }

    public static boolean backspaceCompare(String s, String t) {
        int sPointer = s.length() - 1; // Pointer for the end of string s
        int tPointer = t.length() - 1; // Pointer for the end of string t

        while (sPointer >= 0 || tPointer >= 0) { // Continue until both pointers reach the start
            sPointer = getNextValidCharIndex(s, sPointer); // Get the next valid character index in s
            tPointer = getNextValidCharIndex(t, tPointer); // Get the next valid character index in t

            if (sPointer >= 0 && tPointer >= 0 && s.charAt(sPointer) != t.charAt(tPointer)) {
                return false; // Mismatch found, strings are not equal
            }

            if ((sPointer >= 0) != (tPointer >= 0)) {
                return false; // Only one string still has valid characters left
            }
            sPointer--; // Move both pointers left
            tPointer--;
        }
        return true; // Both strings are empty after processing backspaces, so they are equal
    }

    private static int getNextValidCharIndex(String str, int index) {
        int skip = 0; // Counter for the number of backspaces

        while (index >= 0) {
            if (str.charAt(index) == '#') {
                skip++; // Increment backspace counter for each backspace character
            } else if (skip > 0) {
                skip--; // Decrement backspace counter for each valid character to skip
            } else {
                break; // Valid character found
            }
            index--; // Move to the next character to the left
        }
        return index; // Return the index of the next valid character, or -1 if none
    }
}
