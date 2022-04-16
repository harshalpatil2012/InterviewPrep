package codeInterView;

public class StackOverflowErrorExample {

    public static void recursivePrint(int num) {
        System.out.println("Number: " + num);

        if (num == 0)
            return;
        else
            recursivePrint(++num);
    }

    public static void main(String[] args) {
        /*
        		try {
        			StackOverflowErrorExample.recursivePrint(1);
        
        			System.out.println("\n MY handled Exception Error Before::");
        		} catch (RuntimeException e) {
        			System.out.println("\n MY handled Exception Error::" + e);
        		}*/
        System.out.println("reverseRecursively:: " + reverseRecursively("Rec"));
    }

    public static String reverseRecursively(String str) {

        // base case to handle one char string and empty string
        if (str.length() < 2) {
            return str;
        }

        return reverseRecursively(str.substring(1)) + str.charAt(0);

    }

}