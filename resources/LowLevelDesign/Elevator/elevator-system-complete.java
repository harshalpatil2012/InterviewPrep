package core;

public enum Direction { UP, DOWN, IDLE }

public enum ElevatorState { MOVING, STOPPED, MAINTENANCE, EMERGENCY }

public enum Emergency { NONE, FIRE, POWER_OUT, EARTHQUAKE }

@Getter
@Builder
public class Request {
    private final Integer sourceFloor;
    private final Integer destinationFloor;
    private final Direction direction;
    private final Double weight;
    private final String cardId;
    private final Boolean isPriority;
    
    public Request(Integer sourceFloor, Integer destinationFloor, Double weight, String cardId, Boolean isPriority) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.direction = sourceFloor < destinationFloor ? Direction.UP : Direction.DOWN;
        this.weight = weight;
        this.cardId = cardId;
        this.isPriority = isPriority;
    }
}

@Getter
public class Floor {
    private final int floorNumber;
    private final Set<String> authorizedCards;
    private final boolean isRestricted;
    
    public Floor(int floorNumber, boolean isRestricted) {
        this.floorNumber = floorNumber;
        this.isRestricted = isRestricted;
        this.authorizedCards = new HashSet<>();
    }
    
    public boolean canAccess(String cardId) {
        return !isRestricted || authorizedCards.contains(cardId);
    }
    
    public void addAuthorizedCard(String cardId) {
        authorizedCards.add(cardId);
    }
}

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
