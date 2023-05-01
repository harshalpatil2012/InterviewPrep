package com.practice.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<Long> {

    @Override
    public Long call() {
        long sum = 0;
        for (long i = 1; i < 5; i++) {
            sum += i;
            if (i == 2)
                throw new RuntimeException();
        }
        System.out.println(sum);
        return sum;
    }
}
/*interface DefaulableFactory {
    // Interfaces now allow static methods
    static Defaulable create( Supplier< Defaulable > supplier ) {
        return supplier.get();
    }
}*/

public class CallableFutureDemo {
    private static final int HTHRDS = 10;

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(HTHRDS);
        List<Future<Long>> list = new ArrayList<Future<Long>>();
        for (int i = 01; i < 5; i++) {

            Callable<Long> callableWorker = new MyCallable();
            Future<Long> future = executor.submit(callableWorker);
            list.add(future);
        }

        int sum = 0;
        System.out.println("Size::" + list.size());
        for (Future<Long> future : list) {
            try {
                sum += future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("SUM::" + sum);
        executor.shutdown();

    }
}