package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AttendantTest {
    private Vehicle car = new Vehicle() {};
    private Vehicle otherCar = new Vehicle() {};
    private ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
    private ParkingLot otherParkingLot = Mockito.mock(ParkingLot.class);

    @Test
    void park_shouldCallParkFromParkingLot_whenInvoked() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        Attendant attendant = new Attendant(parkingLots);

        attendant.park(car);

        Mockito.verify(parkingLot, Mockito.times(1)).park(car);
    }

    @Test
    void unpark_shouldCallUnparkFromParkingLot_whenInvoked() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        Attendant attendant = new Attendant(parkingLots);

        attendant.unpark(car);

        Mockito.verify(parkingLot, Mockito.times(1)).unpark(car);
    }

    @Test
    void park_shouldParkInAnotherParkingLot_whenFirstParkingLotIsFull() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(otherParkingLot);
        Attendant attendant = new Attendant(parkingLots);
        Mockito.doThrow(ParkingLotIsFullException.class).when(parkingLot).park(otherCar);
        attendant.park(car);
        
        attendant.park(otherCar);

        Mockito.verify(otherParkingLot, Mockito.times(1)).park(otherCar);
    }

    @Test
    void park_shouldThrowNoAvailableParkingLot_whenAllParkingLotIsFull() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        Attendant attendant = new Attendant(parkingLots);
        Mockito.doThrow(ParkingLotIsFullException.class).when(parkingLot).park(otherCar);
        attendant.park(car);
        
        Assertions.assertThrows(NoAvailableParkingLotException.class, () -> attendant.park(otherCar));
    }
}
