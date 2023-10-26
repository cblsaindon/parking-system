/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking.strategy;

import edu.du.ict4315.parking.strategy.WeekendPrimeTimeStrategy;
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.parkinglot.ParkingLotType;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.PermitManager;
import java.time.Instant;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cblsa
 */
public class WeekendPrimeTimeStrategyTest {
    //We need to test that this correctly charges more for weekends and nights

    private WeekendPrimeTimeStrategy strategy;
    private RealParkingLot parkingLot;
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
        strategy = new WeekendPrimeTimeStrategy();
        address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        parkingLot = new RealParkingLot("Sample Lot", address, 100, ParkingLotType.ENTRY);
        firstName = "Jane";
        lastName = "Doe";
        phoneNumber = "303-555-5555";
        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();
        car = new Car("123", CarType.COMPACT, customer);
        permit = permitManager.register(car);
    }

    @Test
    public void testCalculateParkingCharge_WeekdayPrimeTime() {
        // Create an Instant for a weekday prime time (adjust date and time as needed)
        Instant weekdayPrimeTime = Instant.parse("2023-09-20T19:30:00Z");
        Money baseRate = Money.of(10.0); // Adjust the base rate as needed

        ParkingEvent event = new ParkingEvent(parkingLot, permit, weekdayPrimeTime);
        Money charge = strategy.calculateParkingCharge(event, baseRate);

        // Calculate the expected charge manually based on your logic
        double expectedCharge = 10.0 + 40.0; // Weekday charge + Prime time charge
        Money expectedMoney = Money.of(expectedCharge);

        assertEquals(expectedMoney, charge);
    }

    @Test
    public void testCalculateParkingCharge_WeekdayDaytime() {
        // Create an Instant for a weekday daytime (adjust date and time as needed)
        Instant weekdayDaytime = Instant.parse("2023-09-20T12:00:00Z");
        Money baseRate = Money.of(10.0); // Adjust the base rate as needed

        ParkingEvent event = new ParkingEvent(parkingLot, permit, weekdayDaytime);
        Money charge = strategy.calculateParkingCharge(event, baseRate);

        // Calculate the expected charge manually based on your logic
        double expectedCharge = 10.0 + 20.0; // Weekday charge + Daytime charge
        Money expectedMoney = Money.of(expectedCharge);

        assertEquals(expectedMoney, charge);
    }

    @Test
    public void testCalculateParkingCharge_WeekendPrimeTime() {
        // Create an Instant for a weekend prime time (adjust date and time as needed)
        Instant weekendPrimeTime = Instant.parse("2023-09-23T21:00:00Z");
        Money baseRate = Money.of(10.0); // Adjust the base rate as needed

        ParkingEvent event = new ParkingEvent(parkingLot, permit, weekendPrimeTime);
        Money charge = strategy.calculateParkingCharge(event, baseRate);

        // Calculate the expected charge manually based on your logic
        double expectedCharge = 10.0 + 20.0 + 40.0; // Weekend charge + Prime time charge
        Money expectedMoney = Money.of(expectedCharge);

        assertEquals(expectedMoney, charge);
    }

    @Test
    public void testCalculateParkingCharge_WeekendDaytime() {
        // Create an Instant for a weekend daytime (adjust date and time as needed)
        Instant weekendDaytime = Instant.parse("2023-09-23T14:00:00Z");
        Money baseRate = Money.of(10.0); // Adjust the base rate as needed

        ParkingEvent event = new ParkingEvent(parkingLot, permit, weekendDaytime);
        Money charge = strategy.calculateParkingCharge(event, baseRate);

        // Calculate the expected charge manually based on your logic
        double expectedCharge = 10.0 + 20.0; // Weekend charge + Daytime charge
        Money expectedMoney = Money.of(expectedCharge);

        assertEquals(expectedMoney, charge);
    }

    @Test
    public void testGetStrategyName() {
        assertEquals("Weekend Prime Time", strategy.getStrategyName());
    }
}
