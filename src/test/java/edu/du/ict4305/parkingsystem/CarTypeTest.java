/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

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
    CARTYPE carType = CARTYPE.COMPACT;
    assertEquals("COMPACT", carType.name());
    assertEquals(CARTYPE.COMPACT, carType);
  }

  @Test
  public void testCarType_suv() {
    CARTYPE carType = CARTYPE.SUV;
    assertEquals("SUV", carType.name());
    assertEquals(CARTYPE.SUV, carType);
  }

  @Test
  public void testCarType_valueOf() {
    assertEquals(CARTYPE.COMPACT, CARTYPE.valueOf("COMPACT"));
    assertEquals(CARTYPE.SUV, CARTYPE.valueOf("SUV"));
  }

  @Test
  public void testCarType_valueOf_invalid() {
    assertThrows(IllegalArgumentException.class, () -> {
      CARTYPE.valueOf("invalidType");
    });
  }

}
