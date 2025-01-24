package com.practice.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TwoRepeatingNumbers {
    public static void main(String[] args) {
        findThem(new int[] { 1, 7, 8, 9, 1, 8 });
    }

    public static void findThem(int[] a) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> al = new ArrayList<Integer>();

        for (int i = 0; i < a.length; i++) {
            Integer freq = map.get(a[i]);
            map.put(a[i], (freq == null) ? 1 : freq + 1);
            if (freq == null) {
                System.out.println("adding: " + a[i]);
                al.add(a[i]);
            } else {
                System.out.println("Removing: " + a[i]);
                al.remove(new Integer(a[i]));
            }
        }

        System.out.println("Two number are:  " + al);
    }
}
