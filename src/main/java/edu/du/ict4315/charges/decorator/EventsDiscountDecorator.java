/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.charges.decorator;

import edu.du.ict4305.parkingsystem.Car;
import edu.du.ict4305.parkingsystem.CarType;
import edu.du.ict4305.parkingsystem.Money;
import edu.du.ict4305.parkingsystem.ParkingEvent;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author cblsa
 */
public class EventsDiscountDecorator extends ParkingChargeCalculatorDecorator {

    private Money lineItemCharge;
    private Money totalCharge;

    //These money values should be stored in a database
    Money nonEventCharge = Money.of(10);
    Money eventCharge = Money.of(20);

    public EventsDiscountDecorator(ParkingChargeCalculator component) {
        super(component);
    }

    @Override
    public Money getParkingCharge(ParkingEvent event) {
        Instant timeIn = event.getTimeIn();

        //Get the parking charge from the component
        Money baseCharge = super.getParkingCharge(event); //call the calclator method of the wrapped calculator

        //Give rate if there is an event going on. Use Time-in regardless of entry only vs entry/exit
        if (isEvent(timeIn)) {
            lineItemCharge = eventCharge;
        } else {
            lineItemCharge = nonEventCharge;
        }

        totalCharge = Money.add(baseCharge, lineItemCharge);

        return totalCharge;
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

        LocalDate[] dateArray = {
            LocalDate.of(2023, 9, 1),
            LocalDate.of(2023, 9, 10),
            LocalDate.of(2023, 9, 15),
            LocalDate.of(2023, 9, 20),
            LocalDate.of(2023, 9, 25)
        };

        // Convert the given Instant to LocalDate in the "America/New_York" time zone
        ZonedDateTime zonedDateTime = parkingDate.atZone(ZoneId.of("America/New_York"));
        LocalDate localDate = zonedDateTime.toLocalDate();

        boolean isEvent = false;
        for (LocalDate eventDate : dateArray) {
            if (eventDate.equals(localDate)) {
                isEvent = true;
                break; // Exit the loop once a match is found
            }
        }
        return isEvent;
    }
}
