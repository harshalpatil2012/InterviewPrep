package com.practice.leetcode.sliding.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindAllAnagrams {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result; // No anagrams possible

        HashMap<Character, Integer> pFreq = new HashMap<>();
        for (char c : p.toCharArray()) {
            pFreq.put(c, pFreq.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, matched = 0;
        HashMap<Character, Integer> windowFreq = new HashMap<>();

        for (; right < s.length(); right++) { // Expand window
            char r = s.charAt(right);
            windowFreq.put(r, windowFreq.getOrDefault(r, 0) + 1);

            if (pFreq.containsKey(r) && windowFreq.get(r).equals(pFreq.get(r))) {
                matched++;
            }

            if (right >= p.length() - 1) { // Shrink window if needed
                if (matched == pFreq.size()) { // Found an anagram
                    result.add(left);
                }

                char l = s.charAt(left);
                if (pFreq.containsKey(l) && windowFreq.get(l).equals(pFreq.get(l))) {
                    matched--;
                }
                windowFreq.put(l, windowFreq.get(l) - 1);
                if (windowFreq.get(l) == 0) windowFreq.remove(l);
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s1 = "cbaebabacd", p1 = "abc";
        System.out.println("Example 1: " + findAnagrams(s1, p1)); // Output: [0, 6]

        String s2 = "abab", p2 = "ab";
        System.out.println("Example 2: " + findAnagrams(s2, p2)); // Output: [0, 1, 2]
    }
}
