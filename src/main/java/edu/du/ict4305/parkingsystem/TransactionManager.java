/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import edu.du.ict4315.parking.charges.factory.ParkingChargeStrategyFactory;
import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategy;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Candace Saindon
 */
/**
 * This is the class that manages all the parking transactions. using the
 * manager design pattern
 */
public class TransactionManager {

    private ArrayList<ParkingTransaction> transactions = new ArrayList<>();
    private HashMap<String, ArrayList<ParkingTransaction>> carTransaction = new HashMap<>();
    private HashMap<String, ArrayList<ParkingTransaction>> permitTransaction = new HashMap<>();
    private HashMap<String, ArrayList<ParkingTransaction>> customerTransaction = new HashMap<>();
    private ParkingChargeStrategyFactory factory;
    private ParkingTransaction transaction;

    /**
     * This method will create a parking charge and will add it to the
     * transactions list.
     */
    public ParkingTransaction park(ParkingEvent event) {
        Money charge;
        
        //Get values needed to create the parking transaction
        RealParkingLot lot = event.getParkingLot();
        Permit permit = event.getPermit();
        Instant timeIn = event.getTimeIn();
        Instant timeOut = event.getTimeOut();
        Money baseRate = lot.getBaseRate();
        Car car = permit.getCar();
        
        //Get the strategy
        factory = lot.getParkingChargeStrategyFactory();
        ParkingChargeStrategy strategy = factory.makeStrategy();
        
        //If no strategy, use the base rate
        if (strategy == null) {
            charge = strategy.calculateParkingCharge(event, baseRate);
        } else {
            charge = lot.getBaseRate();
        }

        //Go based on time in if there is not time out
        if (event.getTimeOut() == null) {
            transaction = new ParkingTransaction.Builder(timeIn, permit, lot, charge).build();
        } else {
            transaction = new ParkingTransaction.Builder(timeOut, permit, lot, charge).build();
        }

        transactions.add(transaction);
        return transaction;
    }

    public Money getParkingCharges(Customer customer) {
        Money totalCharges = Money.of(0);
        ArrayList<ParkingTransaction> customerTransactions = customerTransaction.get(customer);
        if (customerTransactions == null) {
            return totalCharges;
        }
        for (ParkingTransaction transaction : customerTransactions) {
            Money charge = transaction.getAmount();
            totalCharges = Money.add(totalCharges, charge);
        }
        return totalCharges;
    }

    public Money getParkingCharges(Permit permit) {
        Money totalCharges = Money.of(0);
        ArrayList<ParkingTransaction> permitTransactions = permitTransaction.get(permit);
        if (permitTransactions == null) {
            return totalCharges;
        }
        for (ParkingTransaction transaction : permitTransactions) {
            Money charge = transaction.getAmount();
            totalCharges = Money.add(totalCharges, charge);
        }
        return totalCharges;
    }

    public Money getParkingCharges(Car car) {

        ArrayList<ParkingTransaction> carTransactions = carTransaction.get(car);
        if (carTransactions == null) {
            return Money.of(0);
        }
        Money totalCharges = Money.of(0);
        for (ParkingTransaction transaction : carTransactions) {
            Permit permit = transaction.getPermit();
            if (permit.getCar().equals(car)) {
                Money charge = transaction.getAmount();
                totalCharges = Money.add(totalCharges, charge);
            }
        }
        return totalCharges;
    }

}
