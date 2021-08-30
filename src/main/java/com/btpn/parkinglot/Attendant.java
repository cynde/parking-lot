package com.btpn.parkinglot;

public class Attendant {
    private ParkingLot parkingLot;

    public Attendant(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void park(Vehicle car) {
        this.parkingLot.park(car);
    }

    public void unpark(Vehicle car) {
        this.parkingLot.unpark(car);
    }
}
