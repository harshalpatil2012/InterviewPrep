package codeInterView.thread.blockQ;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<String> queue = null;

    Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put("Item 1");
            System.out.println("Adding Item to blocking queue 1..");
            // Thread.sleep(1000);
            if (queue.isEmpty())
                Thread.sleep(1000);
            queue.put("Item 2");
            System.out.println("Adding Item to blocking queue 2..");
            if (queue.isEmpty())
                Thread.sleep(1000);
            queue.put("Item 3");
            System.out.println("Adding Item to blocking queue 3..");
            if (queue.isEmpty())
                Thread.sleep(1000);
            queue.put("Item 4");
            System.out.println("Adding Item to blocking queue 4..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
