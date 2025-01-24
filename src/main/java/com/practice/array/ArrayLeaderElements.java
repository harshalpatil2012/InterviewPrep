package com.practice.array;

public class ArrayLeaderElements {

	public static void main(String[] args) {

		int[] input = { 5, 8, 12, 5, 12, 6, 2 };

		findLeaderElements(input, input.length);
		int[] input1 = { 5, 8, 12, 5, 12, 6, 2 };
		findLeaderElementsEfficient(input1, input1.length);
	}

	private static void findLeaderElementsEfficient(int[] input, int len) {

		int curLeader = input[len - 1];
		System.out.println("findLeaderElementsEfficient leader is ::" + curLeader);
		for (int i = len - 2; i >= 0; i--) {
			if (curLeader < input[i]) {
				curLeader = input[i];
				System.out.println(" leader is ::" + input[i]);
			}
		}
	}

	private static void findLeaderElements(int[] input, int len) {

		for (int i = 0; i < input.length; i++) {
			boolean flag = false;
			for (int j = i + 1; j < len; j++) {
				if (input[i] <= input[j]) {
					flag = true;
					break;
				}
			}

			if (flag == false) {
				System.out.println(" leader is ::" + input[i]);
			}
		}

	}

}
