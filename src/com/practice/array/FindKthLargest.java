package com.practice.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Find kth smallest or largest number
 * 
 * @author harshal
 *
 */
public class FindKthLargest {

	public static void main(String[] args) {
		int arr[] = { 7, 4, 6, 3, 9, 1 };
		System.out.println(" findKthLargest element::" + findKthLargest(arr, 2));
		System.out.println("\n");
		System.out.println(" findKthSmall element::" + findKthSmall(arr, 3));

		List<Integer> A = Arrays.asList(7, 4, 6, 3, 9, 1);
		int k = 3;

		System.out.println("K'th smallest element in the array is " + findKthSmallest(A, k));

		// how to find the kth smallest number in an array using the quickselect
		// algorithm:
		int kthSmallest = kthSmallest(arr, k); // returns 4
		System.out.println("K'th smallest element in the array is " + kthSmallest);

		kthSmallest = kthSmallestUsingBinary(arr, k); // returns 4
		System.out.println("K'th smallest element in the array is " + kthSmallest);

		Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 80);
        scores.put("Charlie", 70);
        scores.put("abc", 100); // Adding an entry with key "abc"

        String targetKey = "abc";

        // Using lambda expression to iterate over the entries and check the target key
        scores.forEach((name, score) -> {
            if (name.equals(targetKey)) {
                System.out.println("Found key " + targetKey + ": " + score);
            }
        });
	}

	public static int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(k);
		for (int n : nums) {
			q.offer(n);
			System.out.println("findKthLargest(): element inserted into the array " + n);
			if (q.size() > k) {

				int r = q.poll();
				System.out.println("findKthLargest(): element removed from the array " + r);
			}
		}
		return q.peek();
	}

	public static int findKthSmall(int[] nums, int k) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(Comparator.reverseOrder());
		for (int n : nums) {
			q.offer(n);
			System.out.println("findKthSmall():element inserted into the array " + n);
			if (q.size() > k) {

				int r = q.poll();
				System.out.println("findKthSmall():element removed from the array " + r);
			}
		}
		return q.peek();
	}

	// Function to find the K'th smallest element in the
	// array using max-heap
	public static int findKthSmallest(List<Integer> A, int k) {
		// create an max-heap using PriorityQueue class and
		// insert first k elements of the array into the heap
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		pq.addAll(A.subList(0, k));

		// do for remaining array elements
		for (int i = k; i < A.size(); i++) {
			// if current element is less than the root of the heap
			if (A.get(i) < pq.peek()) {
				// replace root with the current element
				pq.poll();
				pq.add(A.get(i));
			}
		}
		// return the root of max-heap
		return pq.peek();
	}

	public static int kthSmallest(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			throw new IllegalArgumentException("k is out of range.");
		}
		return quickSelect(arr, 0, arr.length - 1, k);
	}

	private static int quickSelect(int[] arr, int left, int right, int k) {
		if (left == right) {
			return arr[left];
		}
		int pivotIndex = partition(arr, left, right);
		if (k == pivotIndex + 1) {
			return arr[pivotIndex];
		} else if (k < pivotIndex + 1) {
			return quickSelect(arr, left, pivotIndex - 1, k);
		} else {
			return quickSelect(arr, pivotIndex + 1, right, k);
		}
	}

	private static int partition(int[] arr, int left, int right) {
		int pivotValue = arr[right];
		int pivotIndex = left;
		for (int i = left; i < right; i++) {
			if (arr[i] < pivotValue) {
				int temp = arr[i];
				arr[i] = arr[pivotIndex];
				arr[pivotIndex] = temp;
				pivotIndex++;
			}
		}
		int temp = arr[right];
		arr[right] = arr[pivotIndex];
		arr[pivotIndex] = temp;
		return pivotIndex;
	}

	public static int kthSmallestUsingBinary(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			throw new IllegalArgumentException("k is out of range.");
		}
		int left = Integer.MIN_VALUE;
		int right = Integer.MAX_VALUE;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			int count = countLessThan(arr, mid);
			if (count == k - 1) {
				return mid;
			} else if (count < k - 1) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

	private static int countLessThan(int[] arr, int value) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < value) {
				count++;
			}
		}
		return count;
	}

}