package codeInterView.assignment;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class FirstUniqueCharInString {
    public static void main(String[] args) {
        String str = "aabbcefgjutejjtt";
        System.out.println("First unique::" + firstUnique(str.toCharArray()));
    }

    public static char firstUnique(char[] stream) {
        HashSet<Character> seen = new HashSet<Character>();
        LinkedHashSet<Character> uniques = new LinkedHashSet<Character>();

        for (int i = 0; i < stream.length; i++) {
            char c = Character.toLowerCase(stream[i]);
            if (!seen.contains(c)) {
                seen.add(c);
                uniques.add(c);
            } else {
                uniques.remove(c);
            }
        }
        if (uniques.size() > 0) {
            return uniques.iterator()
                .next();
        } else
            return '\0';
    }
}
