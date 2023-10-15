/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking.charges.strategy;

import edu.du.ict4305.parkingsystem.Address;
import edu.du.ict4305.parkingsystem.CarType;
import edu.du.ict4305.parkingsystem.Car;
import edu.du.ict4305.parkingsystem.Customer;
import edu.du.ict4305.parkingsystem.Money;
import edu.du.ict4305.parkingsystem.ParkingEvent;
import edu.du.ict4305.parkingsystem.ParkingLotType;
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
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Before
    public void setUp() {
        strategy = new DiscountEventsStrategy();
        address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        parkingLot = new ParkingLot("Sample Lot", address, 100, ParkingLotType.ENTRY);
        firstName = "Jane";
        lastName = "Doe";
        phoneNumber = "303-555-5555";
        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();
    }

    @Test
    public void testNonEventCompactDay() {
        //car parked on a non-event day
        car = new Car("123", CarType.COMPACT, customer);
        permit = permitManager.register(car);
        Instant nonEventDate = Instant.parse("2023-09-02T10:00:00Z");
        Money baseRate = Money.of(10.0);

        ParkingEvent event = new ParkingEvent(parkingLot, permit, nonEventDate);

        Money charge = strategy.calculateParkingCharge(event, baseRate);
        //Calculate the charge we expect manually
        double expectedCharge = (10.0 * (1 - car.getCarType().getDiscountPercentage())) + 10;
        Money expectedMoney = Money.of(expectedCharge);

        assertEquals(expectedMoney, charge);
    }

    @Test
    public void testEventCompactDay() {
        //car is parked during an event
        car = new Car("123", CarType.COMPACT, customer);
        permit = permitManager.register(car);
        Instant EventDate = Instant.parse("2023-09-01T10:00:00Z");
        Money baseRate = Money.of(10.0);

        ParkingEvent event = new ParkingEvent(parkingLot, permit, EventDate);

        Money charge = strategy.calculateParkingCharge(event, baseRate);
        //Calculate the charge we expect manually
        double expectedCharge = (10.0 * (1 - car.getCarType().getDiscountPercentage())) + 10;
        Money expectedMoney = Money.of(expectedCharge);

        assertEquals(expectedMoney, charge);
    }

    @Test
    public void testNonEventSUVDay() {
        //car parked on a non-event day
        car = new Car("123", CarType.SUV, customer);
        permit = permitManager.register(car);
        Instant nonEventDate = Instant.parse("2023-09-02T10:00:00Z");
        Money baseRate = Money.of(10.0);

        ParkingEvent event = new ParkingEvent(parkingLot, permit, nonEventDate);

        Money charge = strategy.calculateParkingCharge(event, baseRate);
        //Calculate the charge we expect manually
        double expectedCharge = (10.0 * (1 - car.getCarType().getDiscountPercentage())) + 10;
        Money expectedMoney = Money.of(expectedCharge);

        assertEquals(expectedMoney, charge);
    }

    @Test
    public void testEventSUVDay() {
        //car is parked during an event
        car = new Car("123", CarType.SUV, customer);
        permit = permitManager.register(car);
        Instant EventDate = Instant.parse("2023-09-01T10:00:00Z");
        Money baseRate = Money.of(10.0);

        ParkingEvent event = new ParkingEvent(parkingLot, permit, EventDate);

        Money charge = strategy.calculateParkingCharge(event, baseRate);
        //Calculate the charge we expect manually
        double expectedCharge = (10.0 * (1 - car.getCarType().getDiscountPercentage())) + 10;
        Money expectedMoney = Money.of(expectedCharge);

        assertEquals(expectedMoney, charge);
    }

    @Test
    public void testGetStrategyName() {
        assertEquals("Discount Events", strategy.getStrategyName());
    }
}
