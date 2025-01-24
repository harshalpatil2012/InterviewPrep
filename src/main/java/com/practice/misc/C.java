package com.practice.misc;

public class C {
    static void m(Object o) {
        System.out.println("m(o)");
    }

    static void m(String s) {
        System.out.println("m(s)");
    }

    static void m(Integer i) {
        System.out.println("m(i)");
    }

    static <T> void f(T t) {
        m(t);
    }

    public static void main(String[] args) {
        C c = null;
        if (c instanceof C) {
            System.out.println("c instanceof C");
        } else {
            System.out.println("c not instanceof C");
        }

        C c1 = new C();
        if (c1 instanceof Object) {
            System.out.println("c1 instanceof C");
        } else {
            System.out.println("c1 not instanceof C");
        }

        C c11 = new C();
        if (c11 instanceof Object) {
            System.out.println("c11 instanceof C");
        } else {
            System.out.println("c11 not instanceof C");
        }
        // f("abc");
    }
}