package codeInterView.serialize;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Car implements Externalizable {

    String name;

    int year;
    volatile int modelNum = 121;

    /*
     * mandatory public no-arg constructor
     */
    public Car() {

        super();
        System.out.println("In car no arg constructor");
    }

    Car(String n, int y) {
        System.out.println("In car arg constructor");
        name = n;
        year = y;
    }

    /**
     * Mandatory writeExernal method.
     */
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(year);
        out.writeInt(modelNum);
    }

    /**
     * Mandatory readExternal method.
     */
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        year = in.readInt();
        modelNum = in.readInt();
    }

    /**
     * Prints out the fields. used for testing!
     */
    public String toString() {
        return ("Name: " + name + "\n" + "Year: " + year + "modelNum: " + modelNum);
    }
}
