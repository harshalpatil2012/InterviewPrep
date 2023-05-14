package com.practice.dataStructure.arrayList;

import java.util.*;

/**
 * Problem: Write own implementation of array list in Java
 * 
 * In this implementation, we use an array data to store the elements of the
 * list, along with variables size and capacity to keep track of the number of
 * elements and the maximum number of elements the array can hold before needing
 * to be resized. The constructor initializes the array with a default capacity
 * of 10, and the add() method adds an element to the end of the array and
 * increases its size. If the array is full, the increaseCapacity() method is
 * called to double its capacity.
 * 
 * The get() method retrieves an element at a given index, throwing an exception
 * if the index is out of bounds. The remove() method removes an element at a
 * given index and shifts all subsequent elements down to fill the gap, also
 * throwing an exception if the index is out of bounds. The size() method
 * returns the current size of the array.
 * 
 * Note that this is a basic implementation and does not include all of the
 * features of a standard Java ArrayList.
 * 
 * 
 * @author harshal
 *
 * @param <T>
 */
public class MyArrayList<T> {
	private Object[] data;
	private int size;
	private int capacity;

	public MyArrayList() {
		capacity = 10;
		data = new Object[capacity];
	}

	public void add(T element) {
		if (size == capacity) {
			increaseCapacity();
		}
		data[size++] = element;
	}

	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
		}
		return (T) data[index];
	}

	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
		}
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		data[--size] = null;
	}

	public int size() {
		return size;
	}

	private void increaseCapacity() {
		capacity *= 2;
		data = Arrays.copyOf(data, capacity);
	}
}
