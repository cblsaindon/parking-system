/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import edu.du.ict4315.parking.charges.factory.ParkingChargeStrategyFactory;
import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategy;
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
    ParkingLot parkingLot;
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
        parkingLot = new ParkingLot("Sample Lot", address, 100, ParkingLotType.ENTRY);
        
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
        String lotId = "123";
        Money charge = Money.of(10);
        TransactionManager transactionManager = new TransactionManager();
        ParkingTransaction result = transactionManager.park(date, permit, parkingLot);
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
        public Money calculateParkingCharge(Instant date, Permit permit, Money baseRate) {
            return Money.of(10.0);
        }

        @Override
        public String getStrategyName() {
            return "Test Mock Parking Charge Strategy";
        }
    }

}
