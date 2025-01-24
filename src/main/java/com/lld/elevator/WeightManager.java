package com.lld.elevator;

import java.util.concurrent.atomic.AtomicReference;

public class WeightManager {
    private final double maxWeight;
    private final AtomicReference<Double> currentWeight;
    
    public WeightManager(double maxWeight) {
        this.maxWeight = maxWeight;
        this.currentWeight = new AtomicReference<>(0.0);
    }
    
    public synchronized boolean canAddWeight(double weight) {
        double newWeight = currentWeight.get() + weight;
        if (newWeight <= maxWeight) {
            currentWeight.set(newWeight);
            return true;
        }
        return false;
    }
    
    public synchronized void removeWeight(double weight) {
        currentWeight.updateAndGet(current -> Math.max(0, current - weight));
    }
}