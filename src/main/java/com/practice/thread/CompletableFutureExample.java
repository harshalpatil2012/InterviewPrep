package com.practice.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
	public static void main(String[] args) {
		CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> method1());
		CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> method2());
		CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> method3());
		CompletableFuture<Void> future4 = CompletableFuture.runAsync(() -> method4());

		// Combine all CompletableFuture instances and wait for them to complete
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3, future4);

		// Wait for all tasks to complete and handle any exceptions
		try {
			allFutures.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void method1() {
		System.out.println("Method 1 is called!");
	}

	public static void method2() {
		System.out.println("Method 2 is called!");
	}

	public static void method3() {
		System.out.println("Method 3 is called!");
	}

	public static void method4() {
		System.out.println("Method 4 is called!");
	}
}
