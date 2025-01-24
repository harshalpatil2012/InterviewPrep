package com.practice.leetcode.merge.intervals;

/**
 * 1094. Car Pooling
 * There is a car with capacity empty seats. The vehicle only drives east
 * (i.e., it cannot turn around and drive west).
 * <p>
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi]
 * indicates that the ith trip has numPassengersi passengers and the locations to pick them up
 * and drop them off are fromi and toi respectively. The locations are given as the number of kilometers
 * due east from the car's initial location.
 * Return true if it is possible to pick up and drop off all
 * passengers for all the given trips, or false otherwise.
 * Example 1:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 */
public class CarPooling {
    public static void main(String[] args) {
        int[][] trips1 = {{2, 1, 5}, {3, 3, 7}};
        int capacity1 = 4;
        System.out.println("Can the carpool handle all trips with capacity " + capacity1 + "? " + carPooling(trips1, capacity1)); // false

        int[][] trips2 = {{2, 1, 5}, {3, 3, 7}};
        int capacity2 = 5;
        System.out.println("Can the carpool handle all trips with capacity " + capacity2 + "? " + carPooling(trips2, capacity2)); // true
    }

    private static boolean carPooling(int[][] trips1, int capacity1) {

        int[] passengerChange  = new int[1000];

        for (int[] trip : trips1) {
            int numPassengers = trip[0];
            int start = trip[1];
            int end = trip[2];

            passengerChange [start] += numPassengers;
            passengerChange [end] -= numPassengers;
        }
        int passengersSum = 0;
        for (int passenger : passengerChange ) {

            if (passengersSum > capacity1) {
                return false;
            }
            passengersSum += passenger;
        }
        return true;
    }
}
