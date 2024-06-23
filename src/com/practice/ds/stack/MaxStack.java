package com.practice.ds.stack;

import java.util.Stack;

//java program to get the max value element using stack
class MaxStack {
    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> maxStack = new Stack<>();

    public void push(int value) {
        mainStack.push(value);

        // Update the maxStack with the new maximum value
        if (maxStack.isEmpty() || value >= maxStack.peek()) {
            maxStack.push(value);
        }
    }

    public void pop() {
        if (!mainStack.isEmpty()) {
            int poppedValue = mainStack.pop();

            // If the popped value is the current maximum, pop it from the maxStack too
            if (poppedValue == maxStack.peek()) {
                maxStack.pop();
            }
        }
    }

    public int getMax() {
        if (!maxStack.isEmpty()) {
            return maxStack.peek();
        }
        throw new IllegalStateException("Stack is empty");
    }
}

class MaxStackApp {
    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();

        // Push elements onto the stack
        maxStack.push(5);
        maxStack.push(2);
        maxStack.push(10);
        maxStack.push(7);

        // Get the maximum value in the stack
        System.out.println("Max Value: " + maxStack.getMax()); // Should print "Max Value: 10"

        // Pop an element from the stack
        maxStack.pop();

        // Get the new maximum value in the stack
        System.out.println("Max Value: " + maxStack.getMax()); // Should print "Max Value: 10"
    }
}
