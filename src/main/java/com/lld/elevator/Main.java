package com.lld.elevator;

public class Main {
    public static void main(String[] args) {
        ElevatorSystem system = new ElevatorSystem(4, 20, 800.0);

        Request request1 = new Request(1, 15, 70.0, "CARD1", false);
        Request request2 = new Request(5, 2, 85.0, "CARD2", true);

        system.processRequest(request1);
        system.processRequest(request2);

        try {
            Thread.sleep(5000);
            system.handleEmergency(Emergency.FIRE);
            Thread.sleep(2000);
            system.shutdown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}