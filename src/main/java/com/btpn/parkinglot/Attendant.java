package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Attendant {
    private List<ParkingLot> parkingLots;
    private Stream<ParkingLot> map;

    public Attendant(List<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
    }

    public void park(Vehicle car) {
        for (ParkingLot parkingLot : this.parkingLots) {
            try {
                parkingLot.park(car);
                return;
            } catch (ParkingLotIsFullException exception) {}
        }
        throw new NoAvailableParkingLotException();
    }

    public void unpark(Vehicle car) {
        ParkingLot parkingLot = this.parkingLots.stream().filter(lot -> lot.isParked(car)).findAny().orElse(null);
        if (parkingLot == null) {
            throw new VehicleIsNotParkedException();
        }
        parkingLot.unpark(car);
    }
}
