package codeInterView.thread.deadlock.deadlock._synchronized;

class A implements Runnable {
    public void run() {

        synchronized (String.class) {

            /*
             * Adding this optional delay so that Thread-2 could enough time to lock Object
             * class and form deadlock. If you remove this sleep, because of threads
             * unpredictable behavior it might that Thread-1 gets completed even before
             * Thread-2 is started and we will never form deadLock.
             */
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread()
                .getName() + "has acquired lock " + "on String class and waiting to acquire lock on Object class...");
            synchronized (Object.class) {
                System.out.println(Thread.currentThread()
                    .getName() + " has acquired lock on Object class");
            }
        }

        System.out.println(Thread.currentThread()
            .getName() + " has ENDED");
    }
}

class B extends Thread {
    public void run() {

        synchronized (Object.class) {
            System.out.println(Thread.currentThread()
                .getName() + " has acquired " + "lock on Object class and waiting to acquire lock on String class...");

            /*
             * Adding this optional delay so that Thread-1 could enough time to lock String
             * class and form deadlock. If you remove this sleep, because of threads
             * unpredictable behavior it might that Thread-2 gets completed even before
             * Thread-1 is started and we will never form deadLock.
             */
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (String.class) {
                System.out.println(Thread.currentThread()
                    .getName() + " has acquired lock on String class");
            }
        }

        System.out.println(Thread.currentThread()
            .getName() + " has ENDED");
    }
}

public class DeadlockCreation {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new A(), "Thread-1");
        Thread thread2 = new Thread(new B(), "Thread-2");
        thread1.start();
        thread2.start();
    }

}