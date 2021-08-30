package com.btpn.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ParkingLotTest {
    private int capacity = 1;
    private Owner owner = Mockito.mock(Owner.class);
    private TrafficCop trafficCop = Mockito.mock(TrafficCop.class);

    @Test
    void park_shouldNotThrowException_whenSuccessfullyParkACar() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(owner, capacity);

        Assertions.assertDoesNotThrow(() -> parkingLot.park(car));
    }

    @Test
    void park_shouldThrowVehicleAlreadyParkedException_whenCarIsAlreadyParked() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(owner, 2);
        parkingLot.park(car);

        Assertions.assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(car));
    }

    @Test
    void unpark_shouldNotThrowException_whenSuccessfullyUnparkACar() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(owner, capacity);
        parkingLot.park(car);

        Assertions.assertDoesNotThrow(() -> parkingLot.unpark(car));
    }

    @Test
    void unpark_shouldThrowVehicleIsNotParkedException_whenCarIsNotParked() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(owner, capacity);

        Assertions.assertThrows(VehicleIsNotParkedException.class, () -> parkingLot.unpark(car));
    }

    @Test
    void park_shouldCallNotifyIfFullFromOwner_whenParkingLotIsFull() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(owner, capacity);

        parkingLot.park(car);

        Mockito.verify(owner, Mockito.times(1)).notifyIfFull();
    }
    
    @Test
    void park_shouldThrowParkingLotIsFullException_whenParkToAFullParkingLot() {
        Vehicle car = new Vehicle() {};
        Vehicle truck = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(owner, capacity);
        parkingLot.park(car);

        Assertions.assertThrows(ParkingLotIsFullException.class, () -> parkingLot.park(truck));
    }

    @Test
    void unpark_shouldCallNotifyIfAvailableFromOwner_whenParkingLotBecomeAvailable() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(owner, capacity);
        parkingLot.park(car);

        parkingLot.unpark(car);

        Mockito.verify(owner, Mockito.times(1)).notifyIfAvailable();
    }

    @Test
    void park_shouldCallNotifyIfFullFromTrafficCop_whenParkingLotIsFull() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(owner, trafficCop, capacity);

        parkingLot.park(car);

        Mockito.verify(trafficCop, Mockito.times(1)).notifyIfFull();
    }
}
