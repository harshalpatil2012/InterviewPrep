package com.practice.ds.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class MostFrequentNumberStack {
	private Stack<Integer> elementsStack;
	private Map<Integer, Integer> elementFrequencyMap;

	public MostFrequentNumberStack() {
		elementsStack = new Stack<>();
		elementFrequencyMap = new HashMap<>();
	}

	public void push(int num) {
		elementsStack.push(num);

		// Update the frequency of the element in the map
		int frequency = elementFrequencyMap.getOrDefault(num, 0) + 1;
		elementFrequencyMap.put(num, frequency);
	}

	public int pop() {
		if (!elementsStack.isEmpty()) {
			int poppedElement = elementsStack.pop();

			// Update the frequency of the popped element in the map
			int frequency = elementFrequencyMap.get(poppedElement) - 1;
			elementFrequencyMap.put(poppedElement, frequency);

			return poppedElement;
		}
		throw new IllegalStateException("Stack is empty.");
	}

	public int getMostFrequentNumberLambda() {
			if (!elementFrequencyMap.isEmpty()) {
				// Find the most frequent number using lambda expression and array
				int[] mostFrequentNumber = { 0 };
				int[] maxFrequency = { Integer.MIN_VALUE };

				elementFrequencyMap.forEach((num, frequency) -> {
					if (frequency > maxFrequency[0]) {
						maxFrequency[0] = frequency;
						mostFrequentNumber[0] = num;
					}
				});

				return mostFrequentNumber[0];
			}
			throw new IllegalStateException("Stack is empty.");
		}

	public int getMostFrequentNumber() {
		if (!elementFrequencyMap.isEmpty()) {
			// Find the most frequent number by traversing the frequency map
			int maxFrequency = Integer.MIN_VALUE;
			int mostFrequentNumber = 0;

			for (Map.Entry<Integer, Integer> entry : elementFrequencyMap.entrySet()) {
				int num = entry.getKey();
				int frequency = entry.getValue();

				if (frequency > maxFrequency) {
					maxFrequency = frequency;
					mostFrequentNumber = num;
				}
			}

			return mostFrequentNumber;
		}
		throw new IllegalStateException("Stack is empty.");
	}

	public static void main(String[] args) {
		MostFrequentNumberStack stack = new MostFrequentNumberStack();
		stack.push(1);
		stack.push(2);
		stack.push(1);
		stack.push(3);
		stack.push(2);
		stack.push(2);

		System.out.println("Most frequent number: " + stack.getMostFrequentNumberLambda()); // Output: 2
	}
}
