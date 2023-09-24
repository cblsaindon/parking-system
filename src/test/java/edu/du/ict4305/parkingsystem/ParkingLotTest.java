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
public class ParkingLotTest {

  private Customer customer;
  private Address address;
  private Car car;

  @BeforeEach
  public void setUpClass() {
    address = new Address("1 Main St", "", "Denver", "CO", "80202");
    customer = new Customer("Jane", "Doe", address, "303-555-5555");
    String license = "123";
    CarType type = CarType.SUV;
    car = new Car(license, type, customer);
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

    ParkingLot instance = new ParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
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

    ParkingLot instance = new ParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
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

    ParkingLot instance = new ParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
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

    ParkingLot instance = new ParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
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

    ParkingLot instance = new ParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
    int expResult = capacity;
    int result = instance.getEmptySlots();
    assertEquals(expResult, result);

  }

  @Test
  public void testEntryWithValidCar() {
    String lotId = "Lot1";
    //Address address = new Address("123 Main St", "Apt 2", "Denver", "80241", "Colorado");
    int capacity = 10;
    ParkingLotType lotType = ParkingLotType.ENTRY;
    ParkingLot lot = new ParkingLot(lotId, address, capacity, lotType);

    lot.entry(car);
    int expResult = 1;
    int result = lot.getCarsInLot();
    assertEquals(expResult, result);

  }

  @Test
  public void testEntryWithNullCar() {
    String lotId = "Lot1";
    //Address address = new Address("123 Main St", "Apt 2", "Denver", "80241", "Colorado");
    int capacity = 10;
    ParkingLotType lotType = ParkingLotType.ENTRY;
    ParkingLot lot = new ParkingLot(lotId, address, capacity, lotType);

    Car car = null;

    assertThrows(IllegalArgumentException.class, () -> lot.entry(car));
  }

  @Test
  public void testEntry_nullCar() {
    ParkingLot lot = new ParkingLot("A", address,
        20, ParkingLotType.ENTRY);
    assertThrows(IllegalArgumentException.class, () -> lot.entry(null));
  }


  @Test
  public void testEntry_ParkingLotFull() {
    ParkingLot lot = new ParkingLot("A", address,
        0, ParkingLotType.ENTRY);
    //Car car = new Car("123", CarType.SUV, owner);
    assertThrows(IllegalStateException.class, () -> lot.entry(car));
  }

  @Test
  public void testExit_NullCar() {
    ParkingLot parkingLot = new ParkingLot("LOT1", address, 100, ParkingLotType.ENTRY);
    assertThrows(IllegalArgumentException.class, () -> {
      parkingLot.exit(null);
    });
  }

  @Test
  public void testExit_lotEmpty() {
    int capacity = 0;
    ParkingLot lot = new ParkingLot("LOT1", address, capacity, ParkingLotType.ENTRYEXIT);

    //Car car = new Car("license", CarType.COMPACT, owner);
    //car.setPermit("permit123");
    assertThrows(IllegalStateException.class, () -> lot.exit(car));
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

    ParkingLot instance = new ParkingLot(lotId, address, capacity, ParkingLotType.ENTRY);
    String expResult = "edu.du.ict4305.parkingsystem.ParkingLot" + "[lotId=" + lotId + ", address=" + address
        + ",capacity=" + capacity + ", carsInLot=" + carsInLot + "]";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

}
