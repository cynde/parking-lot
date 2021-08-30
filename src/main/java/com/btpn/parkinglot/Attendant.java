package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Attendant {
    private List<ParkingLot> parkingLots;

    public Attendant(List<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
    }

    public void park(Vehicle car) {
        this.parkingLots.get(0).park(car);
    }

    public void unpark(Vehicle car) {
        this.parkingLots.get(0).unpark(car);
    }
}
