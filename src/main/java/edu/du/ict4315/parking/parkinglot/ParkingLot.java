/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.du.ict4315.parking.parkinglot;

/**
 *
 * @author candace.saindon
 */
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.observers.ParkingObserver;
import java.time.Instant;
import java.util.List;

public interface ParkingLot {

    String getLotId();

    Address getAddress();

    int getCapacity();

    Money getBaseRate();

    int getCarsInLot();

    int getEmptySlots();

    ParkingLotType getLotType();

    Money getParkingCharge(ParkingEvent event);

    void registerObserver(ParkingObserver observer);

    void unregisterObserver(ParkingObserver observer);

    void notifyObservers(ParkingEvent event);

    List<ParkingObserver> getObservers();

    void enterLot(Instant timeIn, Permit permit);

    void exitLot(Instant timeIn, Instant timeOut, Permit permit);
    
}

