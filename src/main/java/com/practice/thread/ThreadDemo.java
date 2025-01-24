package com.practice.thread;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

class A {
    int add(int a, int b) {
        class Inner {

        }
        return 1;
    }
}

class Outer {

}

class AA {
    static int add(int i, int j)// line 3
    {
        return i + j;
    }
}

public class ThreadDemo extends AA {
    public static void main(String argv[]) {
        short s = 9;// line 13
        System.out.println(add(s, 6));// line 14

    }

    public void print(Object object) {
        // TODO Auto-generated method stub

    }

    public void doStuff() {
        // TODO Auto-generated method stub

    }
}

class ThreadDemo1 extends A {
    private String x = "Outer variable";

    void doStuff() {
        String z = "local variable";
        StringBuffer sb = new StringBuffer();
        //
        // x.replace(oldChar, newChar)
        class Inner {
            public void seeOuter() {
                System.out.println("Outer x is " + x);
                System.out.println("Local variable z is " + z);
            }
        }
    }

    public void print(Object i) {
        System.out.println("Inside integer");
    }

    public void print(String i) {
        System.out.println("Inside String");
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo demo = new ThreadDemo();
        demo.doStuff();
        String str1 = "123.12213asas";
        NumberFormat nm = NumberFormat.getInstance();
        nm.setMaximumFractionDigits(2);
        try {
            System.out.println("Formatted str::" + nm.parse(str1));
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        demo.print(null);
        String[] str = { "abc" };
        try {
            process(str);
        } catch (Exception e) {
            System.out.println("Exception");
            ;
            process(str);
        }
        short s = 9;
        // System.out.println("output::"+add(s,9));
        Object ob = new Object();
        synchronized (Thread.currentThread()) {
            ob.wait();
            ob.notify();

        }
        Double d1 = new Double("2.0");
        Double d2 = new Double("2.00");
        System.out.println("  comap" + d1.equals(d2));
        BigDecimal bd1 = new BigDecimal("2.0");
        BigDecimal bd2 = new BigDecimal("2.00");
        System.out.println("  BigDecimal" + bd1.equals(bd2));
        System.out.println("A");
        synchronized (args) {

            System.out.println("B");
            try {
                args.wait();
            } catch (Exception e) {

            }
            System.out.println("C");
        }
        FromThread fromThread = new FromThread();
        fromThread.start();
        fromThread.run();

        Thread fromRunnable = new Thread(new FromRunnable());
        fromRunnable.start();
        fromRunnable.stop(); // this can stop thread but not guaranteed
        fromThread.join();
        fromRunnable.join();
        System.out.println("after stop in main");

    }

    private static int process(String[] str) {
        return Integer.parseInt(str[0]);

    }
}

class FromThread extends Thread {

    public void run() {
        System.out.println("Inside FromThread");
    }

}

class FromRunnable implements Runnable {

    @Override
    public void run() {

        System.out.println("Inside FromRunnable");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        System.out.println("after sleep in FromRunnable");

    }
}
