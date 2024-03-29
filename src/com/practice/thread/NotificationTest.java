package com.practice.thread;

/**
 * Java program to demonstrate How to use notify and notifyAll method in Java
 * and How to notify and notifyAll method notifies thread, which thread gets
 * woke up etc.
 */
public class NotificationTest {

    private volatile boolean go = false;

    public static void main(String args[]) throws InterruptedException {
        final NotificationTest test = new NotificationTest();

        Runnable waitTask = new Runnable() {

            @Override
            public void run() {
                try {
                    test.shouldGo();
                } catch (InterruptedException ex) {
                    System.out.println("InterruptedException::" + ex.toString());
                }
                System.out.println(Thread.currentThread() + " finished Execution");
            }
        };

        Runnable notifyTask = new Runnable() {

            @Override
            public void run() {
                test.go();
                System.out.println(Thread.currentThread() + " finished Execution");
            }
        };

        Thread t1 = new Thread(waitTask, "WT1"); // will wait
        Thread t2 = new Thread(waitTask, "WT2"); // will wait
        Thread t3 = new Thread(waitTask, "WT3"); // will wait
        Thread t4 = new Thread(notifyTask, "NT1"); // will notify
        t3.setPriority(10);

        // starting all waiting thread
        t1.start();
        t2.start();
        t3.start();

        // pause to ensure all waiting thread started successfully
        Thread.sleep(200);

        // starting notifying thread
        t4.start();

    }

    /*
     * wait and notify can only be called from synchronized method or bock
     */
    private synchronized void shouldGo() throws InterruptedException {
        while (go != true) {
            System.out.println(Thread.currentThread() + " is going to wait on this object");
            wait(); // release lock and reacquires on wakeup
            System.out.println(Thread.currentThread() + " is woken up");
        }
        go = false; // resetting condition
    }

    /*
     * both shouldGo() and go() are locked on current object referenced by
     * "this" keyword
     */
    private synchronized void go() {
        while (go == false) {
            System.out.println(Thread.currentThread() + " is going to notify all or one thread waiting on this object");

            go = true; // making condition true for waiting thread
            // notify(); // only one out of three waiting thread WT1, WT2,WT3 will woke up
            notifyAll(); // all waiting thread WT1, WT2,WT3 will woke up
        }

    }

}
