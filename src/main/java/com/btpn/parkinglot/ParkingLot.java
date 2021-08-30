package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Vehicle> cars = new ArrayList<>();
    private Owner owner;
    private int capacity;

    public ParkingLot(Owner owner, int capacity) {
        this.owner = owner;
        this.capacity = capacity;
    }

    private boolean isParked(Vehicle car) {
        return this.cars.contains(car);
    }

    private boolean isFull() {
        return this.cars.size() == this.capacity;
    }

    public void park(Vehicle car) {
        if (isParked(car)) {
            throw new VehicleAlreadyParkedException();
        }
        if (isFull()) {
            throw new ParkingLotIsFullException();
        }
        this.cars.add(car);
        if (isFull()) {
            this.owner.notifyIfFull();
        }
    }

    public void unpark(Vehicle car) {
        if (!isParked(car)) {
            throw new VehicleIsNotParkedException();
        }
        boolean ifFull = this.isFull();
        this.cars.remove(car);
        if (ifFull) {
            this.owner.notifyIfAvailable();
        }
    }
}
