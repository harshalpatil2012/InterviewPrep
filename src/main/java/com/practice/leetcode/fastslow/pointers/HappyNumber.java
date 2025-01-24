package com.practice.leetcode.fastslow.pointers;

/**
 * 202. Happy Number
 * https://leetcode.com/problems/happy-number/
 * Any number will be called a happy number if, after repeatedly replacing it with a number equal to
 * the sum of the square of all of its digits, leads us to number ‘1’. All other (not-happy) numbers
 * will never reach ‘1’. Instead, they will be stuck in a cycle of numbers which does not include ‘1’
 * Time Complexity: O(log n). The number of steps is proportional to the number of digits in n, which is approximately log n steps.
 * Space Complexity: O(1). We use a constant amount of space for the variables and calculations.
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        } while (fast != slow);
        return slow == 1;
    }

    private int getNext(int n) {
        int totalSum = 0;

        while (n > 0) {
            int digit = n % 10;
            totalSum += digit * digit;
            n = n / 10;
        }
        return totalSum;
    }

    public static void main(String[] args) {
        HappyNumber happyNumber = new HappyNumber();
        int[] testNumbers = {19, 2, 7, 20};

        for (int num : testNumbers) {
            if (happyNumber.isHappy(num)) {
                System.out.println(num + " is a happy number.");
            } else {
                System.out.println(num + " is not a happy number.");
            }
        }
    }

   /* private boolean isHappy(int num) {

        int slow = num, fast = num;

        do (slow) {

        }
        while (fast != slow)

            return slow == 1;
    }*/


}