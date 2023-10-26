/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4315.parking;

import edu.du.ict4315.parking.PermitIdGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author candace.saindon
 */
public class PermitIdGeneratorTest {

  /**
   * Test of generateId method, of class PermitIdGenerator.
   */
  @Test
  public void testGenerateId() {
    String firstId = PermitIdGenerator.generateId();
    String secondId = PermitIdGenerator.generateId();

    assertNotEquals(firstId, secondId);
  }

}
