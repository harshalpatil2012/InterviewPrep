package com.practice.leetcode;

public class GasStation {

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};

        System.out.println(" starting station is " + canComplete(gas, cost));
    }

    private static int canComplete(int[] gas, int[] cost) {

        int currentSurplus = 0;
        int totalSurplus = 0;
        int startingStation = 0;

        for (int i = 0; i < gas.length; i++) {

            currentSurplus += gas[i] - cost[i];
            totalSurplus += gas[i] - cost[i];
            if (totalSurplus < 0) {
                startingStation = i + 1;
                totalSurplus = 0;
            }
        }
        return totalSurplus > 0 ? startingStation : -1;
    }
}