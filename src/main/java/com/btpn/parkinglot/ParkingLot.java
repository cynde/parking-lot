package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Vehicle> cars = new ArrayList<>();

    // public ParkingLot() {
    //     cars = new ArrayList<>();
    // }

    private boolean isParked(Vehicle car) {
        return this.cars.contains(car);
    }

    public void park(Vehicle car) {
        if (isParked(car)) {
            throw new VehicleAlreadyParkedException();
        }
        this.cars.add(car);
    }

    public void unpark(Vehicle car) {
        if (!isParked(car)) {
            throw new VehicleIsNotParkedException();
        }
        this.cars.remove(car);
    }
}
