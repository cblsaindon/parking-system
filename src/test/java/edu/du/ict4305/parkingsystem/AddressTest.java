/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author candace.saindon
 */
public class AddressTest {

  //private Driver owner;
  //private Customer customer;
  private Address address;
  //private Car car;

  @BeforeEach
  public void setUp() {
    //owner = new Driver("John", "Doe", "123");
    address = new Address("1 Main St", "", "Denver", "CO", "80202");
    //customer = new Customer("Jane", "Doe", "123", "1", address, "303-555-5555");
    //String license = "123";
    //Permit permit = new Permit("123");
    //CarType type = CarType.SUV;
    //car = new Car(license, type, owner, customer, permit);
  }

  @Test
  public void testGetAddressInfo() {
    System.out.println("getAddressInfo");

    String streetAddress1 = "1 Main St";
    String streetAddress2 = "";
    String city = "Denver";
    String state = "CO";
    String zipCode = "80202";
    
    String expResult = "" + streetAddress1 + " " + streetAddress2 + " "
        + city + " " + zipCode + " " + state;
    String result = address.getAddressInfo();
    assertEquals(expResult, result);

  }

  /**
   * Test of toString method, of class Address.
   */
  @Test
  public void testToString() {
    System.out.println("toString");

    String streetAddress1 = "1 Main St";
    String streetAddress2 = "";
    String city = "Denver";
    String state = "CO";
    String zipCode = "80202";

    String expResult = "edu.du.ict4305.parkingsystem.Address" + "[streetAddress1=" + streetAddress1 + ", streetAddress2=" + streetAddress2
        + ",city=" + city + ", zipCode=" + zipCode + ", state=" + state + "]";
    String result = address.toString();

    assertEquals(expResult, result);

  }

  public void testNullStreetAddress1() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address(null, "123 Main St", "Anytown", "NY", "80210")
    );
  }

  public void testEmptyStreetAddress1() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address("", "123 Main St", "Anytown", "NY", "80210")
    );
  }

  public void testNullStreetAddress2() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address("123 Main St", null, "Anytown", "NY", "80210")
    );
  }

  public void testEmptyStreetAddress2() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address("123 Main St", "", "Anytown", "NY", "80210")
    );
  }

  public void testNullCity() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address("123 Main St", "123 Main St", null, "11111", "80210")
    );
  }

  public void testEmptyCity() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address("123 Main St", "123 Main St", "", "NY", "80210")
    );
  }

  public void testNullZipCode() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address("123 Main St", "123 Main St", "Anytown", "NY", null)
    );
  }

  public void testEmptyZipCode() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address("123 Main St", "123 Main St", "Anytown", "NY", "")
    );
  }

  public void testInvalidZipCode() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address("123 Main St", "123 Main St", "Anytown", "NY", "123")
    );
  }

  public void testNullState() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address("123 Main St", "123 Main St", "Anytown", "NY", null)
    );
  }

  public void testEmptyState() {
    assertThrows(IllegalArgumentException.class,
        () -> new Address("123 Main St", "123 Main St", "Anytown", "NY", "")
    );
  }

}
