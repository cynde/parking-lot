package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Attendant implements Notifiable {
    private List<ParkingLot> parkingLots;
    private List<ParkingLot> availableParkingLots;
    private ParkingMode parkingMode;

    public Attendant(List<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
        this.availableParkingLots = new ArrayList<>(parkingLots);
        this.parkingMode = ParkingMode.FIRST_AVAILABLE;
        subscribeLots();
    }

    public Attendant(List<ParkingLot> parkingLots, ParkingMode parkingMode) {
        this(parkingLots);
        this.parkingMode = parkingMode;
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
        if (this.parkingMode == ParkingMode.FIRST_AVAILABLE) {
            this.availableParkingLots.get(0).park(car);
        }
        if (this.parkingMode == ParkingMode.MOST_CAPACITY) {
            ParkingLot selectedParkingLot = this.availableParkingLots.stream()
                .sorted((parkingLot, otherParkingLot) -> parkingLot.compareByCapacityDescending(otherParkingLot))
                .findFirst()
                .orElse(null);
            if (selectedParkingLot != null) {
                selectedParkingLot.park(car);
            }
        }
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
