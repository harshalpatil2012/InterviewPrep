package com.practice.thread;

public class ThreadEvenOddNumberExample {

    public static void main(String[] args) {
        int maxNumber = 10;

        NumberPrinter printer = new NumberPrinter();
        new Thread(new EvenNumGenerator(printer, maxNumber)).start();
        new Thread(new OddNumGenerator(printer, maxNumber)).start();
    }
}

class NumberPrinter {

    // To check if even number is printed or not.
    private boolean isEvenNumPrinted = true;

    public void printOdd(int number) throws InterruptedException {
        // Get a lock on NumberPrinter
        synchronized (this) {

            // Wait until even is not printed.
            if (!isEvenNumPrinted)
                wait();

            System.out.println(number);

            isEvenNumPrinted = false;

            // Notify the other waiting thread which is waiting on
            // NumberPrinter
            // Other thread will get out of waiting state
            this.notify();
        }
    }

    public void printEven(int number) throws InterruptedException {
        synchronized (this) {
            if (isEvenNumPrinted)
                wait();

            System.out.println(number);
            isEvenNumPrinted = true;
            this.notify();
        }
    }
}

class OddNumGenerator implements Runnable {
    private NumberPrinter q;
    private int max;

    public OddNumGenerator(NumberPrinter q, int max) {
        this.q = q;
        this.max = max;
    }

    @Override
    public void run() {
        for (int i = 1; i < max; i = i + 2) {
            try {
                q.printOdd(i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class EvenNumGenerator implements Runnable {
    private NumberPrinter printer;
    private int max;

    public EvenNumGenerator(NumberPrinter printer, int max) {
        this.printer = printer;
        this.max = max;
    }

    @Override
    public void run() {
        for (int i = 2; i <= max; i = i + 2) {
            try {
                printer.printEven(i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
