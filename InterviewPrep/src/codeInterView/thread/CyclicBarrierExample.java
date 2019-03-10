package codeInterView.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    private static class Task implements Runnable {

        private CyclicBarrier cyclicBarrier;
        boolean waitFlag;

        public Task(CyclicBarrier cyclicBarrier, boolean waitFlag) {
            this.cyclicBarrier = cyclicBarrier;
            this.waitFlag = waitFlag;
        }

        public void run() {
            long startTime = System.currentTimeMillis();
            System.out.println("Running inside thread " + Thread.currentThread()
                .getName() + " Waiting on cyclic Bariier");

            try {
                System.out.println(Thread.currentThread()
                    .getName() + " before  barrier");
                if (waitFlag == true)
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                cyclicBarrier.await();

                System.out.println(Thread.currentThread()
                    .getName() + " has crossed the barrier :: total execution time" + (System.currentTimeMillis() - startTime));
            } catch (InterruptedException e) {

                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        final CyclicBarrier cBarrier = new CyclicBarrier(3, new Runnable() {

            @Override
            public void run() {
                System.out.println("All parties arrived at Barrier");

            }
        });

        // final CyclicBarrier cBarrier = new CyclicBarrier(3);
        Thread t1 = new Thread(new Task(cBarrier, false), "Thread 1");
        Thread t2 = new Thread(new Task(cBarrier, false), "Thread 2");
        Thread t3 = new Thread(new Task(cBarrier, true), "Thread 3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread()
            .getName() + "  CyclicBarrier  total execution time::::::::::::::::::::::" + (System.currentTimeMillis() - startTime));

        /*cBarrier.reset();
        t1 = new Thread(new Task(cBarrier, false), "Thread 1");
        t2 = new Thread(new Task(cBarrier, false), "Thread 2");
        t3 = new Thread(new Task(cBarrier, true), "Thread 3");
        t1.start();
        t2.start();
        t3.start();*/

    }
}
