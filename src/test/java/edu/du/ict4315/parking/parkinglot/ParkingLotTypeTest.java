/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4315.parking.parkinglot;

import edu.du.ict4315.parking.parkinglot.ParkingLotType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author candace.saindon
 */
public class ParkingLotTypeTest {

  /**
   * Test if the ENTRY constant exists in the ParkingLotType enum
   */
  @Test
  public void testEntryExists() {
    ParkingLotType entry = ParkingLotType.ENTRY;
    assertNotNull(entry);
  }

  /**
   * Test if the ENTRYEXIT constant exists in the ParkingLotType enum
   */
  @Test
  public void testEntryExitExists() {
    ParkingLotType entryExit = ParkingLotType.ENTRYEXIT;
    assertNotNull(entryExit);
  }

}
