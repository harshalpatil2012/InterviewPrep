package codeInterView.thread;

import java.util.concurrent.CountDownLatch;

public class Latch {

    private static class Task implements Runnable {
        private CountDownLatch countDownLatch;
        private int count;

        public Task(CountDownLatch latch, int count) {
            this.countDownLatch = latch;
            this.count = count;
            System.out.println("Inside Task::" + count);
        }

        @Override
        public void run() {

            System.out.println("Inside run method::" + count);
            countDownLatch.countDown();
        }

    }

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t1 = new Thread(new Task(countDownLatch, 1), "T1");
        Thread t2 = new Thread(new Task(countDownLatch, 2), "T2");
        t1.start();
        t2.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
