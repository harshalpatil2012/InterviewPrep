package com.practice.coreJava;

public class InterfaceInheritance {
    public static final int i = 33;

    public static void main(String[] args) {
        NewClass ncls = new NewDerivedCls();
        ncls.method1(1);
    }
}

interface NewInterface {
    public static final int i = 3;

    void method1(int a);

    void method2();
}

interface NewInterface2 {

    void method1(int a);

    void method2();
}

class NewClass implements NewInterface, NewInterface2 {

    @Override
    public void method1(int a) {
        System.out.println("Inside NewClass method1:: " + i);

    }

    @Override
    public void method2() {
        System.out.println("Inside NewClass method2");

    }
}

class NewDerivedCls extends NewClass {
    public void method1() {
        System.out.println("Inside NewDerivedCls method1");

    }

}
