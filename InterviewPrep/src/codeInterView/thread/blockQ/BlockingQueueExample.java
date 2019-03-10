package codeInterView.thread.blockQ;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(4, true);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Producer producer2 = new Producer(queue);
        Consumer consumer2 = new Consumer(queue);
        Thread t1 = new Thread(producer);
        t1.start();
        Thread t2 = new Thread(consumer);
        t2.start();

        Thread t11 = new Thread(producer2);
        t11.start();
        Thread t22 = new Thread(consumer2);
        t22.start();
        try {
            t1.join();

            t11.join();

            t2.join();
            t22.join();

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
