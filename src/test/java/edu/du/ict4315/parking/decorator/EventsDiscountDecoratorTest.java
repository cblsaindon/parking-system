/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.decorator.CompactCarDiscountDecorator;
import edu.du.ict4315.parking.decorator.ParkingChargeCalculator;
import edu.du.ict4315.parking.decorator.FlatRateCalculator;
import edu.du.ict4315.parking.decorator.ParkingChargeCalculatorFactory;
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.parkinglot.ParkingLotType;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.PermitManager;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import java.time.Instant;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


/**
 *
 * @author cblsa
 */
public class EventsDiscountDecoratorTest {

    private PermitManager permitManager;
    Address address;
    RealParkingLot lot;
    Customer customer;

    @BeforeEach
    void setup() {
        permitManager = new PermitManager();
        customer = new Customer.Builder("Jane", "Doe").address(address).phoneNumber("123-456-7890").build();
        address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        lot = new RealParkingLot("Sample Lot", address, 100, ParkingLotType.ENTRY);
    }

    @Test
    public void testGetParkingChargeWithEventsDiscount() {
        ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory(); // Create a factory
        lot.setParkingChargeCalculatorFactory(factory);
        // Set the decorator to use for the ParkingLot
        lot.setStrategy("EventsDiscount");

        // Create a car and permit
        Car car = new Car("ABC123", CarType.COMPACT, customer);
        Permit permit = permitManager.register(car);

        // Create a FlatRateCalculator (or any other calculator)
        ParkingChargeCalculator calculator = new FlatRateCalculator();

        // Wrap the calculator with CompactCarDiscountDecorator
        ParkingChargeCalculator decorator = new CompactCarDiscountDecorator(calculator);

        // Calculate the parking charge with the decorator
        Instant timeIn = Instant.parse("2023-09-01T10:00:00Z");
        Instant timeOut = Instant.parse("2023-09-02T10:00:00Z");
        ParkingEvent event = new ParkingEvent(lot, permit, timeIn, timeOut);
        Money charge = lot.getParkingCharge(event);

        // Expected charge: $35 (Flat rate) + $20 surcharge for events
        Money expectedCharge = Money.of(35.00);

        // Check if the calculated charge matches the expected charge
        assertEquals(expectedCharge.getDollars(), charge.getDollars());
    }

    @Test
    public void testGetParkingChargeWithoutEventsDiscount() {
        ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory(); // Create a factory
        lot.setParkingChargeCalculatorFactory(factory);
        // Create a Car
        Car car = new Car("ABC123", CarType.SUV, customer);

        // Create a Permit
        Permit permit = permitManager.register(car);

        // Create a FlatRateCalculator (or any other calculator)
        ParkingChargeCalculator calculator = new FlatRateCalculator();

        // Wrap the calculator with CompactCarDiscountDecorator
        ParkingChargeCalculator decorator = new CompactCarDiscountDecorator(calculator);

        // Calculate the parking charge with the decorator
        Instant timeIn = Instant.now().minusSeconds(3600); // 1 hour ago
        Instant timeOut = Instant.now(); // Now
        ParkingEvent event = new ParkingEvent(lot, permit, timeIn, timeOut);
        Money charge = lot.getParkingCharge(event);

        // Expected charge: $15 (Flat rate) - No surcharge for non-events
        Money expectedCharge = Money.of(15.00);

        // Check if the calculated charge matches the expected charge
        assertEquals(expectedCharge.getDollars(), charge.getDollars());
    }  

}
