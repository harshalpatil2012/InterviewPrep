package com.practice.ood;

/**
 *
 * Java program which demonstrate that we can not override static method in
 * Java. Had Static method can be overridden, with Super class type and sub
 * class object static method from sub class would be called in our example,
 * which is not the case.
 * 
 * @author
 */
public class CanWeOverrideStaticMethod {

    public static void main(String args[]) {

        Screen scrn = new ColorScreen();
        // if we can override static , this should call method from Child class
        scrn.show(); // IDE will show warning, static method should be called
                     // from classname
        ColorScreen colscrn = new ColorScreen();
        colscrn.show();
        Screen s = new ColorScreen();
        s.show();
        System.out.println("Foo value::" + colscrn.FOO);
        System.out.println("Foo value::" + ((Screen) colscrn).FOO);
        System.out.println("Foo value::" + (((Screen) colscrn)).FOO);
        System.out.println("Foo value::" + ((ColorScreen) ((Screen) colscrn)).FOO);
    }

}

class Screen {
    public static final String FOO = "Screen";

    /*
     * public static method which can not be overridden in Java
     */
    public static void show() {
        System.out.println("Static method from parent class Screen-show");
    }

    final void print() {
        System.out.println("print");
    }
}

class ColorScreen extends Screen {
    public static final String FOO = "ColorScreen";

    public void print1() {
        System.out.println("print");
    }

    /*
     * static method of same name and method signature as existed in super
     * class, this is not method overriding instead this is called method hiding
     * in Java
     */
    public static void show() {
        System.out.println("Overridden static method in Child Class in Java ColorScreen-show");
    }
}
