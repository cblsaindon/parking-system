/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.decorator.CompactCarDiscountDecorator;
import edu.du.ict4315.parking.decorator.ParkingChargeCalculator;
import edu.du.ict4315.parking.decorator.FlatRateCalculator;
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.PermitManager;

import java.time.Instant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


/**
 *
 * @author candace.saindon
 */

public class ParkingChargeCalculatorDecoratorTest {

    private ParkingChargeCalculator realCalculator;
    private ParkingEvent event;

    private PermitManager permitManager;
    Address address;
    RealParkingLot lot;
    Customer customer;

    @BeforeEach
    public void setUp() {
        permitManager = new PermitManager();
        // Initialize your real ParkingChargeCalculator and ParkingEvent instances here
    }

    @Test
    public void testDecoratorWithRealCalculator() {
        
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
        event = new ParkingEvent(lot, permit, timeIn, timeOut);
        
        Money expectedCharge = Money.of(12.00);
        // Call the method you want to test on the decorator
        Money charge = decorator.getParkingCharge(event);


        assertEquals(expectedCharge.getDollars(), charge.getDollars());
    }
}
