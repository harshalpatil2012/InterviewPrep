package com.practice.array;

public class BuySellMaxProfit {
	static int maxProfit(int price[], int n) {
		int profit = 0;

		for (int i = 1; i < n; i++) {
			if (price[i] > price[i - 1])
				profit += price[i] - price[i - 1];
		}

		return profit;
	}

	public static void main(String args[]) {
		int arr[] = { 7, 8, 8, 6, 12 }, n = 5;

		System.out.println(maxProfit(arr, n));

		int arr2[] = { 7, 8, 8, 6, 12 };
		int n2 = arr2.length;

		System.out.println("Maximum Profit: " + maxProfit(arr2, n2));

	}

	static int maxProfitSellOnce(int price[], int n) {
		if (n <= 1) {
			return 0; // No profit possible with less than 2 prices
		}

		int minPrice = price[0]; // Initialize the minimum price seen so far to the first price
		int maxProfit = 0; // Initialize the maximum profit to 0

		for (int i = 1; i < n; i++) {
			int currentProfit = price[i] - minPrice; // Calculate the profit by selling at the current price

			// Update the maximum profit if the current profit is greater
			if (currentProfit > maxProfit) {
				maxProfit = currentProfit;
			}

			// Update the minimum price if the current price is smaller
			if (price[i] < minPrice) {
				minPrice = price[i];
			}
		}

		return maxProfit;
	}

}
