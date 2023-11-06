/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * Money, parking charge, and parking office classes come later
 */
package edu.du.ict4315.parking.parkinglot;

/**
 * Class for ParkingLot that allows a car to enter or exit and calculates the
 * associated charges.
 *
 * @author candace.saindon
 */
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.observers.ParkingObserver;
import edu.du.ict4315.parking.decorator.ParkingChargeCalculator;
import edu.du.ict4315.parking.decorator.ParkingChargeCalculatorFactory;
import edu.du.ict4315.parking.strategy.ParkingChargeStrategyFactory;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;

public class RealParkingLot implements Serializable {

    private String lotId;
    private Address address;
    private int capacity;
    private int carsInLot;
    private ParkingLotType lotType;
    private Money baseRate = Money.of(5.00);
    private String strategy;
    private ParkingChargeStrategyFactory strategyFactory;
    private ParkingChargeCalculatorFactory calculatorFactory;
    private List<ParkingObserver> observers = new ArrayList<>();

    public RealParkingLot(String lotId, Address address, int capacity, ParkingLotType lotType) {
        if (lotId == null || lotId.isEmpty()) {
            throw new IllegalArgumentException("The lot id must not be null or empty");
        }

        this.lotId = lotId;
        this.address = address;
        this.capacity = capacity;
        this.lotType = lotType;
        this.carsInLot = 0;
    }

    public String getLotId() {
        return lotId;
    }

    public Address getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public Money getBaseRate() {
        return baseRate;
    }

    public int getCarsInLot() {
        return carsInLot;
    }

    public int getEmptySlots() {
        int emptySlots = capacity - carsInLot;
        return emptySlots;
    }

    public ParkingLotType getLotType() {
        return lotType;
    }

    public ParkingChargeCalculatorFactory getParkingChargeCalculatorFactory() {
        return calculatorFactory;
    }

    public void setParkingChargeCalculatorFactory(ParkingChargeCalculatorFactory factory) {
        this.calculatorFactory = factory;
    }

    public ParkingChargeStrategyFactory getParkingChargeStrategyFactory() {
        return strategyFactory;
    }

    public void setParkingChargeStrategyFactory(ParkingChargeStrategyFactory factory) {
        this.strategyFactory = factory;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Money getParkingCharge(ParkingEvent event) {

        //ParkingChargeStrategy strategy = strategyFactory.makeStrategy();
        //Money charge = strategy.calculateParkingCharge(event, baseRate);
        ParkingChargeCalculator calc = calculatorFactory.getParkingChargeCalculator(this.strategy);
        Money charge = calc.getParkingCharge(event);
        return charge;
    }

    public void registerObserver(ParkingObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(ParkingObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ParkingEvent event) {
        for (ParkingObserver observer : observers) {
            observer.update(event);
        }
    }

    public List<ParkingObserver> getObservers() {
        return observers;
    }

    // Method for permit-required-on-enter lot
    public void enterLot(Instant timeIn, Permit permit) {
        ParkingEvent event = new ParkingEvent(this, permit, timeIn);
        notifyObservers(event);
        carsInLot++;
    }

    // Method for permit-required-on-exit lot
    public void exitLot(Instant timeIn, Instant timeOut, Permit permit) {
        ParkingEvent event = new ParkingEvent(this, permit, timeIn, timeOut);
        notifyObservers(event);
        carsInLot--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RealParkingLot that = (RealParkingLot) o;
        return capacity == that.capacity
                && carsInLot == that.carsInLot
                && Objects.equals(lotId, that.lotId)
                && Objects.equals(address, that.address)
                && lotType == that.lotType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotId, address, capacity, carsInLot, lotType);
    }

    public String toString() {
        return getClass().getName() + "[lotId=" + lotId + ", address=" + address
                + ",capacity=" + capacity + ", carsInLot=" + carsInLot + "]";
    }

}
