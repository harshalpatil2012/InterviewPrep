package com.practice;

import java.util.HashSet;
import java.util.Set;

public class UniqeString {

    public static void main(String[] args) {

        String input = "BCDAAABCCDABCCDDAC";
        char[] charArray = input.toCharArray();
        Set<String> unique = new HashSet<>();
        for (int i = 0; i <= charArray.length; i++) {
            unique.add(String.valueOf(charArray[i]));
        }

        System.out.println(unique);

    }
}
