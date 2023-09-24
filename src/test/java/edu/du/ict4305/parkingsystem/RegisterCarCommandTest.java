/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.util.ArrayList;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author candace.saindon
 */
public class RegisterCarCommandTest {
    private ParkingOffice office;
    private Address address;
    RegisterCarCommand command;
    
    @Before
    public void setUp() {
      address = new Address("111 Main St", "", "Brighton", "CO", "12345");
      office = new ParkingOffice("OfficeName", address, new ArrayList<Customer>(), new ArrayList<Car>(), new ArrayList<ParkingLot>());
      command = new RegisterCarCommand(office);
    
    }
    
    /**
     * Test of getCommandName method, of class RegisterCarCommand.
     */
    @Test
    public void testGetCommandName() {
        System.out.println("getCommandName");
        RegisterCarCommand instance = null;
        String expResult = "";
        String result = instance.getCommandName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDisplayName method, of class RegisterCarCommand.
     */
    @Test
    public void testGetDisplayName() {
        System.out.println("getDisplayName");
        RegisterCarCommand instance = null;
        String expResult = "";
        String result = instance.getDisplayName();
        assertEquals(expResult, result);
    }

 @Test
    public void testValidCarRegistration() {
  Properties params = new Properties();
   
      params.setProperty("first name", "John");
      params.setProperty("last name", "Doe");
      params.setProperty("phone number", "123-456-7890");
      params.setProperty("car type", "compact");
      params.setProperty("street address 1", "111 Main St");
      params.setProperty("street address 2", "");
      params.setProperty("city", "Brighton");
      params.setProperty("state", "CO");
      params.setProperty("zipcode", "12345");

      String result = command.execute(params);

      assertTrue(result.contains("Car registered successfully"));
    }

    @Test
    public void testMissingCarType() {
      Properties params = new Properties();
      params.setProperty("first name", "John");
      params.setProperty("last name", "Doe");
      params.setProperty("phone number", "123-456-7890");
      params.setProperty("street address 1", "111 Main St");
      params.setProperty("street address 2", "");
      params.setProperty("city", "Brighton");
      params.setProperty("state", "CO");
      params.setProperty("zipcode", "12345");

      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
          command.execute(params);
      });

      assertTrue(exception.getMessage().contains("Parameter 'car type' is missing or empty"));
    }
    
}
