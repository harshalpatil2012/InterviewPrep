package com.lld.elevator;

import lombok.Getter;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Elevator {
    private final int id;
    private final AtomicInteger currentFloor;
    private volatile Direction currentDirection;
    private volatile ElevatorState state;
    private final WeightManager weightManager;
    private final DoorController doorController;
    private final Map<Integer, Floor> floors;
    private final PriorityQueue<Request> upRequests;
    private final PriorityQueue<Request> downRequests;
    private final int maxFloor;
    private Emergency emergencyStatus;

    public Elevator(int id, int maxFloor, double maxWeight) {
        this.id = id;
        this.currentFloor = new AtomicInteger(1);
        this.currentDirection = Direction.IDLE;
        this.state = ElevatorState.STOPPED;
        this.weightManager = new WeightManager(maxWeight);
        this.doorController = new DoorController();
        this.floors = new ConcurrentHashMap<>();
        this.upRequests = new PriorityQueue<>(Comparator.comparing(Request::getDestinationFloor));
        this.downRequests = new PriorityQueue<>(Comparator.comparing(Request::getDestinationFloor).reversed());
        this.maxFloor = maxFloor;
        this.emergencyStatus = Emergency.NONE;
        initializeFloors();
    }

    private void initializeFloors() {
        for (int i = 1; i <= maxFloor; i++) {
            floors.put(i, new Floor(i, false));
        }
    }

    public boolean addRequest(Request request) {
        if (!isValidRequest(request)) return false;
        if (!weightManager.canAddWeight(request.getWeight())) return false;

        if (request.getDirection() == Direction.UP) {
            upRequests.offer(request);
        } else {
            downRequests.offer(request);
        }
        updateDirection();
        return true;
    }

    private boolean isValidRequest(Request request) {
        Floor destinationFloor = floors.get(request.getDestinationFloor());
        return destinationFloor != null && destinationFloor.canAccess(request.getCardId());
    }

    private void updateDirection() {
        if (currentDirection == Direction.IDLE) {
            if (!upRequests.isEmpty()) {
                currentDirection = Direction.UP;
            } else if (!downRequests.isEmpty()) {
                currentDirection = Direction.DOWN;
            }
        }
    }

    public void move() {
        if (state != ElevatorState.MOVING || emergencyStatus != Emergency.NONE) return;

        PriorityQueue<Request> currentQueue = currentDirection == Direction.UP ? upRequests : downRequests;
        if (!currentQueue.isEmpty()) {
            Request nextRequest = currentQueue.peek();
            int nextFloor = nextRequest.getDestinationFloor();

            if (currentFloor.get() == nextFloor) {
                stopAtFloor();
            } else {
                moveToNextFloor();
            }
        } else {
            switchDirection();
        }
    }

    private void stopAtFloor() {
        state = ElevatorState.STOPPED;
        doorController.openDoor();
        PriorityQueue<Request> currentQueue = currentDirection == Direction.UP ? upRequests : downRequests;
        Request completed = currentQueue.poll();
        weightManager.removeWeight(completed.getWeight());
        doorController.closeDoor();
    }

    private void moveToNextFloor() {
        if (currentDirection == Direction.UP && currentFloor.get() < maxFloor) {
            currentFloor.incrementAndGet();
        } else if (currentDirection == Direction.DOWN && currentFloor.get() > 1) {
            currentFloor.decrementAndGet();
        }
    }

    private void switchDirection() {
        if (currentDirection == Direction.UP && !downRequests.isEmpty()) {
            currentDirection = Direction.DOWN;
        } else if (currentDirection == Direction.DOWN && !upRequests.isEmpty()) {
            currentDirection = Direction.UP;
        } else {
            currentDirection = Direction.IDLE;
            state = ElevatorState.STOPPED;
        }
    }

    public void handleEmergency(Emergency emergency) {
        this.emergencyStatus = emergency;
        state = ElevatorState.EMERGENCY;
        upRequests.clear();
        downRequests.clear();
    }
}