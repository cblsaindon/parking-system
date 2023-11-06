/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.strategy;

import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.parkinglot.ParkingLotType;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import edu.du.ict4315.parking.Permit;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.io.Serializable;

/**
 * This Strategy implements a discount for certain car types, and also on event
 * days
 *
 * @author candace.saindon
 */
public class DiscountEventsStrategy implements ParkingChargeStrategy, Serializable {

    private Car car;
    private Money carTypeCharge;
    private Money lineItemCharge;
    private Money totalCharge;

    //These money values should be stored in a database
    Money nonEventCharge = Money.of(10);
    Money eventCharge = Money.of(20);

    @Override
    public Money calculateParkingCharge(ParkingEvent event, Money baseRate) {
        Instant timeIn = event.getTimeIn();

        //set the base rate
        if (totalCharge == null) {
            totalCharge = Money.of(0);
        } else {
            totalCharge = baseRate;
        }

        //Get the discounted rate based on the car type
        car = event.getPermit().getCar();
        double discountedRate = getDiscountedRate(car, totalCharge);
        carTypeCharge = Money.of(discountedRate);
        totalCharge = Money.add(totalCharge, carTypeCharge);

        //Give rate if there is an event going on. Use Time-in regardless of entry only vs entry/exit
        if (isEvent(timeIn)) {
            lineItemCharge = eventCharge;
        } else {
            lineItemCharge = nonEventCharge;
        }

        totalCharge = Money.add(totalCharge, lineItemCharge);

        return totalCharge;
    }

    @Override
    public String getStrategyName() {
        return "Discount Events";
    }

    // Calculates the discounted rate for a given rate and car type.
    private double getDiscountedRate(Car car, Money rate) {
        CarType carType = car.getCarType();
        //Get the money value in dollars (double) to determine the percentage discount
        double dollarRate = rate.getDollars();
        double discountPercentage = carType.getDiscountPercentage();
        return dollarRate * (1 - discountPercentage);
    }

    private boolean isEvent(Instant parkingDate) {
        // The is a stub for event dates. This would be stored in a database.
        Instant instant = parkingDate.now();
        LocalDate[] dateArray = {
            LocalDate.of(2023, 9, 1),
            LocalDate.of(2023, 9, 10),
            LocalDate.of(2023, 9, 15),
            LocalDate.of(2023, 9, 20),
            LocalDate.of(2023, 9, 25)
        };
        boolean isEvent = false;
        // Specify a time zone
        ZoneId zoneId = ZoneId.of("America/New_York");

        // Convert the Instant to a ZonedDateTime in the specified time zone
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);

        // Get the day of the week from the ZonedDateTime
        LocalDate localDate = zonedDateTime.toLocalDate();

        boolean isInArray = false;
        for (LocalDate eventDate : dateArray) {
            if (eventDate.equals(localDate)) {
                isInArray = true;
                break; // Exit the loop once a match is found
            }
        }
        return isEvent;
    }
}


