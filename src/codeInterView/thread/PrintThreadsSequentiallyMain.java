package codeInterView.thread;

public class PrintThreadsSequentiallyMain {

    public static void main(String[] args) {

        /*  PrintSequenceRunnable runnable1 = new PrintSequenceRunnable(1);
        PrintSequenceRunnable runnable2 = new PrintSequenceRunnable(2);
        PrintSequenceRunnable runnable3 = new PrintSequenceRunnable(0);
        
        Thread t1 = new Thread(runnable1, "T1");
        Thread t2 = new Thread(runnable2, "T2");
        Thread t3 = new Thread(runnable3, "T3");
        
        t1.start();
        t2.start();
        t3.start();*/
        int num = 3;
        for (int i = 0; i < num; i++) {
            PrintSequenceRunnable runnable = new PrintSequenceRunnable(i);
            Thread t = new Thread(runnable, "Thread_" + i);
            t.start();
        }
    }
}

class PrintSequenceRunnable implements Runnable {

    public int PRINT_NUMBERS_UPTO = 10;// 3;
    static int number = 1;
    int remainder;
    static Object lock = new Object();

    PrintSequenceRunnable(int remainder) {
        this.remainder = remainder;
    }

    @Override
    public void run() {
        while (number < PRINT_NUMBERS_UPTO - 1) {
            synchronized (lock) {
                while (number % 3 != remainder) { // wait for numbers other than remainder
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread()
                    .getName() + " " + number);
                number++;
                lock.notifyAll();
            }
        }
    }
}
