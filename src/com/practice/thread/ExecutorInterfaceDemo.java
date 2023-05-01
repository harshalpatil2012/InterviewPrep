package com.practice.thread;

import java.util.concurrent.Executor;

public class ExecutorInterfaceDemo {
    public static void main(String[] args) {
        Executor executor = new MyExecutor();
        executor.execute(new Task1());
        executor.execute(new Task2());

        // Normal THread execution
        new Thread(new Task1()).start();
        new Thread(new Task2()).start();
    }

}

class MyExecutor implements Executor {

    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}

class Task1 implements Runnable {
    @Override
    public void run() {
        System.out.println("Running from task1");
    }
}

class Task2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Running from task2");
    }
}