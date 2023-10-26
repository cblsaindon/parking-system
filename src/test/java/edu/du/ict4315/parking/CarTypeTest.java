/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4315.parking;

import edu.du.ict4315.parking.CarType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author candace.saindon
 */
public class CarTypeTest {

  public CarTypeTest() {
  }

  @Test
  public void testCarType_compact() {
    CarType carType = CarType.COMPACT;
    assertEquals("COMPACT", carType.name());
    assertEquals(CarType.COMPACT, carType);
  }

  @Test
  public void testCarType_suv() {
    CarType carType = CarType.SUV;
    assertEquals("SUV", carType.name());
    assertEquals(CarType.SUV, carType);
  }

  @Test
  public void testCarType_valueOf() {
    assertEquals(CarType.COMPACT, CarType.valueOf("COMPACT"));
    assertEquals(CarType.SUV, CarType.valueOf("SUV"));
  }

  @Test
  public void testCarType_valueOf_invalid() {
    assertThrows(IllegalArgumentException.class, () -> {
      CarType.valueOf("invalidType");
    });
  }

}
