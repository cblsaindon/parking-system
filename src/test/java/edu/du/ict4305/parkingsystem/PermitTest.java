/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

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
    
    @BeforeEach
    public void setUp() {
      address = new Address("1 Main St", "", "Denver", "CO", "80202");
      customer = new Customer("Jane", "Doe", address, "303-555-5555");
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

      Car newCar = new Car("XYZ789", CarType.SUV, new Customer("Jane", "Smith", new Address("456 Avenue", "", "Town", "State", "67890"), "987-654-3210"));
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
