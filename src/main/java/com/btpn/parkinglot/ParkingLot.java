package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Vehicle> cars = new ArrayList<>();
    private List<Notifiable> notifiables;
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.notifiables = new ArrayList<>();
    }

    public ParkingLot(List<Notifiable> notifiables, int capacity) {
        this.notifiables = notifiables;
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
            for (Notifiable notifiablePerson : this.notifiables) {
                notifiablePerson.notifyIfFull();
            }
        }
    }

    public void unpark(Vehicle car) {
        if (!isParked(car)) {
            throw new VehicleIsNotParkedException();
        }
        boolean ifFull = this.isFull();
        this.cars.remove(car);
        if (ifFull) {
            for (Notifiable notifiablePerson : this.notifiables) {
                notifiablePerson.notifyIfAvailable();
            }
        }
    }
}
