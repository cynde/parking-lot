package com.btpn.parkinglot;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ParkingLotTest {
    private int capacity = 1;
    private Notifiable owner = Mockito.mock(Notifiable.class);
    private Notifiable trafficCop = Mockito.mock(Notifiable.class);

    @Test
    void park_shouldNotThrowException_whenSuccessfullyParkACar() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(capacity);

        Assertions.assertDoesNotThrow(() -> parkingLot.park(car));
    }

    @Test
    void park_shouldThrowVehicleAlreadyParkedException_whenCarIsAlreadyParked() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(car);

        Assertions.assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(car));
    }

    @Test
    void unpark_shouldNotThrowException_whenSuccessfullyUnparkACar() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(capacity);
        parkingLot.park(car);

        Assertions.assertDoesNotThrow(() -> parkingLot.unpark(car));
    }

    @Test
    void unpark_shouldThrowVehicleIsNotParkedException_whenCarIsNotParked() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(capacity);

        Assertions.assertThrows(VehicleIsNotParkedException.class, () -> parkingLot.unpark(car));
    }

    @Test
    void park_shouldCallNotifyIfFullFromOwner_whenParkingLotIsFull() {
        Vehicle car = new Vehicle() {};
        List<Notifiable> notifiables = new ArrayList<>();
        notifiables.add(owner);
        ParkingLot parkingLot = new ParkingLot(notifiables, capacity);

        parkingLot.park(car);

        Mockito.verify(owner, Mockito.times(1)).notifyIfFull();
    }
    
    @Test
    void park_shouldThrowParkingLotIsFullException_whenParkToAFullParkingLot() {
        Vehicle car = new Vehicle() {};
        Vehicle truck = new Vehicle() {};
        List<Notifiable> notifiables = new ArrayList<>();
        notifiables.add(owner);
        ParkingLot parkingLot = new ParkingLot(notifiables, capacity);
        parkingLot.park(car);

        Assertions.assertThrows(ParkingLotIsFullException.class, () -> parkingLot.park(truck));
    }

    @Test
    void unpark_shouldCallNotifyIfAvailableFromOwner_whenParkingLotBecomeAvailable() {
        Vehicle car = new Vehicle() {};
        List<Notifiable> notifiables = new ArrayList<>();
        notifiables.add(owner);
        ParkingLot parkingLot = new ParkingLot(notifiables, capacity);
        parkingLot.park(car);

        parkingLot.unpark(car);

        Mockito.verify(owner, Mockito.times(1)).notifyIfAvailable();
    }

    @Test
    void park_shouldCallNotifyIfFullFromTrafficCop_whenParkingLotIsFull() {
        Vehicle car = new Vehicle() {};
        List<Notifiable> notifiables = new ArrayList<>();
        notifiables.add(owner);
        notifiables.add(trafficCop);
        ParkingLot parkingLot = new ParkingLot(notifiables, capacity);

        parkingLot.park(car);

        Mockito.verify(trafficCop, Mockito.times(1)).notifyIfFull();
    }

    @Test
    void unpark_shouldCallNotifyIfAvailableFromTrafficCop_whenParkingLotBecomeAvailable() {
        Vehicle car = new Vehicle() {};
        List<Notifiable> notifiables = new ArrayList<>();
        notifiables.add(owner);
        notifiables.add(trafficCop);
        ParkingLot parkingLot = new ParkingLot(notifiables, capacity);
        parkingLot.park(car);

        parkingLot.unpark(car);

        Mockito.verify(owner, Mockito.times(1)).notifyIfAvailable();
    }

    @Test
    void addSubscriber_shouldNotThrowAlreadySubscribedException_whenSuccessfullyAddANotifiableAsASubscriber() {
        ParkingLot parkingLot = new ParkingLot(capacity);

        Assertions.assertDoesNotThrow(() -> parkingLot.addSubscriber(owner));
    }

    @Test
    void addSubscriber_shouldThrowAlreadySubscribedException_whenSubscribeWithTheSameSubscriberTwice() {
        ParkingLot parkingLot = new ParkingLot(capacity);
        parkingLot.addSubscriber(owner);

        Assertions.assertThrows(AlreadySubscribedException.class, () -> parkingLot.addSubscriber(owner));
    }

    @Test
    void isParked_shouldReturnTrue_whenCarIsParkedInTheParkingLot() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(capacity);
        parkingLot.park(car);

        Assertions.assertTrue(parkingLot.isParked(car));
    }

    @Test
    void isParked_shouldReturnFalse_whenCarIsNotParkedInTheParkingLot() {
        Vehicle car = new Vehicle() {};
        ParkingLot parkingLot = new ParkingLot(capacity);

        Assertions.assertFalse(parkingLot.isParked(car));
    }
}
