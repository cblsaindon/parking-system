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

    /**
     * This method will create a parking charge and will add it to the
     * transactions list.
     */
    public ParkingTransaction park(Instant date, Permit permit, ParkingLot parkingLot) {
        Money baseRate = parkingLot.getBaseRate();
        Car car = permit.getCar();
        factory = parkingLot.getParkingChargeStrategyFactory();
        ParkingChargeStrategy strategy = factory.makeStrategy();
        if (strategy == null) {
            throw new IllegalArgumentException("Null strategy");
        }

        Money charge = strategy.calculateParkingCharge(date, permit, baseRate);
        ParkingTransaction transaction = new ParkingTransaction.Builder(date, permit, parkingLot, charge).build();
        transactions.add(transaction);
        //carTransaction.put(car, transaction);
        //permitTransaction.put(permit, transaction);
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

    //extra code from ICT4305
    /*
    // Calculates the overnight parking charge for a given car and the number of days it was parked.
    private Money calculateOvernightCharge(Car car, long daysInLot) {
        double dailyRate = 30.00;
        double discountedRate = getDiscountedRate(car, dailyRate);
        double totalDollars = discountedRate * daysInLot;
        return new Money(totalDollars);
    }

    // Calculates the hourly parking charge for a given car and the number of hours it was parked.
    private Money calculateHourlyCharge(Car car, long hoursInLot) {
        double hourlyRate = 15.00;
        double discountedRate = getDiscountedRate(car, hourlyRate);
        double totalDollars = discountedRate * hoursInLot;
        return new Money(totalDollars);
    }

    // Calculates the one-time parking charge for a given car.
    private Money calculateOneTimeCharge(Car car) {
        double dailyRate = 32.00;
        double discountedRate = getDiscountedRate(car, dailyRate);
        return new Money(discountedRate);
    }

    // Calculates the discounted rate for a given rate and car type.
    private double getDiscountedRate(Car car, double rate) {
        CARTYPE carType = car.getCarType();
        double discountPercentage = carType.getDiscountPercentage();
        return rate * (1 - discountPercentage);
    }
     */
}
