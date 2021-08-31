package com.btpn.parkinglot;

public interface Notifiable {
    public void notifyIfFull(ParkingLot parkingLot);
    public void notifyIfAvailable(ParkingLot parkingLot);
}
