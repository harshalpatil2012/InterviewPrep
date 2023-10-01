package com.practice.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		executorService.submit(() -> method1());
		executorService.submit(() -> method2());
		executorService.submit(() -> method3());
		executorService.submit(() -> method4());
		

		// Shutdown the ExecutorService after tasks are submitted
		executorService.shutdown();
		try {
			// Wait for all tasks to complete or timeout after a specific duration
			if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
				// If tasks did not complete within the timeout, force shutdown
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			// (Optional) Handle the interruption if needed
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
