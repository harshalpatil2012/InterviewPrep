package com.practice.serialize;

import java.io.*;

public class TestSerialization {
    public static void main(String args[]) {
        // Object serialization
        try {
            MyClass object1 = new MyClass("Hello");
            System.out.println("object1: " + object1);
            FileOutputStream fos = new FileOutputStream("serial");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object1);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Exception during serialization: " + e);
            System.exit(0);
        }
        // Object deserialization
        try {
            MyClass object2;
            FileInputStream fis = new FileInputStream("serial");
            ObjectInputStream ois = new ObjectInputStream(fis);
            object2 = (MyClass) ois.readObject();
            ois.close();
            System.out.println("object2: " + object2);
        } catch (Exception e) {
            System.out.println("Exception during deserialization: " + e);
            System.exit(0);
        }
    }
}

class FailSerilization {
    String y = "fail serilization";
}

class MySuperClass {
    String x = "not super";
    FailSerilization fs;

    // MySuperClass(FailSerilization fs){
    // this.fs=fs;
    // }
    MySuperClass() {
        fs = new FailSerilization();
    }

}

class MyClass extends MySuperClass implements Serializable {
    String s;

    public MyClass(String s) {
        // super(new FailSerilization());
        this.s = s;

    }

    public String toString() {
        return "s=" + s + ";x= " + x + "; fs= " + fs.y;
    }
}
