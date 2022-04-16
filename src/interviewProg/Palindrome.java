package interviewProg;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Palindrome {
    public static void main(String args[]) {

        System.out.println("Please Enter a number : ");
        int palindrome = 98789;

        if (isPalindrome(palindrome)) {
            System.out.println("Number : " + palindrome + " is a palindrome");
        } else {
            System.out.println("Number : " + palindrome + " is not a palindrome");
        }

        String palindromeStr = "abcdcba";

        if (isPalindrome(palindromeStr)) {
            System.out.println("palindromeStr : " + palindromeStr + " is a palindrome");
        } else {
            System.out.println("palindromeStr : " + palindromeStr + " is not a palindrome");
        }

    }

    public static boolean isPalindrome(String text) {
        String clean = text.replaceAll("\\s+", "")
            .toLowerCase();
        int length = clean.length();
        int forward = 0;
        int backward = length - 1;
        while (backward > forward) {
            char forwardChar = clean.charAt(forward++);
            char backwardChar = clean.charAt(backward--);
            if (forwardChar != backwardChar)
                return false;
        }
        return true;
    }

    public boolean isPalindromeReverseTheString(String text) {
        StringBuilder reverse = new StringBuilder();
        String clean = text.replaceAll("\\s+", "")
            .toLowerCase();
        char[] plain = clean.toCharArray();
        for (int i = plain.length - 1; i >= 0; i--) {
            reverse.append(plain[i]);
        }
        return (reverse.toString()).equals(clean);
    }

    public boolean isPalindromeUsingIntStream(String text) {
        String temp = text.replaceAll("\\s+", "")
            .toLowerCase();
        return IntStream.range(0, temp.length() / 2)
            .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - i - 1));
    }
    
    private boolean recursivePalindrome(String text, int forward, int backward) {
        if (forward == backward) {
            return true;
        }
        if ((text.charAt(forward)) != (text.charAt(backward))) {
            return false;
        }
        if (forward < backward + 1) {
            return recursivePalindrome(text, forward + 1, backward - 1);
        }
     
        return true;
    }
    

    /*
     * Java method to check if number is palindrome or not
     */
    public static boolean isPalindrome(int number) {
        int palindrome = number; // copied number into variable
        int reverse = 0;

        while (palindrome != 0) {
            int remainder = palindrome % 10;
            reverse = reverse * 10 + remainder;
            palindrome = palindrome / 10;
            System.out.println("remainder::" + remainder + " reverse::" + reverse + " palindrome::" + palindrome);
        }
        // if original and reverse of number is equal means
        // number is palindrome in Java
        if (number == reverse) {
            return true;
        }
        return false;
    }

}
