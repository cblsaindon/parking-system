/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking.charges.strategy;

import edu.du.ict4305.parkingsystem.Address;
import edu.du.ict4305.parkingsystem.CARTYPE;
import edu.du.ict4305.parkingsystem.Car;
import edu.du.ict4305.parkingsystem.Customer;
import edu.du.ict4305.parkingsystem.Money;
import edu.du.ict4305.parkingsystem.PARKINGLOTTYPE;
import edu.du.ict4305.parkingsystem.ParkingLot;
import edu.du.ict4305.parkingsystem.Permit;
import edu.du.ict4305.parkingsystem.PermitManager;
import java.time.Instant;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author cblsa
 */
public class DiscountEventsStrategyTest {
    //SUV, Compact, Event, Non-Event
    //set up the initial conditions
    //invoke the strategy's calculateParkingCharge() method
    //assert that the calculated charge matches your expectations.

    private DiscountEventsStrategy strategy;
    private ParkingLot parkingLot;
    private Car car;
    private Permit permit;
    private Address address;
    private Customer customer;
    private PermitManager permitManager;

    @Before
    public void setUp() {
        strategy = new DiscountEventsStrategy();
        address = new Address("123 Main St", "Apt 1", "Denver", "CO", "80202");
        parkingLot = new ParkingLot("Sample Lot", address, 100, PARKINGLOTTYPE.ENTRY);
        customer = new Customer("Jane", "Doe", address, "303-555-5555");
        car = new Car("123", CARTYPE.COMPACT, customer);
        permit = permitManager.register(car);
    }

    @Test
    public void testNonEventDay() {
        //car parked on a non-event day
        Instant nonEventDate = Instant.parse("2023-09-02T10:00:00Z");
        Money baseRate = new Money(10.0);

        Money charge = strategy.calculateParkingCharge(nonEventDate, permit, baseRate);
        //Calculate the charge we expect manually
        double expectedCharge = (10.0 * (1 - car.getCarType().getDiscountPercentage())) + 10;
        Money expectedMoney = new Money(expectedCharge);

        assertEquals(expectedMoney, charge);
    }

    @Test
    public void testEventDay() {
        //car is parked during an event
        Instant nonEventDate = Instant.parse("2023-09-01T10:00:00Z");
        Money baseRate = new Money(10.0);

        Money charge = strategy.calculateParkingCharge(nonEventDate, permit, baseRate);
        //Calculate the charge we expect manually
        double expectedCharge = (10.0 * (1 - car.getCarType().getDiscountPercentage())) + 10;
        Money expectedMoney = new Money(expectedCharge);

        assertEquals(expectedMoney, charge);
    }

    @Test
    public void testGetStrategyName() {
        assertEquals("Discount Events", strategy.getStrategyName());
    }
}
