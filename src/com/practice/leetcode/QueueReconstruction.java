package com.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 406. Queue Reconstruction by Height," we need to reconstruct a queue from a set
 * of people where each person is described by their height and the number of people
 * in front of them who are of equal or greater height. The goal is to return the queue
 * in such a way that each person's requirements are satisfied.
 */
public class QueueReconstruction {
    public static void main(String[] args) {
        int[][] people = {{5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}};


        int[][] result = reconstruct(people);
    }

    private static int[][] reconstruct(int[][] people) {

        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else {
                return a[1] - b[1];
            }
        });

        List<int[]> list = new ArrayList<>();
        for (int[] p : people) {
            // List.add(index, element) shifts the element currently at that position
            // (if any) and any subsequent elements to the right
            list.add(p[1], p);
        }
        return people;
    }
}
