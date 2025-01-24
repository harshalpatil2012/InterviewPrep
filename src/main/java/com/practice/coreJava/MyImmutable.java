package com.practice.coreJava;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class MyImmutable {

    private final int[] myArray;
    BlockingQueue blocking = new ArrayBlockingQueue<>(2);

    public MyImmutable(int[] anArray) {
        this.myArray = anArray.clone();
        // this.myArray = anArray; // wrong
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("Numbers are: ");
        for (int i = 0; i < myArray.length; i++) {
            sb.append(myArray[i] + " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        int[] array = { 1, 2 };
        MyImmutable myImmutableRef = new MyImmutable(array);
        System.out.println("Before constructing " + myImmutableRef);
        array[1] = 5; // change
                      // (i.e.
                      // mutate)
                      // the
                      // element
        System.out.println("After constructing " + myImmutableRef);
    }
}
// the caller could change the array after calling the constructor.
