/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Candace Saindon
 */
/**
 * This is the class that manages all the parking transactions. using the manager design pattern
 */
public class TransactionManager {

  private ArrayList<ParkingCharge> transactions =  new ArrayList<>();
  private HashMap<String, ArrayList<ParkingCharge>> carTransaction = new HashMap<>();
  
  /**
   * This method will create a parking charge and will add it to the transactions list.
   */
  public ParkingCharge park(Instant date, Permit permit, String lotId, Money charge) {
    //todo find out the fee charged
    ParkingCharge transaction = new ParkingCharge(permit, lotId, date, charge);
    transactions.add(transaction);
    return transaction;
  }


  public Money getParkingCharges(Permit permit) {
    Money totalCharges = new Money(0);
    ArrayList<ParkingCharge> permitTransactions = carTransaction.get(permit);
    if (permitTransactions == null) {
      return totalCharges;
    }
    for (ParkingCharge transaction : permitTransactions) {
      Money charge = transaction.getAmount();
    }
    return totalCharges;
  }

  public Money getParkingCharges(Car car) {

    ArrayList<ParkingCharge> carTransactions = carTransaction.get(car);
    if (carTransactions == null) {
      return new Money(0);
    }
    Money totalCharges = new Money(0);
    for (ParkingCharge transaction : carTransactions) {
      Permit permit = transaction.getPermit();
      if (permit.getCar().equals(car)) {
        Money charge = transaction.getAmount();
      }
    }
    return totalCharges;
  }

}
