/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import java.time.Instant;

/**
 *
 * @author candace.saindon
 */
public class CompactCarDiscountDecorator extends ParkingChargeCalculatorDecorator {

    //Reference to the component that this decorator wraps
    private ParkingChargeCalculator component;

    public CompactCarDiscountDecorator(ParkingChargeCalculator component) {
        super(component);
    }

    @Override
    public Money getParkingCharge(ParkingEvent event) {
        Instant timeIn = event.getTimeIn();
        Instant timeOut = event.getTimeOut();
        Car car = event.getPermit().getCar();

        //Get the parking charge from the component
        Money baseCharge = super.getParkingCharge(event); //call the calclator method of the wrapped calculator

        //Get the discounted rate based on the car type
        Money discountedRate = getDiscountedRate(car, baseCharge);

        return discountedRate;
    }

    // Calculates the discounted rate for a given rate and car type.
    private Money getDiscountedRate(Car car, Money rate) {
        CarType carType = car.getCarType();
        double discountPercentage = carType.getDiscountPercentage();
        Money discountedRate = Money.times(rate, (1-discountPercentage));
        return discountedRate;
    }
}
