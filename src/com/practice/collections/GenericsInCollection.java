package com.practice.collections;

public class GenericsInCollection<T> {

    public static void main(String[] args) {

        // Create arrays of Integer, Double and Character
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        System.out.println("Array integerArray contains:");
        printArray(intArray); // pass an Integer array

        System.out.println("\nArray doubleArray contains:");
        printArray(doubleArray); // pass a Double array

        System.out.println("\nArray characterArray contains:");
        printArray(charArray); // pass a Character array
    }

    public static <T> void printArray(T[] inArray) {
        for (T element : inArray) {
            System.out.println("element	::" + element);
        }
        System.out.println(" ");
    }

}
