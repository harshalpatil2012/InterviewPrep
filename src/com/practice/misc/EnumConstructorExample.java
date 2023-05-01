package com.practice.misc;

enum TrafficSignal {
    // this will call enum constructor with one String argument
    RED("wait"), GREEN("go"), ORANGE("slow down");

    private String action;

    public String getAction() {
        return this.action;
    }

    // enum constructor - can not be public or protected
    TrafficSignal(String action) {
        this.action = action;
    }

}

class ABC {
    enum TrafficSignal {
        // this will call enum constructor with one String argument
        RED("wait"), GREEN("go"), ORANGE("slow down");

        private String action;

        public String getAction() {
            return this.action;
        }

        // enum constructor - can not be public or protected
        TrafficSignal(String action) {
            this.action = action;
        }

    }
}

/**
 *
 * Java Enum example with constructor. Java Enum can have constructor but can
 * not be public or protected
 *
 * @author http://java67.blogspot.com
 */
public class EnumConstructorExample {

    public static void main(String args[]) {

        // let's print name of each enum and there action - Enum values()
        // examples
        ABC.TrafficSignal sig = ABC.TrafficSignal.GREEN;
        TrafficSignal[] signals = TrafficSignal.values();
        TrafficSignal signal1 = TrafficSignal.RED;
        System.out.println("ENUM default value RED::" + TrafficSignal.RED);
        System.out.println("ENUM value RED toString::" + TrafficSignal.RED.toString());
        System.out.println("ENUM value RED name()::" + TrafficSignal.RED.name());
        System.out.println("ENUM value getAction::" + signal1.getAction());
        for (TrafficSignal signal : signals) {
            // Java name example - Java getter method example
            System.out.println("name : " + signal.name() + " action: " + signal.getAction());
        }

        Shape unknown = null;
        Shape circle = Shape.CIRCLE;
        boolean result = unknown == circle; // return false result =
                                            // unknown.equals(circle); //throws nullPointerException
        result = unknown.equals(circle);
        System.out.println("result:: " + result);
    }
}

enum Shape {
    RECTANGLE, SQUARE, CIRCLE, TRIANGLE;
}

enum Status {
    ON, OFF;
}
