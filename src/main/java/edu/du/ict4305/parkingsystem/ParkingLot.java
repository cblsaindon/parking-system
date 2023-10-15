/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

/**
 *
 * @author candace.saindon
 */
import edu.du.ict4315.parking.charges.factory.ParkingChargeStrategyFactory;
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

    ParkingChargeStrategyFactory getParkingChargeStrategyFactory();

    void setParkingChargeStrategyFactory(ParkingChargeStrategyFactory factory);

    Money getParkingCharge(ParkingEvent event);

    void registerObserver(ParkingObserver observer);

    void unregisterObserver(ParkingObserver observer);

    void notifyObservers(ParkingEvent event);

    List<ParkingObserver> getObservers();

    void enterLot(Instant timeIn, Permit permit);

    void exitLot(Instant timeIn, Instant timeOut, Permit permit);
    
}

