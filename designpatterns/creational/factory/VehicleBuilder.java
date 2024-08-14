package designpatterns.creational.factory;

// *Product interface representing a vehicle
abstract class Vehicle {
    public abstract void printVehicle();
}

// *Concrete Products
class TwoWheeler extends Vehicle {

    @Override
    public void printVehicle() {
        System.out.println("I am a two wheeler vehicle");
    }

}

class ThreeWheeler extends Vehicle {

    @Override
    public void printVehicle() {
        System.out.println("I am a three wheeler vehicle");
    }

}

// * Concrete Interface
abstract class VehicleFactory {
    public abstract Vehicle createVehicle();
}

// * Concrete Creators
class TwoWheelerFactory extends VehicleFactory {

    @Override
    public Vehicle createVehicle() {
        return new TwoWheeler();
    }

}

class ThreeWheelerFactory extends VehicleFactory {

    @Override
    public Vehicle createVehicle() {
        return new ThreeWheeler();
    }

}

class ClientHandler {
    private Vehicle vehicle;

    public ClientHandler(VehicleFactory vehicleFactory) {
        vehicle = vehicleFactory.createVehicle();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

public class VehicleBuilder {
    public static void main(String[] args) {
        TwoWheelerFactory twoWheelerFactory = new TwoWheelerFactory();
        ClientHandler twoWheelerClient = new ClientHandler(twoWheelerFactory);
        Vehicle twoWheeler = twoWheelerClient.getVehicle();
        twoWheeler.printVehicle();

        ThreeWheelerFactory threeWheelerFactory = new ThreeWheelerFactory();
        ClientHandler threeWheelerClient = new ClientHandler(threeWheelerFactory);
        Vehicle threeWheeler = threeWheelerClient.getVehicle();
        threeWheeler.printVehicle();
    }
}