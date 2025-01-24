package com.practice.leetcode.greedy;

public class WateringPlantsII {
    public static void main(String[] args) {
        WateringPlantsII wp = new WateringPlantsII();
        int[] plants1 = {2, 2, 3, 3};
        int capacityA1 = 5, capacityB1 = 5;
        System.out.println("Total refills: " + wp.minimumRefill(plants1, capacityA1, capacityB1)); // Output: 1

        int[] plants2 = {2, 2, 3, 3};
        int capacityA2 = 3, capacityB2 = 4;
        System.out.println("Total refills: " + wp.minimumRefill(plants2, capacityA2, capacityB2)); // Output: 2

        int[] plants3 = {5};
        int capacityA3 = 10, capacityB3 = 8;
        System.out.println("Total refills: " + wp.minimumRefill(plants3, capacityA3, capacityB3)); // Output: 0
    }

    private int minimumRefill(int[] plants, int capacityA, int capacityB) {

        int aWaterCapacity = capacityA;
        int bWaterCapacity = capacityB;
        int aliceFills = 0, bobFills = 0;
        int i = 0;
        int j = plants.length - 1;
        while (i <= j) {
            // alice and bob meets
            if (i == j) {
                if (aWaterCapacity > plants[i] || bWaterCapacity > plants[j]) {
                    return aliceFills + bobFills;
                } else {
                    aWaterCapacity = capacityA - plants[i];
                    aliceFills++;
                    return aliceFills + bobFills;
                }
            }
            //alice fills
            if (aWaterCapacity > plants[i]) {
                aWaterCapacity -= plants[i];
            } else {
                aWaterCapacity = capacityA - plants[i];
                aliceFills++;
            }
            i++;
            //bob fills
            if (bWaterCapacity > plants[j]) {
                bWaterCapacity -= plants[j];
            } else {
                bWaterCapacity = capacityB - plants[j];
                bobFills++;
            }
            j--;
        }
        return aliceFills + bobFills;
    }
}
