/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.Instant;

import org.junit.jupiter.api.*;

//import org.mockito.ArgumentMatchers;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author candace.saindon
 */
public class CarTest {

  private Customer customer;
  private Address address;
  private Car car;

  @BeforeEach
  public void setUp() {
    address = new Address("1 Main St", "", "Denver", "CO", "80202");
    customer = new Customer("Jane", "Doe", address, "303-555-5555");
    String license = "123";
    CarType type = CarType.SUV;
    car = new Car(license, type, customer);
  }

  @Test
  public void testSetLotEnter() {
    System.out.println("setLotEnter");
    Instant lotEnter = Instant.now();
    car.setLotEnter(lotEnter);

  }

  @Test
  public void testGetLotEnterNull() {
    System.out.println("getLotEnter");
    Instant expResult = null;
    Instant result = car.getLotEnter();
    assertEquals(expResult, result);

  }

  @Test
  public void testGetHoursInLot() {
    System.out.println("getHoursInLot");
    Instant today = Instant.now();
    car.setLotEnter(today);
    int expResult = 0;
    int result = car.getHoursInLot(today);
    assertEquals(expResult, result);

  }

  @Test
  public void testGetCarType() {
    System.out.println("getCarType");
    CarType expResult = CarType.SUV;
    CarType result = car.getCarType();
    assertEquals(expResult, result);

  }

  @Test
  public void testSetLicensePlate() {
    System.out.println("setLicense");
    String license = "123";
    car.setLicensePlate(license);

  }

  @Test
  public void testSetCarType() {
    System.out.println("setCarType");
    CarType carType = CarType.COMPACT;
    car.setCarType(carType);

  }

  @Test
  public void testNullLicense() {
    assertThrows(IllegalArgumentException.class,
        () -> new Car(null, CarType.SUV, new Customer("Jane",
            "Doe", address, "303-555-5555"))
    );
  }

  @Test
  public void testEmptyLicense() {
    assertThrows(IllegalArgumentException.class,
        () -> new Car("", CarType.SUV, new Customer("Jane", "Doe", address, "303-555-5555"))
    );
  }


  @Test
  public void testNullCarType() {
    assertThrows(IllegalArgumentException.class,
        () -> new Car("123ABC", null, new Customer("Jane", "Doe", address, "303-555-5555"))
    );
  }


  @Test
  public void testNullLotEnter() {
    Car car = new Car("123ABC", CarType.SUV, new Customer("Jane",
        "Doe", address, "303-555-5555"));
    assertThrows(IllegalArgumentException.class,
        () -> car.getHoursInLot(null)
    );
  }

  /**
   * Test of toString method, of class Car.
   */
  @Test
  public void testToString() {
    System.out.println("toString");

    String license = "123";
    CarType type = CarType.SUV;

    String expResult = "edu.du.ict4305.parkingsystem.Car" + "[licensePlate=" + license + ",type=" + type + ",customer=" + customer + "]";
    String result = car.toString();

    assertEquals(expResult, result);
    
  }

}
