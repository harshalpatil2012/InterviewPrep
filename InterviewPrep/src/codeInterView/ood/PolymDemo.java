package codeInterView.ood;

public class PolymDemo {

    public static void main(String[] args) {
        int num = 9999;
        PolymDemo poly = new PolymDemo();
        Parent p = new Child();
        ((Parent) p).print();
        ((Child) p).display();
        System.out.println("Parent Value of a::" + p.a);
        p.a = 300;
        Child c1 = new Child();
        c1.a = 400;
        System.out.println("Parent Value of a::" + p.a);
        /* Child c = (Child) new Parent(); */
        /* System.out.println("Child Value of a::"+c.a); */
        System.out.println("Before Child Value of C1 a::" + c1.a + " NUM value::" + num);
        poly.display(c1, num);
        System.out.println("After Child Value of C1 a::" + c1.a + " NUM value::" + num);
        Parent p1 = new Child();
        System.out.println("Child Value of p1::" + p1.a);
        display("NO");

    }

    public final void display(Child c, int num) {
        num = 888;
        c.a = 900;
    }

    public static String display(String str1) {

        switch (str1) {
        case "no":
            System.out.println("Inside no case");
            break;
        case "No":
            System.out.println("Inside No case");
            break;
        case "NO":
            System.out.println("Inside NO case");
            break;

        }
        return str1;
    }
}

class Parent {

    int a = 100;

    public void print() {
        System.out.println("Inside Parent a:: " + a);
    }
}

class Child extends Parent {

    int a = 200;

    public void print() {
        System.out.println("Inside Child a:: " + a);
    }

    public void display() {
        System.out.println("Inside Child display a:: " + a);
    }
}
