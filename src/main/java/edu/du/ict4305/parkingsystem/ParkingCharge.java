/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.Instant;
import java.util.Objects;

/**
 * ParkingCharge holds info about a parking charge incurred by a vehicle
 *
 * @author candace.saindon
 */
public class ParkingCharge<T extends Money> {

  private Permit permit;
  private String lotId;
  private Instant incurred;
  private T amount;

  public ParkingCharge(Permit permit, String lotId, Instant incurred, T amount) {
    this.permit = permit;
    this.lotId = lotId;
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
    ParkingCharge<?> that = (ParkingCharge<?>) o;
    return Objects.equals(permit, that.permit)
        && Objects.equals(lotId, that.lotId)
        && Objects.equals(incurred, that.incurred)
        && Objects.equals(amount, that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(permit, lotId, incurred, amount);
  }

  public String toString() {
    return getClass().getName() + "[permit=" + permit + ", lotId=" + lotId
        + ",incurred=" + incurred + ", amount=" + amount + "]";
  }
}
