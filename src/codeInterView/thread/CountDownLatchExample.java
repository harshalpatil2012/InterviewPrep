package codeInterView.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    private static class Task implements Runnable {

        private CountDownLatch countDownLatch;
        boolean waitFlag;

        public Task(CountDownLatch countDownLatch, boolean waitFlag) {
            this.countDownLatch = countDownLatch;
            this.waitFlag = waitFlag;
        }

        public void run() {
            long startTime = System.currentTimeMillis();
            System.out.println("Running inside thread " + Thread.currentThread()
                .getName() + " Waiting on CountDownLatch");

            if (waitFlag == true)
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            countDownLatch.countDown();
            System.out.println(Thread.currentThread()
                .getName() + " has crossed the CountDownLatch  total execution time" + (System.currentTimeMillis() - startTime));

            // countDownLatch.countDown();
            // System.out.println(Thread.currentThread().getName() + " has crossed the CountDownLatch twice");
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        final CountDownLatch latch = new CountDownLatch(3);
        // final CyclicBarrier cBarrier = new CyclicBarrier(3);
        Thread t1 = new Thread(new Task(latch, false), "Thread 1");
        Thread t2 = new Thread(new Task(latch, false), "Thread 2");
        Thread t3 = new Thread(new Task(latch, true), "Thread 3");
        // Semaphore sem = new Semaphore(permits)
        t1.start();
        t2.start();
        t3.start();
        try {
            latch.await();
        } catch (InterruptedException e) {

        }
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread()
            .getName() + "CountDownLatch  total execution time::" + (System.currentTimeMillis() - startTime));
        System.out.println(Thread.currentThread()
            .getName() + " In Main method");
    }
}
