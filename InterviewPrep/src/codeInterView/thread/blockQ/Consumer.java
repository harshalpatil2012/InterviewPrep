package codeInterView.thread.blockQ;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    BlockingQueue blockingQueue = null;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;

    }

    @Override
    public void run() {
        try {

            System.out.println("Taking item from block queue::" + blockingQueue.take());
            // Thread.sleep(1000);
            System.out.println("Taking item from block queue::" + blockingQueue.take());
            // Thread.sleep(500);
            System.out.println("Taking item from block queue::" + blockingQueue.take());
            // Thread.sleep(1000);
            System.out.println("Taking item from block queue::" + blockingQueue.take());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
