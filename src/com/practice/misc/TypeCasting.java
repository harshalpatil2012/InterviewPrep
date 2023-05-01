package com.practice.misc;
public class TypeCasting {
    public static void main(String[] args) {
        ((TestA) new TestB()).start();
        TestA a = new TestB();
        ((TestA) a).start();
        System.out.println(" Value of a at 1 ::" + ((TestA) a).a);
        ((TestB) a).start();
        System.out.println(" Value of a at 2 ::" + ((TestB) a).a);
        ((TestA) a).start1();
        ((TestB) a).start1();

        ((TestA) a).start2();
        ((TestB) a).start2();

        Cup cup = new PoisonCup();
        takeCup(cup);
        TestB b = (TestB) new TestA();

        TestA A = new TestB();
        TestB B = (TestB) new TestA();
        B.display1();
        B.display2(1);
        A.display2(1);

    }

    public static void takeCup(Cup c) {
        if (c instanceof PoisonCup) {
            System.out.println("Inconceivable!");
        } else if (c instanceof Cup) {
            System.out.println("Dizzying intellect!");
        } else {
            System.exit(0);
        }
    }
}

class TestA {
    int a = 10;

    public void start() {
        System.out.println("Inside TestA start");
    }

    public void display1() {
        System.out.println("Inside TestB start");
    }

    public void display2(int a) {
        System.out.println("Inside TestB start");
    }

    public static void start1() {
        System.out.println("Inside  TestA static start1");
    }

    public static void start2() {
        System.out.println("Inside  TestA static start2");
    }
}

class TestB extends TestA {
    int a = 20;

    public void start() {
        System.out.println("Inside TestB start");
    }

    public void display2() {
        System.out.println("Inside TestB start");
    }

    public static void start1() {
        System.out.println("Inside TestB static start1");
    }

    public static void start3() {
        System.out.println("Inside TestB static start3");
    }

    public static void main1(String[] args) {

    }
}

class Cup {
    int cup = 1;
}

class PoisonCup extends Cup {
    int PoisonCup = 1;
}

class Base {

    // Static method in base class which will be hidden in subclass
    protected static void display() {
        System.out.println("Static or class method from Base");
    }

    // Non-static method which will be overridden in derived class
    public void print() {
        System.out.println("Non-static or Instance method from Base");
    }
}

// Subclass
class Derived extends Base {

    // Static is removed here (Causes Compiler Error)
    public void display1() {
        System.out.println("Non-static method from Derived");
    }

    // Static is added here (Causes Compiler Error)
    public static void print2() {
        System.out.println("Static method from Derived");
    }
}