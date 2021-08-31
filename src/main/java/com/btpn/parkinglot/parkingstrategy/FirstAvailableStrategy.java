package com.btpn.parkinglot.parkingstrategy;

import java.util.stream.Stream;

import com.btpn.parkinglot.ParkingLot;
import com.btpn.parkinglot.ParkingStrategy;

public class FirstAvailableStrategy implements ParkingStrategy {
    @Override
    public ParkingLot selectParkingLot(Stream<ParkingLot> parkingLotsStream) {
        return parkingLotsStream
            .findFirst()
            .orElse(null);
    }
}
