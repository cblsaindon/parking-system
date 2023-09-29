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
public class MoneyTest {

  @Test
  public void testGetDollars() {
    System.out.println("getDollars");
    Money instance = new Money(10.0);
    double expResult = 10.0;
    double result = instance.getDollars();
    assertEquals(expResult, result, 0.0);
  }

  @Test
  public void testToString() {
    System.out.println("toString");
    long cents = 1000;
    Money instance = new Money(10);
    String expResult = "edu.du.ict4305.parkingsystem.Money[cents=" + cents + "]";

    String result = instance.toString();
    assertEquals(expResult, result);
  }

}
