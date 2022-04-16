package codeInterView.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExternExample {

    public static void main(String args[]) {

        // create a Car object
        Car car = new Car("Mitsubishi", 2009);
        Car newCar = null;

        // serialize the car
        try {
            FileOutputStream fo = new FileOutputStream("tmp");
            ObjectOutputStream so = new ObjectOutputStream(fo);
            so.writeObject(car);
            so.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            System.exit(1);
        }

        // de-serialize the Car
        try {
            FileInputStream fi = new FileInputStream("tmp");
            ObjectInputStream si = new ObjectInputStream(fi);
            newCar = (Car) si.readObject();

            System.out.println("Deserialized newCar...");
            System.out.println("Name: " + newCar.name);
            System.out.println("year: " + newCar.year);
            System.out.println("modelNum: " + newCar.modelNum + "\n ");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            System.exit(1);
        }

        /*
         * Print out the original and new car information
         */
        System.out.println("The original car is ");
        System.out.println(car);
        System.out.println("The new car is ");
        System.out.println(newCar);
    }
}
