package LldProblems;

import java.util.*;

/*
 * Actors:
 * ParkingLot(Singelton Instance) => Mutliple Floors, Location
 * ParkingTicket => ParkingSpot, Floor, EntryTime, ExitTime, Fare
 * Floor => Floor No, Multiple Parking Spot
 * ParkingSpot => Spot no, VehicleType, Vehicle
 * Vehicle => VechileType, Licence No
 * VehicleType(CAR, BIKE, Truck)
 * Car
 * Bike
 * Truck
 */

public class PakingLotDemo {
  public static void main(String[] args) {
    ParkingLot parkingLot = ParkingLot.getInstance();
    parkingLot.addFloor(new Floor(1, 100));
    parkingLot.addFloor(new Floor(1, 50));

    Vehcile car1 = new Car("XL1");
    System.out.println("Spot available: " + parkingLot.availableSpot());
    ParkingTicket ticket = null;
    if (parkingLot.availableSpot()) {
      ticket = parkingLot.getParkingTicket(car1);
      parkingLot.parkVehcile(car1, ticket);
      System.out.println("Floor no: " + ticket.floorNo + " Spot No: " + ticket.parkingSpotNo);
    }

    Vehcile car2 = new Car("XL2");
    System.out.println("Spot available: " + parkingLot.availableSpot());
    ParkingTicket ticket2 = null;
    if (parkingLot.availableSpot()) {
      ticket2 = parkingLot.getParkingTicket(car2);
      parkingLot.parkVehcile(car2, ticket2);
      System.out.println("Floor no: " + ticket2.floorNo + " Spot No: " + ticket2.parkingSpotNo);
    }

    if (ticket != null && parkingLot.unParkVechile(car1, ticket)) {
      System.out.println("Current Status: " + ticket.parkingStatus);
    }


    if (ticket2 != null && parkingLot.unParkVechile(car2, ticket2)) {
      System.out.println("Current Status: " + ticket2.parkingStatus);
    }

  }

}

class ParkingLot {
  private List<Floor> floors;
  private static ParkingLot parkingLot;
  private List<ParkingTicket> parkingTickets;

  private ParkingLot() {
    floors = new ArrayList<>();
    parkingTickets = new ArrayList<>();
  }

  public static ParkingLot getInstance() {
    if (parkingLot == null) {
      parkingLot = new ParkingLot();
    }
    return parkingLot;
  }

  public void addFloor(Floor floor) {
    floors.add(floor);
  }

  public boolean availableSpot() {
    for (Floor floor : floors) {
      if (floor.availableSpot())
        return true;
    }
    return false;
  }

  public ParkingTicket getParkingTicket(Vehcile vehcile) {
    ParkingTicket parkingTicket = new ParkingTicket(vehcile);
    parkingTickets.add(parkingTicket);
    return parkingTicket;
  }

  public boolean parkVehcile(Vehcile vehcile, ParkingTicket parkingTicket) {
    for (Floor floor : floors) {
      if (floor.parkVehcile(vehcile, parkingTicket)) {
        parkingTicket.floorNo = floor.floorNo;
        return true;
      }
    }
    return false;
  }

  public boolean unParkVechile(Vehcile vehcile, ParkingTicket parkingTicket) {
    for (Floor floor : floors) {
      if (floor.unParkVechile(vehcile, parkingTicket))
        return true;
    }
    return false;
  }

}

class ParkingTicket {
  int parkingSpotNo;
  int floorNo;
  Date entryTime;
  Date exitTime;
  double fare;
  Vehcile vehcile;
  ParkingStatus parkingStatus;

  public ParkingTicket(Vehcile vehcile) {
    parkingStatus = ParkingStatus.NOT_PARKED;
    this.vehcile = vehcile;
  }
}

enum ParkingStatus {
  NOT_PARKED, PARKED, EXITED
}

class Floor {
  int floorNo;
  List<ParkingSpot> parkingSpots;

  public Floor(int floorNo, int totalSpots) {
    this.floorNo = floorNo;
    parkingSpots = new ArrayList<>();
    for (int i = 0; i < totalSpots; i++) {
      parkingSpots.add(new ParkingSpot(i + 1, VehcileType.CAR)); // default(we can add custom rules here like even odd,
                                                                 // like above 50 Bikes)
    }
  }

  protected boolean availableSpot() {
    for (ParkingSpot parkingSpot : parkingSpots) {
      if (parkingSpot.parkedVehcile == null)
        return true;
    }
    return false;
  }

  protected boolean parkVehcile(Vehcile vehcile, ParkingTicket parkingTicket) {
    for (ParkingSpot parkingSpot : parkingSpots) {
      if (parkingSpot.parkVehcile(vehcile)) {
        parkingTicket.parkingSpotNo = parkingSpot.spotNo;
        parkingTicket.entryTime = new Date();
        parkingTicket.parkingStatus = ParkingStatus.PARKED;
        return true;
      }

    }
    return false;
  }

  protected boolean unParkVechile(Vehcile vehcile, ParkingTicket parkingTicket) {
    for (ParkingSpot parkingSpot : parkingSpots) {
      if (parkingSpot.unParkVechile(vehcile)) {
        parkingTicket.exitTime = new Date();
        parkingTicket.parkingStatus = ParkingStatus.EXITED;
        return true;
      }
    }
    return false;
  }

}

class ParkingSpot {
  int spotNo;
  Vehcile parkedVehcile;
  VehcileType vehcileType;

  protected ParkingSpot(int spotNo, VehcileType vehcileType) {
    this.spotNo = spotNo;
    this.vehcileType = vehcileType;
  }

  protected boolean parkVehcile(Vehcile vehcile) {
    if (parkedVehcile == null && vehcile.vehcileType == vehcileType) {
      parkedVehcile = vehcile;
      return true;
    }
    return false;
  }

  protected boolean unParkVechile(Vehcile vehcile) {
    if (parkedVehcile != null && parkedVehcile.licenceNo.equals(vehcile.licenceNo)) {
      parkedVehcile = null;
      return true;
    }
    return false;
  }
}

abstract class Vehcile {
  String licenceNo;
  VehcileType vehcileType;

  protected Vehcile(String liceneNo, VehcileType vehcileType) {
    this.licenceNo = liceneNo;
    this.vehcileType = vehcileType;
  }
}

class Bike extends Vehcile {

  protected Bike(String liceneNo) {
    super(liceneNo, VehcileType.BIKE);
  }

}

class Car extends Vehcile {

  protected Car(String liceneNo) {
    super(liceneNo, VehcileType.CAR);
  }

}

class Truck extends Vehcile {

  protected Truck(String liceneNo) {
    super(liceneNo, VehcileType.TRUCK);
  }

}

enum VehcileType {
  CAR, BIKE, TRUCK
}