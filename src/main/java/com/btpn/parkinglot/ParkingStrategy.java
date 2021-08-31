package com.btpn.parkinglot;

import java.util.stream.Stream;

public interface ParkingStrategy {
    ParkingLot selectParkingLot(Stream<ParkingLot> parkingLotsStream);
}
