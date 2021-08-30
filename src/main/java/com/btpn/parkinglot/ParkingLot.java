package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Vehicle> cars = new ArrayList<>();
    private Notifiable owner;
    private Notifiable trafficCop;
    private int capacity;

    public ParkingLot(Notifiable owner, int capacity) {
        this.owner = owner;
        this.capacity = capacity;
        this.trafficCop = new Notifiable(){

            @Override
            public void notifyIfFull() {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void notifyIfAvailable() {
                // TODO Auto-generated method stub
                
            }
            
        };
    }

    public ParkingLot(Notifiable owner, Notifiable trafficCop, int capacity) {
        this(owner, capacity);
        this.trafficCop = trafficCop;
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
            this.trafficCop.notifyIfFull();
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
