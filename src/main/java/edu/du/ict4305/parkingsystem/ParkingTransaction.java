/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.Instant;
import java.util.Objects;

/**
 * ParkingTransaction holds info about a parking charge incurred by a vehicle
 *
 * @author candace.saindon
 */
public class ParkingTransaction<T extends Money> {

  private Permit permit;
  private ParkingLot parkingLot;
  private Instant incurred;
  private T amount;

  public ParkingTransaction(Instant incurred, Permit permit, ParkingLot parkingLot, T amount) {
    this.permit = permit;
    this.parkingLot = parkingLot;
    this.incurred = incurred;
    this.amount = amount;
  }

  public T getAmount() {
    return amount;
  }
  
  public Permit getPermit() {
    return permit;
  }
  

  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParkingTransaction<?> that = (ParkingTransaction<?>) o;
    return Objects.equals(permit, that.permit)
        && Objects.equals(parkingLot, that.parkingLot)
        && Objects.equals(incurred, that.incurred)
        && Objects.equals(amount, that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(permit, parkingLot, incurred, amount);
  }

  public String toString() {
    return getClass().getName() + "[permit=" + permit + ", parkinglot=" + parkingLot
        + ",incurred=" + incurred + ", amount=" + amount + "]";
  }
}
