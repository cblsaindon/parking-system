/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;

/**
 *
 * @author candace.saindon
 */
public class ParkingChargeTest {
  private PermitManager permitManager;
  /**
   * Test of toString method, of class ParkingCharge.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    permitManager = new PermitManager();
    Address address = new Address("1 Main St", "", "Denver", "CO", "80202");
    Customer customer = new Customer("Jane", "Doe", address, "303-555-5555");
    String license = "123";
    CarType type = CarType.SUV;
    Car car = new Car(license, type, customer);
    Permit permit = permitManager.register(car);
        
    String lotId = "L1";
    Instant incurred = Instant.now();
    Money amount = new Money(10.0);

    ParkingCharge<Money> parkingCharge = new ParkingCharge(permit, lotId, incurred, amount);

    String expResult = "edu.du.ict4305.parkingsystem.ParkingCharge[permit=" + permit
        + ", lotId=" + lotId + ",incurred=" + incurred + ", amount=" + amount + "]";

    String result = parkingCharge.toString();
    assertEquals(expResult, result);

  }

}
