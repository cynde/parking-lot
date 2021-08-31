package com.btpn.parkinglot;

import java.util.stream.Stream;

public enum ParkingMode implements ParkingStrategy {
    FIRST_AVAILABLE {
        @Override
        public ParkingLot selectParkingLot(Stream<ParkingLot> parkingLotsStream) {
            return parkingLotsStream
                .findFirst()
                .orElse(null);
        }
    }, MOST_CAPACITY {
        @Override
        public ParkingLot selectParkingLot(Stream<ParkingLot> parkingLotsStream) {
            return parkingLotsStream
                .sorted((parkingLot, otherParkingLot) -> parkingLot.compareByCapacityDescending(otherParkingLot))
                .findFirst()
                .orElse(null);
        }
    }, MOST_FREE_SPACE {
        @Override
        public ParkingLot selectParkingLot(Stream<ParkingLot> parkingLotsStream) {
            return parkingLotsStream
                .sorted((parkingLot, otherParkingLot) -> parkingLot.compareByFreeSpaceDescending(otherParkingLot))
                .findFirst()
                .orElse(null);
        }
    };

}
