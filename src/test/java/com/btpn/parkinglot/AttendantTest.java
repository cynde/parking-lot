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
        attendant.park(car);
        Mockito.doReturn(true).when(parkingLot).isParked(car);

        attendant.unpark(car);

        Mockito.verify(parkingLot, Mockito.times(1)).unpark(car);
    }

    @Test
    void park_shouldParkInAnotherParkingLot_whenFirstParkingLotIsFull() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(new ArrayList<>(), 1);
        ParkingLot otherParkingLot = new ParkingLot(new ArrayList<>(), 1);
        parkingLots.add(parkingLot);
        parkingLots.add(otherParkingLot);
        Attendant attendant = new Attendant(parkingLots);
        attendant.park(car);

        Assertions.assertDoesNotThrow(() -> otherParkingLot.park(otherCar));
    }

    @Test
    void park_shouldThrowNoAvailableParkingLot_whenAllParkingLotIsFull() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(new ArrayList<>(), 1);
        parkingLots.add(parkingLot);
        Attendant attendant = new Attendant(parkingLots);
        attendant.park(car);
        
        Assertions.assertThrows(NoAvailableParkingLotException.class, () -> attendant.park(otherCar));
    }

    @Test
    void unpark_shouldCallUnparkFromOtherParkingLot_whenCarIsNotParkedInTheFirstParkingLot() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(otherParkingLot);
        Attendant attendant = new Attendant(parkingLots);
        attendant.park(otherCar);
        attendant.park(car);
        Mockito.doReturn(true).when(otherParkingLot).isParked(car);
        Mockito.doThrow(VehicleIsNotParkedException.class).when(parkingLot).unpark(car);

        attendant.unpark(car);

        Mockito.verify(otherParkingLot, Mockito.times(1)).unpark(car);
    }

    @Test
    void park_shouldParkToOtherParkingLot_whenOtherParkingLotHasMostCapacityAndParkingModeIsMostCapacity() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(new ArrayList<>(), 2);
        ParkingLot otherParkingLot = new ParkingLot(new ArrayList<>(), 3);
        parkingLots.add(parkingLot);
        parkingLots.add(otherParkingLot);
        Attendant attendant = new Attendant(parkingLots, ParkingMode.MOST_CAPACITY);
        
        attendant.park(car);

        Assertions.assertTrue(otherParkingLot.isParked(car));
    }
}
