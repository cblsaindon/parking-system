/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.charges.strategy;

import edu.du.ict4305.parkingsystem.Car;
import edu.du.ict4305.parkingsystem.Money;
import edu.du.ict4305.parkingsystem.ParkingLotType;
import edu.du.ict4305.parkingsystem.CarType;
import edu.du.ict4305.parkingsystem.ParkingLot;
import edu.du.ict4305.parkingsystem.Permit;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * This Strategy implements a discount for certain car types, and also on event
 * days
 *
 * @author candace.saindon
 */
public class DiscountEventsStrategy implements ParkingChargeStrategy {

    private Car car;
    private Money carTypeCharge;
    private Money lineItemCharge;
    private Money totalCharge;

    //These money values should be stored in a database
    Money nonEventCharge = Money.of(10);
    Money eventCharge = Money.of(20);

    @Override
    public Money calculateParkingCharge(Instant date, Permit permit, Money baseRate) {
        //set the base rate
        if (totalCharge == null) {
            totalCharge = Money.of(0);
        } else {
            totalCharge = baseRate;
        }

        //Get the discounted rate based on the car type
        car = permit.getCar();
        double discountedRate = getDiscountedRate(car, totalCharge);
        carTypeCharge = Money.of(discountedRate);
        totalCharge = Money.add(totalCharge, carTypeCharge);

        //Give rate if there is an event going on
        if (isEvent(date)) {
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


/*
        Instant lotEnterTime = car.getLotEnter();
        Instant lotExitTime = date;
        Money charge = new Money(0);

        //1. find out what lot type it is based on the lotid
        ParkingLot lot = getLotTypeByLotId(lotId);
        ParkingLotType lotType = lot.getLotType();

        if (lotType == ParkingLotType.ENTRY) {
            Duration duration = Duration.between(lotEnterTime, lotExitTime);
            long daysInLot = duration.toHours();
            //2. calculate the charge
            if (daysInLot > 0) {
                charge = calculateOvernightCharge(car, daysInLot);
            }

        } else if (lotType == ParkingLotType.ENTRYEXIT) {
            //charge based on hours

            Duration duration = Duration.between(lotEnterTime, lotExitTime);
            long hoursInLot = duration.toHours();

            charge = calculateHourlyCharge(car, hoursInLot);
        }

        parkingLotType = parkingLot.getLotType();

        if (parkingLotType == ParkingLotType.ENTRY) {

        } else if (parkingLotType == ParkingLotType.ENTRYEXIT) {

        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
 */
