package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Attendant {
    private List<ParkingLot> parkingLots;

    public Attendant(List<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
    }

    public void park(Vehicle car) {
        for(ParkingLot parkingLot : parkingLots) {
            try {
                parkingLot.park(car);
                return;
            } catch (ParkingLotIsFullException exception) {}
        }
    }

    public void unpark(Vehicle car) {
        this.parkingLots.get(0).unpark(car);
    }
}
