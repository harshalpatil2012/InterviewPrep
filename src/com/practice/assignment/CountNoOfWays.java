package com.practice.assignment;

public class CountNoOfWays {
    static int i = 0;
    static int sum = 0;

    // Function returns count of ways to cover 'dist'
    static int printCountRec1(int dist, int n) {
        // Base cases
        if (dist < 0 || i == n)
            return 0;
        if (dist == 0)
            return 1;
        i++;
        // Recur for all previous 3 and add the results
        return sum + printCountRec1(dist - i, n);
    }

    // driver program
    public static void main1(String[] args) {
        int dist = 4;
        int n = 3;
        System.out.println(printCountRec1(dist, n));
    }

    // Function returns count of ways to cover 'dist'
    static int printCountRec(int dist) {
        // Base cases
        if (dist < 0)
            return 0;
        if (dist == 0)
            return 1;

        // Recur for all previous 3 and add the results
        return printCountRec(dist - 1) + printCountRec(dist - 2) + printCountRec(dist - 3);
    }

    // driver program
    public static void main(String[] args) {
        int dist = 4;
        System.out.println(printCountRec(dist));
    }
}
