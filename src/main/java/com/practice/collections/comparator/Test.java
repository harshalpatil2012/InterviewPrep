package com.practice.collections.comparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    // Complete the smallestString function below.
    static String smallestString(List<String> substrings) {
        String[] arr = new String[substrings.size()];
        arr = substrings.toArray(arr);
        return lexsmallest(arr, arr.length);
    }

    static long countMax(List<String> upRight) {

        Integer A[] = new Integer[upRight.size()];
        A = upRight.toArray(A);
        int highestRepeatingElement = -1;
        int maxFrequency = -1;
        java.util.Map<Integer, Integer> freqMap = new java.util.HashMap<Integer, Integer>();

        for (int i = 0; i < A.length; i++) {

            if (freqMap.containsKey(A[i])) {
                freqMap.put(A[i], 1 + freqMap.get(A[i]));
            } else {
                freqMap.put(A[i], 1);
            }
            if (maxFrequency < freqMap.get(A[i])) {
                maxFrequency = freqMap.get(A[i]);
                highestRepeatingElement = A[i];
            }
        }
        System.out.println("frequency table: " + freqMap);
        return highestRepeatingElement;

    }

    static void sort(String a[], int n) {
        // sort the array
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((a[i] + a[j]).compareTo(a[j] + a[i]) > 0) {
                    String s = a[i];
                    a[i] = a[j];
                    a[j] = s;
                }
            }
        }
    }

    static String lexsmallest(String a[], int n) {

        sort(a, n);
        // Concatenating sorted strings
        String str = "";
        for (int i = 0; i < n; i++)
            str += a[i];
        return str;
    }
}
