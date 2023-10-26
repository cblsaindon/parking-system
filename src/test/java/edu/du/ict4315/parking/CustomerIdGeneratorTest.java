/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4315.parking;

import edu.du.ict4315.parking.CustomerIdGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author cblsa
 */
public class CustomerIdGeneratorTest {

  /**
   * Test of generateId method, of class CustomerIdGenerator.
   */
  @Test
  public void testGenerateId() {
    String firstId = CustomerIdGenerator.generateId();
    String secondId = CustomerIdGenerator.generateId();

    assertNotEquals(firstId, secondId);
  }

}
