package com.practice.serialize;

public class Employee extends Manager implements java.io.Serializable {
    /**
     * 
     */
    public static final long serialVersionUID = 2222;
    public String name;
    public String address;
    public transient int SSN;
    public int number;
    public int n1;
    public static int age;

    public void mailCheck() {
        System.out.println("Mailing a check to " + name + " " + address);
    }
}

class Manager {
    public String des;
    public String dept;

    public void mailCheck() {
        System.out.println("Mailing a check to " + des + " " + dept);
    }
}