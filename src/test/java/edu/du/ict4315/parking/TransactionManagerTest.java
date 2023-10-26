/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking;

import edu.du.ict4315.parking.TransactionManager;
import edu.du.ict4315.parking.ParkingTransaction;
import edu.du.ict4315.parking.parkinglot.ParkingLotType;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.PermitManager;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.decorator.ParkingChargeCalculatorFactory;
import edu.du.ict4315.parking.strategy.ParkingChargeStrategyFactory;
import edu.du.ict4315.parking.strategy.ParkingChargeStrategy;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Candace Saindon
 */
public class TransactionManagerTest {

    private PermitManager permitManager;
    Address address;
    Customer customer;
    CarType type;
    String license;
    Car car;
    Permit permit;
    RealParkingLot parkingLot;
    private ParkingChargeStrategy mockChargeStrategy;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @BeforeEach
    void setup() {
        address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        firstName = "Jane";
        lastName = "Doe";
        phoneNumber = "303-555-5555";
        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();
        license = "123";
        type = CarType.SUV;
        car = new Car(license, type, customer);
        permitManager = new PermitManager();
        permit = permitManager.register(car);
        parkingLot = new RealParkingLot("Sample Lot", address, 100, ParkingLotType.ENTRY);

        ParkingChargeStrategyFactory mockFactory = new TransactionManagerTest.MockParkingChargeStrategyFactory(); // Create a mock factory
        parkingLot.setParkingChargeStrategyFactory(mockFactory);

    }

    /**
     * Test of park method, of class TransactionManager.
     */
    @Test
    public void testPark() {
        System.out.println("park");
        Instant date = Instant.now();
        ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory(); // Create a factory
        parkingLot.setParkingChargeCalculatorFactory(factory);
        // Set the decorator to use for the ParkingLot
        parkingLot.setStrategy("CompactCarDiscount");
        ParkingEvent event = new ParkingEvent(parkingLot, permit, date, date);
        String lotId = "123";
        Money charge = Money.of(10);
        TransactionManager transactionManager = new TransactionManager();
        ParkingTransaction result = transactionManager.park(event);
        assertNotNull(result);
    }

    /**
     * Test of getParkingCharges method, of class TransactionManager.
     */
    @Test
    public void testGetParkingCharges_Permit() {
        System.out.println("getParkingCharges");
        TransactionManager instance = new TransactionManager();
        Money result = instance.getParkingCharges(permit);
        assertNotNull(result);
    }

    /**
     * Test of getParkingCharges method, of class TransactionManager.
     */
    @Test
    public void testGetParkingCharges_Car() {
        System.out.println("getParkingCharges");
        Car car = null;
        TransactionManager instance = new TransactionManager();
        Money result = instance.getParkingCharges(car);
        assertNotNull(result);
    }

    // Mock ParkingChargeStrategyFactory for testing
    //This class uses the MockParkingChargeStrategy class to calculate the parking charge
    private class MockParkingChargeStrategyFactory implements ParkingChargeStrategyFactory {

        @Override
        public ParkingChargeStrategy makeStrategy() {
            return new TransactionManagerTest.MockParkingChargeStrategy(); // Return a mock strategy
        }
    }

    // Mock ParkingChargeStrategy for testing
    private class MockParkingChargeStrategy implements ParkingChargeStrategy {

        @Override
        public Money calculateParkingCharge(ParkingEvent event, Money baseRate) {
            return Money.of(10.0);
        }

        @Override
        public String getStrategyName() {
            return "Test Mock Parking Charge Strategy";
        }
    }

}
