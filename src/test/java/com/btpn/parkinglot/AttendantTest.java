package com.btpn.parkinglot;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AttendantTest {
    private Vehicle car = new Vehicle() {};

    @Test
    void park_shouldCallParkFromParkingLot_whenInvoked() {
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        Attendant attendant = new Attendant(parkingLot);

        attendant.park(car);

        Mockito.verify(parkingLot, Mockito.times(1)).park(car);
    }

    @Test
    void unpark_shouldCallUnparkFromParkingLot_whenInvoked() {
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        Attendant attendant = new Attendant(parkingLot);

        attendant.unpark(car);

        Mockito.verify(parkingLot, Mockito.times(1)).unpark(car);
    }
}
