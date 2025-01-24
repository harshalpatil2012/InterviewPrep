package com.practice.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class MyRunnable implements Runnable {
    private final long countUntil;

    public MyRunnable(long countUntil) {
        this.countUntil = countUntil;
    }

    @Override
    public void run() {
        long sum = 0;
        for (long i = 1; i < countUntil; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}

public class ExecutorServiceDemo {
    private static final int HTHRDS = 10;

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(HTHRDS);
        ThreadPoolExecutor executor1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(HTHRDS);
        for (int i = 01; i < 50; i++) {
            System.out.println(" executor1:: " + i);
            Runnable myRunnable = new MyRunnable(10);
            executor1.execute(myRunnable);
            executor.submit(myRunnable);
        }
        executor1.shutdown();
        // executor.awaitTermination();
    }
}