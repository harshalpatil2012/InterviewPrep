package com.practice.string;

public class StringCompression {
    public static String compress(String str) {
        StringBuilder result = new StringBuilder();
        int i;
        int count = 0;
        for (i = 0; i < str.length() - 1; i++, count++) {
            if (str.charAt(i) != str.charAt(i + 1)) {
                result.append(str.charAt(i))
                    .append(count);
                count = 0;
            }
        }
        result.append(str.charAt(i))
            .append(count);
        return result.toString();
    }

    public static String compressStr(String inStr) {
        for (int i = 0; i < inStr.length(); i++) {
            int j = i + 1;
            int duplicateCharCount = 1;
            while (j < inStr.length() && inStr.charAt(i) == inStr.charAt(j)) {
                j++;
                duplicateCharCount++;
            }
            inStr = inStr.substring(0, i + 1) + duplicateCharCount + inStr.substring(i + duplicateCharCount);
            i++;
        }
        return inStr;
    }

    public static void main(String[] args) {
        String string = "aaassssdddaaaggghhhfgreeeeeeedrrrrr";
        String x = compress(string);
        System.out.println(" Output string::" + x);

        String inStr = "aaabbccaaaddj";
        System.out.println("Input str :: " + inStr);

        System.out.println("Input str :: " + compressStr(inStr));
    }
}