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
public class ParkingLotTypeTest {

  /**
   * Test if the ENTRY constant exists in the PARKINGLOTTYPE enum
   */
  @Test
  public void testEntryExists() {
    PARKINGLOTTYPE entry = PARKINGLOTTYPE.ENTRY;
    assertNotNull(entry);
  }

  /**
   * Test if the ENTRYEXIT constant exists in the PARKINGLOTTYPE enum
   */
  @Test
  public void testEntryExitExists() {
    PARKINGLOTTYPE entryExit = PARKINGLOTTYPE.ENTRYEXIT;
    assertNotNull(entryExit);
  }

}
