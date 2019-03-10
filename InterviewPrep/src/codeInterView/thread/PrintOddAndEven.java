package codeInterView.thread;

import java.util.concurrent.Semaphore;

public class PrintOddAndEven {

    private static class OddThread extends Thread {
        private Semaphore semaphore;
        private Semaphore otherSemaphore;
        private int value = 1;

        public OddThread(Semaphore semaphore, Semaphore otherSemaphore) {
            this.semaphore = semaphore;
            this.otherSemaphore = otherSemaphore;
        }

        public void run() {
            while (value <= 100) {
                try {
                    // Acquire odd semaphore
                    semaphore.acquire();
                    System.out.println(" Odd Thread " + value + " " + Thread.currentThread()
                        .getName());

                } catch (InterruptedException excetion) {
                    excetion.printStackTrace();
                }
                value = value + 2;
                // Release odd semaphore
                otherSemaphore.release();
            }
        }
    }

    private static class EvenThread extends Thread {
        private Semaphore semaphore;
        private Semaphore otherSemaphore;

        private int value = 2;

        public EvenThread(Semaphore semaphore, Semaphore otherSemaphore) {
            this.semaphore = semaphore;
            this.otherSemaphore = otherSemaphore;
        }

        public void run() {
            while (value <= 10) {
                try {
                    // Acquire even semaphore
                    semaphore.acquire();
                    System.out.println(" Even Thread " + value + " " + Thread.currentThread()
                        .getName());

                } catch (InterruptedException excetion) {
                    excetion.printStackTrace();
                }
                value = value + 2;
                // Release odd semaphore
                otherSemaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        // Initialize oddSemaphore with permit 1
        Semaphore oddSemaphore = new Semaphore(1);
        // Initialize evenSempahore with permit 0
        Semaphore evenSempahore = new Semaphore(0);
        OddThread oddThread = new OddThread(oddSemaphore, evenSempahore);
        EvenThread evenThread = new EvenThread(evenSempahore, oddSemaphore);
        oddThread.start();
        evenThread.start();
    }
}
