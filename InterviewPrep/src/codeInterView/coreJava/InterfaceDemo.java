package codeInterView.coreJava;

public class InterfaceDemo implements LotsOfColors {
    static int i = 10;

    public void print1() {

    }

    public static void main(String[] args) throws InterruptedException {

        InterfaceDemo interfaceDemo = new InterfaceDemo();
        System.out.println(" classsloader::" + String.class.getClassLoader());
        interfaceDemo.display();
        interfaceDemo.print();
    }

    static {
        System.out.println("In STATIC block()");
        System.out.println(" i::" + i);
    }

    static void display() throws InterruptedException {
        System.out.println("In STATIC display()");
        Thread.currentThread()
            .sleep(100);
    }

    void print() throws InterruptedException {
        display();
        i = 20;
        System.out.println("IN print i::" + i);

    }

    @Override
    public void print1(String a) {
        // TODO Auto-generated method stub

    }

    @Override
    public void print1(Object b) {
        // TODO Auto-generated method stub

    }
}

interface BaseColors {
    int RED = 1, GREEN = 2, BLUE = 4;
}

interface RainbowColors extends BaseColors {
    int YELLOW = 3, ORANGE = 5, INDIGO = 6, VIOLET = 7;

    void print1(String a);
}

interface PrintColors extends BaseColors {
    int YELLOW = 8, CYAN = 16, MAGENTA = 32;

    void print1(Object b);
}

interface LotsOfColors extends RainbowColors, PrintColors {
    int FUCHSIA = 17, VERMILION = 43, CHARTREUSE = RED + 90;
}

class Concreate {

    public String display(Object obj) {
        // TODO Auto-generated method stub
        return null;
    }

    protected int display(Integer integer) {
        // TODO Auto-generated method stub
        return 1;
    }

    private String display(String string) {
        // TODO Auto-generated method stub
        return null;
    }

    private String display(Long string) {
        // TODO Auto-generated method stub
        return null;
    }

    private static String display(int i) {
        return null;

    }

    private static String display(double i) {
        return null;

    }

}

interface P1 {
    String display(String string);
}

interface P2 {
    String display(Integer integer);
}

interface C1 extends P1, P2 {
    String display();
}