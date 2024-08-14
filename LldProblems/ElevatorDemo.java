package LldProblems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorDemo {
    public static void main(String[] args) {
        ElevatorSystem elevatorSystem = new ElevatorSystem(10, 10);
        Request request1 = new Request(3, 7);
        Request request2 = new Request(1, 5);
        Request request3 = new Request(8, 2);

        elevatorSystem.requestElevator(request1);
        elevatorSystem.requestElevator(request2);
        elevatorSystem.requestElevator(request3);
    }

}

/*
 * Actors:
 * ElevatorSystem(SingleTon, List<Elevator>, int totalFloor, find)
 * Elevator(Capacity, currentFloor, List<Request> direct, reverse, Direction)
 * Direction(UP, DOWN, STILL)
 * Request(sourceFloor, DestinationFloor)
 */

class ElevatorSystem {
    int totalFloor;
    List<Elevator> elevators;


    public ElevatorSystem(int maxFloor, int numberOfElevators) {
        this.totalFloor = maxFloor;
        elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            Elevator elevator = new Elevator(i + 1, 10);
            elevators.add(elevator);
            new Thread(elevator::run).start();
        }
    }

    public void requestElevator(Request request) {
        Elevator optimElevator = findOptimalElevator(request.source);
        optimElevator.addRequest(request);
    }

    public Elevator findOptimalElevator(int source) {
        int minDistance = Integer.MAX_VALUE;
        Elevator res = null;
        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.currentFloor - source);
            distance += elevator.requests.size();
            if (distance < minDistance) {
                minDistance = distance;
                res = elevator;
            }
        }
        return res;
    }

}

class Elevator {
    int elevatorNo;
    int maxCapacity;
    int currentFloor;
    Direction currDirection;
    List<Request> requests;

    public Elevator(int elevatorNo, int maxCapacity) {
        this.elevatorNo = elevatorNo;
        this.maxCapacity = maxCapacity;
        currentFloor = 1;
        currDirection = Direction.UP;
        requests = new ArrayList<>();
    }

    public synchronized void addRequest(Request request) {
        requests.add(request);
        notifyAll();
    }

    public synchronized Request getNextRequest() {
        while (requests.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return requests.remove(0);
    }

    public synchronized void processRequests() {
        while (true) {
            while (!requests.isEmpty()) {
                Request request = getNextRequest();
                processRequest(request, true);
                processRequest(request, false);
            }
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void processRequest(Request request, boolean process) {
        int start = currentFloor;
        int end = process ? request.source : request.destination;
        if (start < end) {
            currDirection = Direction.UP;

            for (int i = start; i <= end; i++) {
                if(i != start)
                System.out.println("Elevatior: " + elevatorNo + " moved to floor no: " + i);
                currentFloor = i;
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (start > end) {
            currDirection = Direction.DOWN;
            for (int i = start; i >= end; i--) {
                if(i != start)
                System.out.println("Elevatior: " + elevatorNo + " moved to floor no: " + i);
                currentFloor = i;
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {
        processRequests();
    }
}

enum Direction {
    UP, DOWN
}

class Request {
    int source;
    int destination;

    public Request(int source, int destination) {
        this.source = source;
        this.destination = destination;
    }
}