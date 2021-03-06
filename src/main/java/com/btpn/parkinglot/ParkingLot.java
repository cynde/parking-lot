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

    public void addSubscriber(Notifiable notifiable) {
        if (this.notifiables.contains(notifiable)) {
            throw new AlreadySubscribedException();
        }
        this.notifiables.add(notifiable);

        if (isFull()) {
            notifiable.notifyIfFull(this);
            return;
        }
        notifiable.notifyIfAvailable(this);
    }

    public boolean isParked(Vehicle car) {
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
                notifiablePerson.notifyIfFull(this);
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
                notifiablePerson.notifyIfAvailable(this);
            }
        }
    }

    public int compareByCapacityDescending(ParkingLot otherParkingLot) {
        return otherParkingLot.capacity - this.capacity;
    }

    public int compareByFreeSpaceDescending(ParkingLot otherParkingLot) {
        return (otherParkingLot.capacity - otherParkingLot.cars.size()) - (this.capacity - this.cars.size()); // use integer by compare
    }
}
