/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * Money, parking charge, and parking office classes come later
 */
package edu.du.ict4305.parkingsystem;

/**
 * Class for ParkingLot that allows a car to enter or exit and calculates the associated charges.
 *
 * @author candace.saindon
 */
import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategy;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public class ParkingLot {

  private String lotId;
  private Address address;
  private int capacity;
  private int carsInLot;
  private ParkingLotType lotType;
  private Money baseRate = new Money(5.00);
  private ParkingChargeStrategy parkingChargeStrategy;

  public ParkingLot(String lotId, Address address, int capacity, ParkingLotType lotType) {
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
  
  public ParkingChargeStrategy getParkingChargeStrategy() {
      return parkingChargeStrategy;
  }

  public void setParkingChargeStrategy(ParkingChargeStrategy parkingChargeStrategy) {
      this.parkingChargeStrategy = parkingChargeStrategy;
  }
  
  public Money getParkingCharge(Instant date, Permit permit) {
        Money charge = parkingChargeStrategy.calculateParkingCharge(date, permit, baseRate);    
        return charge;
  }

  
  // A method to allow a car to enter a parking lot.
  public void entry(Car car) {

    try {

      if (car == null) {
        throw new IllegalArgumentException("Car must not be null");
      }

      if (car.getLotEnter() != null) {
        throw new IllegalStateException("Car is already in a parking lot");
      }

      int emptySlots = capacity - carsInLot;

      if (emptySlots <= 0) {
        throw new IllegalStateException("Parking lot is full");
      }

      carsInLot++;

      Instant lotEnterTime = Instant.now();
      car.setLotEnter(lotEnterTime);

    } catch (IllegalArgumentException | IllegalStateException e) {
      System.out.println("Error: " + e.getMessage());
      throw e;
    }

  }

  // A method to allow a car to exit a parking lot.
  public void exit(Car car) {

    if (car == null) {
      throw new IllegalArgumentException("Car must not be null");
    }

    if (carsInLot <= 0) {
      throw new IllegalStateException("Parking lot is already empty");
    }

    car.setLotEnter(null);
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
    ParkingLot that = (ParkingLot) o;
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
