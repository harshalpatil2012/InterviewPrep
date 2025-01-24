package com.lld.elevator;

import java.util.concurrent.locks.ReentrantLock;

public class DoorController {
    private final ReentrantLock doorLock = new ReentrantLock();
    private boolean isDoorOpen = false;
    
    public void openDoor() {
        doorLock.lock();
        try {
            if (!isDoorOpen) {
                Thread.sleep(1000);
                isDoorOpen = true;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            doorLock.unlock();
        }
    }
    
    public void closeDoor() {
        doorLock.lock();
        try {
            if (isDoorOpen) {
                Thread.sleep(1000);
                isDoorOpen = false;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            doorLock.unlock();
        }
    }
}