/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author cblsa
 */
public class WeekendPrimeTimeDiscountDecorator extends ParkingChargeCalculatorDecorator {

    private Money totalCharge;
    private Money lineItemCharge;

    //These money values should be stored in a database
    private Money weekendCharge = Money.of(20);
    private Money weekdayCharge = Money.of(10);
    private Money primeTimeCharge = Money.of(40);
    private Money dayCharge = Money.of(20);

    public WeekendPrimeTimeDiscountDecorator(ParkingChargeCalculator parkingChargeCalculator) {
        super(parkingChargeCalculator);
    }

    @Override
    public Money getParkingCharge(ParkingEvent event) {
        Instant timeIn = event.getTimeIn();
        Instant timeOut = event.getTimeOut();
        //charge the customer more if it on the weekend
        //On entry/exit lots, look at both time in and time out as the cars must be only during the weekday to get the deal

        //Get the parking charge from the component
        Money baseCharge = super.getParkingCharge(event); //call the calclator method of the wrapped calculator

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

        totalCharge = Money.add(baseCharge, lineItemCharge);

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
}
