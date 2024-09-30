package com.practice.leetcode.greedy;

public class WateringPlants {
    public static void main(String[] args) {
        WateringPlants wp = new WateringPlants();
        int[] plants1 = {2, 2, 3, 3};
        int capacity1 = 5;
        System.out.println("Total steps: " + wp.wateringPlants(plants1, capacity1)); // Output should be 14

        int[] plants2 = {1, 1, 1, 4, 2, 3};
        int capacity2 = 4;
        System.out.println("Total steps: " + wp.wateringPlants(plants2, capacity2)); // Output should be 30

        int[] plants3 = {7, 7, 7, 7, 7, 7, 7};
        int capacity3 = 8;
        System.out.println("Total steps: " + wp.wateringPlants(plants3, capacity3)); // Output should be 49
    }

    private int wateringPlants(int[] plants, int capacity) {

        int steps = 0;
        int remainingCapacity = capacity;

        for (int i = 0; i < plants.length; i++) {
            if (remainingCapacity > plants[i]) {
                remainingCapacity -= plants[i];
                steps++;
            } else {
                steps += (i * 2);
                remainingCapacity = capacity - plants[i];
                steps++;
            }
        }
        return steps;
    }
}
