/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.charges.decorator;

import edu.du.ict4305.parkingsystem.Address;
import edu.du.ict4305.parkingsystem.Car;
import edu.du.ict4305.parkingsystem.CarType;
import edu.du.ict4305.parkingsystem.Customer;
import edu.du.ict4305.parkingsystem.Money;
import edu.du.ict4305.parkingsystem.ParkingEvent;
import edu.du.ict4305.parkingsystem.ParkingLotType;
import edu.du.ict4305.parkingsystem.Permit;
import edu.du.ict4305.parkingsystem.PermitManager;
import edu.du.ict4305.parkingsystem.RealParkingLot;
import java.time.Instant;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author candace.saindon
 */
public class CompactCarDiscountDecoratorTest {

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
    public void testGetParkingChargeWithCompactCarDiscount() {
        ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory(); // Create a factory
        lot.setParkingChargeCalculatorFactory(factory);
        // Set the decorator to use for the ParkingLot
        lot.setStrategy("CompactCarDiscount");

        // Create a car and permit
        Car car = new Car("ABC123", CarType.COMPACT, customer);
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

        // Expected charge: $15 (Flat rate) - 20% discount for COMPACT car type
        Money expectedCharge = Money.of(12.00);

        // Check if the calculated charge matches the expected charge
        assertEquals(expectedCharge.getDollars(), charge.getDollars());
    }

    @Test
    public void testGetParkingChargeWithoutCompactCarDiscount() {
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

        // Set the decorator to use for the ParkingLot
        //lot.getParkingChargeStrategyFactory().setParkingChargeCalculator(decorator);
        // Calculate the parking charge with the decorator
        Instant timeIn = Instant.now().minusSeconds(3600); // 1 hour ago
        Instant timeOut = Instant.now(); // Now
        ParkingEvent event = new ParkingEvent(lot, permit, timeIn, timeOut);
        Money charge = lot.getParkingCharge(event);

        // Expected charge: $15 (Flat rate) - No discount for non-COMPACT car type
        Money expectedCharge = Money.of(15.00);

        // Check if the calculated charge matches the expected charge
        assertEquals(expectedCharge.getDollars(), charge.getDollars());
    }
}
