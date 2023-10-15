/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import edu.du.ict4315.parking.charges.factory.ParkingChargeStrategyFactory;
import java.time.Instant;
import java.util.List;

/**
 *
 * @author candace.saindon
 */
public class ParkingLotProxy implements ParkingLot {

    private RealParkingLot realParkingLot;
    private String lotId;
    private Address address;
    private int capacity;
    private ParkingLotType lotType;

    public ParkingLotProxy(String lotId, Address address, int capacity, ParkingLotType lotType) {
        // You can include any validation or processing specific to the proxy here.
        this.lotId = lotId;
        this.address = address;
        this.capacity = capacity;
        this.lotType = lotType;
        this.realParkingLot = new RealParkingLot(lotId, address, capacity, lotType);
    }

    @Override
    public String getLotId() {
        return realParkingLot.getLotId();
    }

    @Override
    public Address getAddress() {
        return realParkingLot.getAddress();
    }

    @Override
    public int getCapacity() {
        return realParkingLot.getCapacity();
    }

    @Override
    public Money getBaseRate() {
        return realParkingLot.getBaseRate();
    }

    @Override
    public int getCarsInLot() {
        return realParkingLot.getCarsInLot();
    }

    @Override
    public int getEmptySlots() {
        return realParkingLot.getEmptySlots();
    }

    @Override
    public ParkingLotType getLotType() {
        return realParkingLot.getLotType();
    }

    @Override
    public ParkingChargeStrategyFactory getParkingChargeStrategyFactory() {
        return realParkingLot.getParkingChargeStrategyFactory();
    }

    @Override
    public void setParkingChargeStrategyFactory(ParkingChargeStrategyFactory factory) {
        realParkingLot.setParkingChargeStrategyFactory(factory);
    }

    @Override
    public Money getParkingCharge(ParkingEvent event) {
        return realParkingLot.getParkingCharge(event);
    }

    @Override
    public void registerObserver(ParkingObserver observer) {
        realParkingLot.registerObserver(observer);
    }

    @Override
    public void unregisterObserver(ParkingObserver observer) {
        realParkingLot.unregisterObserver(observer);
    }

    @Override
    public void notifyObservers(ParkingEvent event) {
        realParkingLot.notifyObservers(event);
    }

    @Override
    public List<ParkingObserver> getObservers() {
        return realParkingLot.getObservers();
    }

    @Override
    public void enterLot(Instant timeIn, Permit permit) {
        //We will use the protection proxy to make sure that the parking lot is not full before a car enters
        int capacity = realParkingLot.getCapacity();
        int carsInLot = realParkingLot.getCarsInLot();

        int emptySlots = capacity - carsInLot;

        if (emptySlots <= 0) {
            throw new IllegalStateException("Parking lot is full");
        } else {
            realParkingLot.enterLot(timeIn, permit);
        }

    }

    @Override
    public void exitLot(Instant timeIn, Instant timeOut, Permit permit) {
        //We will use the protection proxy to make sure that the parking lot is not empty before a car leaves 
        int carsInLot = realParkingLot.getCarsInLot();

        if (carsInLot <= 0) {
            throw new IllegalStateException("Parking lot is already empty");
        } else {
            realParkingLot.exitLot(timeIn, timeOut, permit);
        }

    }

}
