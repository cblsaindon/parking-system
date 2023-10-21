/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.charges.decorator;

/**
 *
 * @author candace.saindon
 */
public class ParkingChargeCalculatorFactory {

    public ParkingChargeCalculator getParkingChargeCalculator(String strategy) {
        if ("CompactCarDiscount".equalsIgnoreCase(strategy)) {
            System.out.println("CompactCarDiscountDecorator created");
            return new CompactCarDiscountDecorator(new FlatRateCalculator());
        } else if ("EventsDiscount".equalsIgnoreCase(strategy)) {
            System.out.println("EventsDiscountDecorator created");
            return new EventsDiscountDecorator(new FlatRateCalculator());
        } else if ("WeekendPrimeTimeDiscount".equalsIgnoreCase(strategy)) {
            System.out.println("WeekendPrimeTimeDiscountDecorator created");
            return new WeekendPrimeTimeDiscountDecorator(new FlatRateCalculator());
        } else if ("CompactCarEventsDiscount".equalsIgnoreCase(strategy)) {
            System.out.println("CompactCar and Events created");
            return new CompactCarDiscountDecorator(new EventsDiscountDecorator(new FlatRateCalculator()));
        } else if (strategy == null) {
            System.out.println("FlatRate calculator created");
            return new FlatRateCalculator();
        }
        return null;
    }
}
