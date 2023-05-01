package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Boxer1 {
    Integer i;
    int x;

    public Boxer1(int y) {
        x = i + y;
        System.out.println(x);
    }
}

public class Test implements DeclareStuff {
    public void doStuff1(int s) {
        s += EASY + ++s;
        System.out.println("doStuff1 s :::" + s);
    }

    static class A {
        void process() throws Exception {
            throw new Exception();
        }
    }

    static class B extends A {
        void process() {
            System.out.println("B ");
        }
    }

    public static Iterator reverse(List list) {
        Collections.reverse(list);
        return list.iterator();
    }

    /*
     * Scanner sc = new Scanner(System.in); int testcase = sc.nextInt(); int
     * arrSize = sc.nextInt(); int[] l = new int[arrSize]; System.out.println(
     * " arrSize==>" + arrSize); for (int j = 0; j < arrSize; j++) { l[j] = j; }
     * System.out.println(" arrSize==>" + l.length);
     * 
     * boolean[] flags = new boolean[l.length]; int count = 0; for (int i = 0; i
     * != l.length;) {
     * 
     * if(count==4) break;
     * 
     * System.out.println("Inside"); ArrayList<Integer> a = new ArrayList<>(), b
     * = new ArrayList<>(); for (int j = 0; j < l.length; j++) if (flags[j])
     * a.add(l[j]); else b.add(l[j]); count++;
     * 
     * }
     * 
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int testcase = sc.nextInt();
            int arrSize = sc.nextInt();
            int[] l = new int[arrSize];
            System.out.println(" arrSize==>" + arrSize);
            for (int j = 0; j < arrSize; j++) {
                l[j] = j;
            }
            System.out.println(" arrSize==>" + l.length);

            boolean[] flags = new boolean[l.length];
            int count = 0;
            for (int i = 0; i != l.length;) {
                ArrayList<Integer> a = new ArrayList<>(), b = new ArrayList<>();
                for (int j = 0; j < l.length; j++)
                    if (flags[j])
                        a.add(l[j]);
                    else
                        b.add(l[j]);
                count++;

            }

            count = count - 2;
            System.out.println("Total goups  count ::" + count);
        }
    }

    public static void main12(String[] args) {

        int x = 5;
        new Test().doStuff(++x);

        int x2 = 6;
        Test p = new Test();
        p.doStuff1(x);
        System.out.println(" main x = " + x);

        int x1 = 5;
        boolean b1 = true;
        boolean b2 = false;

        if ((x == 4) && !b2)
            System.out.print("1 ");
        System.out.print("2 ");
        if ((b2 = true) && b1)
            System.out.print("3 ");

        System.out.print("b2 " + b2);

        Float pi = new Float(3.14f);
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("Cat");
            }
        };
        Thread t = new Thread(r) {
            public void run() {
                System.out.println("Dog");
            }
        };
        // t.start();

        ArrayList<String> strings = new ArrayList<String>();
        /*
         * AAaa AaA aAa aAaA
         */
        strings.add("aAaA");
        strings.add("AaA");
        strings.add("aAa");
        strings.add("AAaa");
        Collections.sort(strings);
        for (String s : strings)

        {
            System.out.println(s + " ");
        }

        String str = "four";
        int check = 4;

        System.out.println(" str index::" + str.charAt(check -= 1));
        try

        {
            int a = 9, b = 0;
            int c = a / b;
        } catch (

        ArithmeticException e)

        {
            System.out.println("Printing ArithmeticException a");
        } finally

        {
            System.out.println("Printing b");
        }
    }

    static int[] a;

    void doStuff(int x) {
        System.out.println(" doStuff x = " + x++);
    }

    public static void go(short n) {
        System.out.println("short");
    }

    public static void go(Short n) {
        System.out.println("SHORT");
    }

    public static void go(Long n) {
        System.out.println(" LONG");
    }

    public static void main2(String[] args) {
        Short y = 6;
        int z = 7;
        go(y);
        // go(z);
    }

    // static { a[0]=2; }
    public static void main22(String[] args) {
        try {
            args = null;
            args[0] = "test";
            System.out.println(args[0]);
            int a = 9, b = 0;
            int c = a / b;
        } catch (Exception ex) {
            System.out.println("Exception");
        }

    }

    public static void main1(String[] args) {
        try {
            int a = 9, b = 0;
            int c = a / b;
        } catch (Exception e) {
            System.out.println("Printing a");
        } finally {
            System.out.println("Printing b");
        }
        ArrayList list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("element::" + list.get(i));
        }
        /*
         * for (Object obj : reverse(list)) System.out.print(obj + ", ");
         */

        new Boxer1(new Integer(4));
        A a2 = new B();
        // a.process();

        String str = "null";
        if (str == null) {
            System.out.println("null");
        } else if (str.length() == 0) {
            System.out.println("zero");
        } else {
            System.out.println("some");
        }

        test(str);
        test(null);
        Float pi = new Float(3.14f);
        if (pi > 3) {

        }
        // System.out.format("Pi is approximately %d", Math.PI);//Exception
        // in
        // thread "main" java.util.IllegalFormatConversionException: d !=
        // java.lang.Double

        System.out.format("Pi is approximately %f", Math.PI);
        String test = "This is a test";
        String[] tokens = test.split("\\s");
        System.out.println(tokens.length);

        int data[] = { 4, 9, 10, 88, 9, 5, 33, 3, 66, 1, 84 };
        int first, second;
        first = second = Integer.MAX_VALUE;

        for (int i = 0; i < data.length; i++) {
            if (data[i] < first) {

                second = first;
                first = data[i];
            } else if (data[i] < second && data[i] != first) {
                second = data[i];
            }
            // EnumSet

        }

        HashMap<String, String> map = new HashMap(1000000000, 0.75f);
        HashMap map3 = new HashMap<String, String>(1000000000, 0.75f);
        HashMap map4d = new HashMap(1000000000, 0.75f);
        HashMap<String, String> map2 = new HashMap<String, String>(1000000000, 0.75f);

        String String = "String";
        System.out.println("First::" + first + "  Second::" + second + " String::" + String);
    }

    public static void test(String str) {
        if (str == null || str.length() == 0) {
            System.out.println("String is empty");
        } else {
            System.out.println("String is not empty");
        }
    }

    public void print1() {
        // TODO Auto-generated method stub

    }

    public void print2() {
        // TODO Auto-generated method stub

    }
}

interface Eatable {
    void eat();
}

interface DeclareStuff {
    public static final int EASY = 3;

    void doStuff1(int t);
}

class TestAnnonymousInner1 {
    public static void main(String args[]) {
        // Eatable b = new Eatable();
        Eatable e = new Eatable() {
            public void eat() {
                System.out.println("nice fruits");
            }
        };
        e.eat();
    }
}

interface int1 extends int2 {
    public void print1();
}

interface int2 {
    public void print1();

    public void print2();
   
}

abstract class Abs {
    private int a;
}