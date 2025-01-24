package com.practice.thread.deadlock.livelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockSample {

    private static final Lock lock1 = new ReentrantLock(true);
    private static final Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        LivelockSample sample = new LivelockSample();
        Thread threadA = new Thread(LivelockSample::doA, "Thread A");
        Thread threadB = new Thread(LivelockSample::doB, "Thread B");
        threadA.start();
        threadB.start();
    }

    public static void doA() {
        try {
            while (!lock1.tryLock()) {
                System.out.println(Thread.currentThread()
                    .getName() + " : doA waits for lock 1");
                Thread.sleep(100);
            }
            System.out.println(Thread.currentThread()
                .getName() + " : doA holds lock 1");
            // System.exit(0);
            try {
                while (!lock2.tryLock()) {
                    System.out.println(Thread.currentThread()
                        .getName() + " : doA waits for lock 2");
                    Thread.sleep(100);
                }
                System.out.println(Thread.currentThread()
                    .getName() + " : doA holds lock 2");

                try {
                    System.out.println(Thread.currentThread()
                        .getName() + " : doA critical section of doA()");
                } finally {
                    lock2.unlock();
                    System.out.println(Thread.currentThread()
                        .getName() + " : doB does not hold lock 2 any longer");
                }
            } finally {
                lock1.unlock();
                System.out.println(Thread.currentThread()
                    .getName() + " : doA does not hold lock 1 any longer");
            }
        } catch (InterruptedException e) {
            // can be ignored here for this sample
        }
    }

    public static void doB() {
        try {
            while (!lock2.tryLock()) {
                System.out.println(Thread.currentThread()
                    .getName() + " : doB waits for lock 2");
                Thread.sleep(100);
            }
            System.out.println(Thread.currentThread()
                .getName() + " : doB holds lock 2");
            // System.exit(0);
            try {
                while (!lock1.tryLock()) {
                    System.out.println(Thread.currentThread()
                        .getName() + " : doB waits for lock 1");
                    Thread.sleep(100);
                }
                System.out.println(Thread.currentThread()
                    .getName() + " : doB holds lock 1");

                try {
                    System.out.println(Thread.currentThread()
                        .getName() + " : doB critical section of doB()");
                } finally {
                    lock1.unlock();
                    System.out.println(Thread.currentThread()
                        .getName() + " : doB does not hold lock 1 any longer");
                }
            } finally {
                lock2.unlock();
                System.out.println(Thread.currentThread()
                    .getName() + " : doB does not hold lock 2 any longer");
            }
        } catch (InterruptedException e) {
            // can be ignored here for this sample
        }
    }
}
