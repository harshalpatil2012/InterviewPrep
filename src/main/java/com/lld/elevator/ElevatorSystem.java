package com.lld.elevator;

import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorSystem {
    private final CopyOnWriteArrayList<Elevator> elevators;
    private final ExecutorService executorService;
    private final int maxFloor;
    private volatile Emergency systemEmergency = Emergency.NONE;

    public ElevatorSystem(int numElevators, int maxFloor, double maxWeight) {
        this.elevators = new CopyOnWriteArrayList<>();
        this.executorService = Executors.newFixedThreadPool(numElevators + 1);
        this.maxFloor = maxFloor;
        initializeElevators(numElevators, maxWeight);
        startElevatorMonitoring();
    }

    private void initializeElevators(int numElevators, double maxWeight) {
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator(i + 1, maxFloor, maxWeight));
        }
    }

    public void processRequest(Request request) {
        if (systemEmergency != Emergency.NONE) return;
        executorService.submit(() -> {
            Elevator selectedElevator = findBestElevator(request);
            if (selectedElevator != null) {
                selectedElevator.addRequest(request);
            }
        });
    }

    private Elevator findBestElevator(Request request) {
        return elevators.stream()
                .filter(e -> e.getState() != ElevatorState.MAINTENANCE && e.getState() != ElevatorState.EMERGENCY)
                .min(Comparator.comparingInt(e -> calculateCost(e, request)))
                .orElse(null);
    }

    private int calculateCost(Elevator elevator, Request request) {
        int distance = Math.abs(elevator.getCurrentFloor().get() - request.getSourceFloor());
        if (elevator.getCurrentDirection() == request.getDirection()) {
            return distance;
        }
        return distance + maxFloor;
    }

    private void startElevatorMonitoring() {
        executorService.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                elevators.forEach(Elevator::move);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public void handleEmergency(Emergency emergency) {
        this.systemEmergency = emergency;
        elevators.forEach(e -> e.handleEmergency(emergency));
    }

    public void shutdown() {
        executorService.shutdown();
    }

    @Override
    protected void finalize() {
        shutdown();
    }
}