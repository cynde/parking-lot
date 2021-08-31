package com.btpn.parkinglot.parkingstrategy;

import java.util.stream.Stream;

import com.btpn.parkinglot.ParkingLot;
import com.btpn.parkinglot.ParkingStrategy;

public class MostFreeSpaceStrategy implements ParkingStrategy {
    @Override
    public ParkingLot selectParkingLot(Stream<ParkingLot> parkingLotsStream) {
        return parkingLotsStream
            .sorted((parkingLot, otherParkingLot) -> parkingLot.compareByFreeSpaceDescending(otherParkingLot))
            .findFirst()
            .orElse(null);
    }
}
