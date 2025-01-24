package com.practice.array;

public class ShiftZeroToRight {

	public static void main(String[] args) {
		int input[] = { 2, 5, 0, 6, 0, 7, 79, 0, 77 };

		shiftToRight(input, input.length);
		System.out.println(" impl1 shift O/P");
		for (int i = 0; i < input.length; i++) {
			System.out.println(input[i]);
		}

		int input1[] = { 2, 5, 0, 6, 0, 7, 79, 0, 77 };

		pushZerosToEnd(input1, input1.length);

		System.out.println(" efficientShift O/P");
		for (int i = 0; i < input1.length; i++) {
			System.out.println(input1[i]);
		}
	}

	// Function which pushes all zeros to end of an array.
	static void pushZerosToEnd(int arr[], int n) {
		int count = 0; // Count of non-zero elements

		// Traverse the array. If element encountered is
		// non-zero, then replace the element at index 'count'
		// with this element
		for (int i = 0; i < n; i++)
			if (arr[i] != 0)
				arr[count++] = arr[i]; // here count is
										// incremented

		// Now all non-zero elements have been shifted to
		// front and 'count' is set as index of first 0.
		// Make all elements 0 from count to end.
		
		System.out.println("  count::"+ count);
		while (count < n)
			arr[count++] = 0;
	}

	private static void shiftToRight(int[] input, int length) {

		for (int i = 0; i < input.length; i++) {
			if (input[i] == 0) {
				for (int j = i + 1; j < length; j++) {
					if (input[j] != 0) {
						int temp = input[i];
						input[i] = input[j];
						input[j] = temp;
					}
				}
			}

		}
	}

}
