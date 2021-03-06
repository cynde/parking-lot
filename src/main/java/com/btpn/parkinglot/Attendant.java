package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;

import com.btpn.parkinglot.parkingstrategy.FirstAvailableStrategy;

public class Attendant implements Notifiable {
    private List<ParkingLot> parkingLots;
    private List<ParkingLot> availableParkingLots;
    private ParkingStrategy parkingStrategy;

    public Attendant(List<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
        this.availableParkingLots = new ArrayList<>(); // new arraylist aja
        this.parkingStrategy = new FirstAvailableStrategy();
        subscribeLots();
    }

    public Attendant(List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        this(parkingLots);
        this.parkingStrategy = parkingStrategy;
    }

    private void subscribeLots() {
        for (ParkingLot parkingLot : this.parkingLots) {
            parkingLot.addSubscriber(this);
        }
    }

    public void park(Vehicle car) {
        if (availableParkingLots.isEmpty()) {
            throw new NoAvailableParkingLotException();
        }
        ParkingLot selectedParkingLot = this.parkingStrategy.selectParkingLot(this.availableParkingLots.stream());
        selectedParkingLot.park(car);
    }

    public void unpark(Vehicle car) {
        ParkingLot parkingLot = this.parkingLots.stream().filter(lot -> lot.isParked(car)).findAny().orElse(null);
        if (parkingLot == null) {
            throw new VehicleIsNotParkedException();
        }
        parkingLot.unpark(car);
    }

    @Override
    public void notifyIfFull(ParkingLot parkingLot) {
        this.availableParkingLots.remove(parkingLot);
        
    }

    @Override
    public void notifyIfAvailable(ParkingLot parkingLot) {
        this.availableParkingLots.add(parkingLot);
    }
}
