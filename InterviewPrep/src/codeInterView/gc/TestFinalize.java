package codeInterView.gc;

import java.util.ArrayList;

public class TestFinalize {

    static TestFinalize t;

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ArrayList<String> list = new ArrayList<>(10);
        list.add(0, "4");
        list.add(1, "2");
        list.add(2, "2");
        list.add(3, "2");
        list.add(4, "2");
        list.remove(3);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("list::" + list.get(i));
        }

        TestFinalize t1 = new TestFinalize();

        // making t1 eligible for garbage collection
        t1 = null; // line 12

        System.out.println("gc call 1  ");
        // calling garbage collector
        System.gc(); // line 15

        // waiting for gc to complete
        Thread.sleep(1000);

        // making t eligible for garbage collection,
        t = null; // line 21

        System.out.println("gc call 2  ");
        // calling garbage collector
        System.gc(); // line 24

        // waiting for gc to complete
        Thread.sleep(100);

        System.out.println("finalize method called " + count + " times");

    }

    @Override
    protected void finalize() {
        System.out.println("inside finalize method ");
        count++;

        t = this; // line 38

    }

    public static void main1(String[] args) throws InterruptedException {
        String str = new String("GeeksForGeeks");

        TestFinalize t = new TestFinalize();

        // making t eligible for garbage collection
        t = null;
        // making str eligible for gc
        str = null;

        // calling garbage collector
        System.gc();

        // waiting for gc to complete
        Thread.sleep(1000);

        System.out.println("end of main");
    }

    protected void finalize1() {
        System.out.println("finalize method called");
    }
}
