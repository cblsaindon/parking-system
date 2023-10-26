/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4315.parking.parkinglot;

import edu.du.ict4315.parking.parkinglot.ParkingLotType;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.observers.ParkingObserver;
import edu.du.ict4315.parking.decorator.ParkingChargeCalculatorFactory;
import edu.du.ict4315.parking.strategy.DiscountEventsStrategyFactory;
import edu.du.ict4315.parking.strategy.ParkingChargeStrategyFactory;
import edu.du.ict4315.parking.strategy.ParkingChargeStrategy;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author candace.saindon
 */
public class ParkingLotTest {

    private Address address;
    private Car car;
    private RealParkingLot lot;
    private ParkingChargeStrategy mockChargeStrategy;
    private ParkingOffice office;
    private Permit permit;

    @BeforeEach
    public void setUpClass() {
        Address address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        String firstName = "Jane";
        String lastName = "Doe";
        String phoneNumber = "303-555-5555";
        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();
        String license = "123";
        CarType type = CarType.SUV;
        car = new Car(license, type, customer);
        permit = new Permit("123", car);
        lot = new RealParkingLot("Sample Lot", address, 100, ParkingLotType.ENTRY);
        mockChargeStrategy = new MockParkingChargeStrategy();

        office = new ParkingOffice("ParkingOffice", address, null, null, null);
        lot.registerObserver(office);
    }

    /**
     * Test of getLotId method, of class ParkingLot.
     */
    @Test
    public void testGetLotId() {
        System.out.println("getLotId");
        String lotId = "123";
        //Address address = new Address("123 Main St", "Apt 2", "Denver", "80241", "Colorado");
        int capacity = 100;

        RealParkingLot instance = new RealParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
        String expResult = lotId;
        String result = instance.getLotId();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAddress method, of class ParkingLot.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String lotId = "123";
        int capacity = 100;
        //Address address = new Address("123 Main St", "Apt 2", "Denver", "80241", "Colorado");

        RealParkingLot instance = new RealParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
        Address expResult = address;
        Address result = instance.getAddress();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCapacity method, of class ParkingLot.
     */
    @Test
    public void testGetCapacity() {
        System.out.println("getCapacity");
        String lotId = "123";
        //Address address = new Address("123 Main St", "Apt 2", "Denver", "80241", "Colorado");

        int capacity = 100;

        RealParkingLot instance = new RealParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
        int expResult = capacity;
        int result = instance.getCapacity();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCarsInLot method, of class ParkingLot.
     */
    @Test
    public void testGetCarsInLot() {
        System.out.println("getCarsInLot");
        String lotId = "123";
        //Address address = new Address("123 Main St", "Apt 2", "Denver", "80241", "Colorado");
        int capacity = 100;

        RealParkingLot instance = new RealParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
        int expResult = 0;
        int result = instance.getCarsInLot();
        assertEquals(expResult, result);

    }

    /**
     * Test of getEmptySlots method, of class ParkingLot.
     */
    @Test
    public void testGetEmptySlots() {
        System.out.println("getEmptySlots");
        String lotId = "123";
        //Address address = new Address("123 Main St", "Apt 2", "Denver", "80241", "Colorado");
        int capacity = 100;

        RealParkingLot instance = new RealParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
        int expResult = capacity;
        int result = instance.getEmptySlots();
        assertEquals(expResult, result);

    }

  
    @Test
    public void testSetParkingChargeStrategy() {
        ParkingChargeStrategyFactory mockFactory = new MockParkingChargeStrategyFactory(); // Create a mock factory
        lot.setParkingChargeStrategyFactory(mockFactory);
        assertEquals(mockFactory, lot.getParkingChargeStrategyFactory());
    }

    @Test
    public void testGetParkingCharge() {
        ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory(); // Create a factory
        lot.setParkingChargeCalculatorFactory(factory);

        ParkingEvent event = new ParkingEvent(lot, new Permit("Sample Permit", car), Instant.now(), Instant.now());

        // Calculate the parking charge using the configured factory
        Money charge = lot.getParkingCharge(event);

        // expected charge value vs. the mock strategy
        assertEquals(Money.of(15.0).getDollars(), charge.getDollars()); // Adjust the expected value accordingly
    }

// Mock ParkingChargeStrategyFactory for testing
    private class MockParkingChargeStrategyFactory implements ParkingChargeStrategyFactory {

        @Override
        public ParkingChargeStrategy makeStrategy() {
            return new MockParkingChargeStrategy(); // Return a mock strategy
        }
    }

// Mock ParkingChargeStrategy for testing
    private class MockParkingChargeStrategy implements ParkingChargeStrategy {

        @Override
        public String getStrategyName() {
            return "Test Mock Parking Charge Strategy";
        }

        @Override
        public Money calculateParkingCharge(ParkingEvent event, Money baseRate) {
            return Money.of(10.0);
        }
    }

    /**
     * Test of toString method, of class ParkingLot.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String lotId = "123";
        //Address address = new Address("123 Main St", "Apt 2", "Denver", "80241", "Colorado");
        int capacity = 100;
        int carsInLot = 0;

        RealParkingLot instance = new RealParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
        String expResult = "edu.du.ict4305.parkingsystem.RealParkingLot" + "[lotId=" + lotId + ", address=" + address
                + ",capacity=" + capacity + ", carsInLot=" + carsInLot + "]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterObserver() {
        lot.registerObserver(office);
        List<ParkingObserver> observers = lot.getObservers();
        assertTrue(observers.contains(office));
    }

    @Test
    public void testUnregisterObserver() {
        lot.unregisterObserver(office);
        List<ParkingObserver> observers = lot.getObservers();
        assertFalse(observers.contains(office));
    }

    @Test
    public void testNotifyObservers() {
        ParkingEvent event = new ParkingEvent(lot, permit, Instant.now());
        ParkingChargeStrategyFactory factory = new DiscountEventsStrategyFactory();
        lot.setParkingChargeStrategyFactory(factory);
        lot.notifyObservers(event);

        assertTrue(office.getCharges() != null);
    }
}
