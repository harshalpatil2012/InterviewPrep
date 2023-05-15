package com.practice.array;

/*
 * Before Rotation
1 2 3 4 5 
After Rotation
3 4 5 1 2 
*/

public class RotateArraryByNDigit {
	static void leftRotate(int arr[], int d, int n) {
		int temp[] = new int[d];

		for (int i = 0; i < d; i++) {
			temp[i] = arr[i];
		}

		for (int i = d; i < n; i++) {
			arr[i - d] = arr[i];
		}

		for (int i = 0; i < d; i++) {
			arr[n - d + i] = temp[i];
		}
	}

	public static void main(String args[]) {
		int arr[] = { 1, 2, 3, 4, 5 }, n = 5, d = 2;

		System.out.println("Before Rotation");

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();

		leftRotateEfficiently(arr, d, n);

		System.out.println("After Rotation");

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}

	}

	static void leftRotateEfficiently(int arr[], int d, int n) {
		reverse(arr, 0, d - 1);

		reverse(arr, d, n - 1);

		reverse(arr, 0, n - 1);

	}

	static void reverse(int arr[], int low, int high) {
		while (low < high) {
			int temp = arr[low];
			arr[low] = arr[high];
			arr[high] = temp;

			low++;
			high--;
		}
	}

	public static void main2(String args[]) {
		int arr[] = { 1, 2, 3, 4, 5 }, n = 5, d = 2;

		System.out.println("Before Rotation");

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();

		leftRotate(arr, d, n);

		System.out.println("After Rotation");

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}

	}

}
