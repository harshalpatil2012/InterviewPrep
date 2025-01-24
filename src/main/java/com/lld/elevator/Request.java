package com.lld.elevator;

import lombok.Getter;

@Getter
public class Request {
    private final int sourceFloor;
    private final int destinationFloor;
    private final Direction direction;
    private final double weight;
    private final String cardId;
    private final boolean isPriority;

    // Constructor with all parameters
    public Request(int sourceFloor, int destinationFloor, double weight, String cardId, boolean isPriority) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.direction = sourceFloor < destinationFloor ? Direction.UP : Direction.DOWN;
        this.weight = weight;
        this.cardId = cardId;
        this.isPriority = isPriority;
    }

    // Convenience constructor without priority
    public Request(int sourceFloor, int destinationFloor, double weight, String cardId) {
        this(sourceFloor, destinationFloor, weight, cardId, false);
    }

    // Minimal constructor
    public Request(int sourceFloor, int destinationFloor) {
        this(sourceFloor, destinationFloor, 70.0, "DEFAULT", false);
    }
}