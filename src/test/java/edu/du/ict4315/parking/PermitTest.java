/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4315.parking;

import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.CarType;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author candace.saindon
 */
public class PermitTest {

    private Car car;
    private LocalDateTime expiration;
    private Address address;
    private Customer customer;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @BeforeEach
    public void setUp() {
        address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        firstName = "Jane";
        lastName = "Doe";
        phoneNumber = "303-555-5555";
        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();
        car = new Car("ABC123", CarType.COMPACT, customer);
        expiration = LocalDateTime.now().plus(1, ChronoUnit.DAYS); // Expire in 1 day
    }

    @Test
    public void testConstructorAndGetters() {
        Permit permit = new Permit("P123", car, expiration);

        assertEquals("P123", permit.getId());
        assertEquals(car, permit.getCar());
        assertFalse(permit.isExpired());
    }

    @Test
    public void testExpiredPermit() {
        LocalDateTime expiredDateTime = LocalDateTime.now().minus(1, ChronoUnit.DAYS); // Expired 1 day ago
        Permit expiredPermit = new Permit("P456", car, expiredDateTime);
        assertTrue(expiredPermit.isExpired());
    }

    @Test
    public void testSetCar() {
        Permit permit = new Permit("P789", car, expiration);

        Car newCar = new Car("XYZ789", CarType.SUV, customer);
        permit.setCar(newCar);

        assertEquals(newCar, permit.getCar());
    }

    @Test
    public void testToString() {
        Permit permit = new Permit("P123", car, expiration);
        String expectedToString = "edu.du.ict4305.parkingsystem.Permit[id=P123, permitExpiration=" + expiration + "]";
        assertEquals(expectedToString, permit.toString());
    }

}
