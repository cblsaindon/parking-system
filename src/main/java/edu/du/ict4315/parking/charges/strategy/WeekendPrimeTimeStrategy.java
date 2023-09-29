/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.charges.strategy;

import edu.du.ict4305.parkingsystem.Money;
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
    Money totalCharge;
    Money lineItemCharge;

    //These money values should be stored in a database
    Money weekendCharge = new Money(20);
    Money weekdayCharge = new Money(10);
    Money primeTimeCharge = new Money(40);
    Money dayCharge = new Money(20);
    
    @Override
    public Money calculateParkingCharge(Instant date, Permit permit, Money baseRate) {
        //set the base rate
        if (totalCharge == null) {
            totalCharge = new Money(0);
        } else {
            totalCharge = baseRate;
        }

        //charge the customer more if it on the weekend
        if (isWeekend(date)) {
            lineItemCharge = weekendCharge;
        } else {
            lineItemCharge = weekdayCharge;
        }
        totalCharge = Money.add(totalCharge, lineItemCharge);

        //charge the customer more if it is prime time i.e. the evening/night
        if (isPrimeTime(date)) {
            lineItemCharge = primeTimeCharge;
        } else {
            lineItemCharge = dayCharge;
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

        isPrimeTime = hour > 6;

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
