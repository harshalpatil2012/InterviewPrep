package codeInterView.coreJava;

public class JavaCastingApp {

    public static void main1(String[] args) {
        Parent[] pRef = new Child[1];
        for (int i = 0; i < pRef.length; i++) {

            pRef[i] = new Child();
            System.out.println("new Child..");
            pRef[i].print();
            pRef[i].display();

            pRef[i] = new Parent();
            System.out.println("New Parent..");
            pRef[i].print();
            pRef[i].display();
        }
    }

    public static void main(String[] args) {
        Parent pRef = new Child();
        pRef.show2();
        pRef.print();
        pRef.display();

        Child cRef = (Child) new Parent();
        cRef.show1();
        cRef.print();
        cRef.display();
    }

}

class Parent {
    public int num = 10;

    public void print() {
        System.out.println("I am in Parent print() method");
    }

    public void display() {
        System.out.println("I am in Parent display() method");
    }

    public void show2() {
        System.out.println("I am in Parent show2() method");
    }
}

class Child extends Parent {
    public int num = 20;

    public void print() {
        System.out.println("I am in Child print() method");
    }

    public void show() {
        System.out.println("I am in Child show() method");
    }

    public void show1() {
        System.out.println("I am in Child show1() method");
    }

    public void show2() {
        System.out.println("I am in Child show2() method");
    }

}