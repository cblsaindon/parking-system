/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.charges.strategy;

import edu.du.ict4305.parkingsystem.Money;
import edu.du.ict4305.parkingsystem.ParkingEvent;
import edu.du.ict4305.parkingsystem.ParkingLot;
import edu.du.ict4305.parkingsystem.Permit;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author candace.saindon
 */
public class WeekendPrimeTimeStrategy implements ParkingChargeStrategy {

    private Money totalCharge;
    private Money lineItemCharge;

    //These money values should be stored in a database
    private Money weekendCharge = Money.of(20);
    private Money weekdayCharge = Money.of(10);
    private Money primeTimeCharge = Money.of(40);
    private Money dayCharge = Money.of(20);

    @Override
    public Money calculateParkingCharge(ParkingEvent event, Money baseRate) {
        Instant timeIn = event.getTimeIn();
        Instant timeOut = event.getTimeOut();
        //set the base rate
        if (totalCharge == null) {
            totalCharge = Money.of(0);
        } else {
            totalCharge = baseRate;
        }

        //charge the customer more if it on the weekend
        //On entry/exit lots, look at both time in and time out as the cars must be only during the weekday to get the deal
        if (timeOut != null) {
            if (isWeekend(timeIn) && isWeekend(timeOut)) {
                lineItemCharge = weekendCharge;
            } else {
                lineItemCharge = weekdayCharge;
            }
        } else {
            if (isWeekend(timeIn)) {
                lineItemCharge = weekendCharge;
            } else {
                lineItemCharge = weekdayCharge;
            }
        }

        totalCharge = Money.add(totalCharge, lineItemCharge);

        //charge the customer more if it is prime time i.e. the evening/night
        //On entry/exit lots, look at both time in and time out as the cars must be only during day to get the deal
        if (timeOut != null) {
            if (isPrimeTime(timeIn) || isPrimeTime(timeOut)) {
                lineItemCharge = primeTimeCharge;
            } else {
                lineItemCharge = dayCharge;
            }
        } else {
            if (isPrimeTime(timeIn)) {
                lineItemCharge = primeTimeCharge;
            } else {
                lineItemCharge = dayCharge;
            }
        }

        totalCharge = Money.add(totalCharge, lineItemCharge);

        return totalCharge;
    }

    private boolean isPrimeTime(Instant date) {
        // Create an Instant representing a specific point in time
        Instant instant = date.now();
        boolean isPrimeTime = false;

        // Specify a time zone
        ZoneId zoneId = ZoneId.of("America/New_York");

        // Convert the Instant to a ZonedDateTime in the specified time zone
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);

        // Get the day of the week from the ZonedDateTime
        int hour = zonedDateTime.getHour();

        isPrimeTime = hour > 18;

        return isPrimeTime;
    }

    private boolean isWeekend(Instant date) {
        // Create an Instant representing a specific point in time
        Instant instant = date.now();
        boolean isWeekend = false;

        // Specify a time zone
        ZoneId zoneId = ZoneId.of("America/New_York");

        // Convert the Instant to a ZonedDateTime in the specified time zone
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);

        // Get the day of the week from the ZonedDateTime
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();

        isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;

        return isWeekend;
    }

    @Override
    public String getStrategyName() {
        return "Weekend Prime Time";
    }
}
