/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author candace.saindon
 */
public class RegisterCustomerCommandTest {
    private ParkingOffice office;
    private RegisterCustomerCommand command;    
    
    
    @Before
    public void setUp() {
                // Create a mock ParkingOffice or use a testing framework for dependency injection
        //office = new MockParkingOffice(); // You can implement a mock ParkingOffice for testing
        command = new RegisterCustomerCommand(office);
    }

    /**
     * Test of getCommandName method, of class RegisterCustomerCommand.
     */
    @Test
    public void testGetCommandName() {
        System.out.println("getCommandName");
        RegisterCustomerCommand instance = null;
        String expResult = "";
        String result = instance.getCommandName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDisplayName method, of class RegisterCustomerCommand.
     */
    @Test
    public void testGetDisplayName() {
        System.out.println("getDisplayName");
        RegisterCustomerCommand instance = null;
        String expResult = "";
        String result = instance.getDisplayName();
        assertEquals(expResult, result);
    }

    /**
     * Test of execute method, of class RegisterCustomerCommand.
     */

    
    
    @Test
    public void testValidCustomerRegistration() {
      //Testing the execution
      Properties params = new Properties();
      params.setProperty("first name", "Canadce");
      params.setProperty("last name", "Doe");
      params.setProperty("phone number", "123-456-7890");
      params.setProperty("street address 1", "123 Fairfax St");
      params.setProperty("street address 2", "");
      params.setProperty("city", "Brighton");
      params.setProperty("state", "CO");
      params.setProperty("zipcode", "12345");

      String result = command.execute(params);
      assertTrue(result.contains("Customer registered successfully"));
    }

    @Test
    public void testMissingRequiredParameters() {
        Properties params = new Properties();
        // Missing "last name" and "phone number"
        params.setProperty("first name", "Candace");
        params.setProperty("street address 1", "123 Main St");
        params.setProperty("city", "City");
        params.setProperty("state", "State");
        params.setProperty("zipcode", "12345");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            command.execute(params);
        });
        assertTrue(exception.getMessage().contains("Parameter 'last name' is missing"));
        assertTrue(exception.getMessage().contains("Parameter 'phone number' is missing"));
    }

    @Test
    public void testEmptyRequiredParameters() {
        Properties params = new Properties();
        // Empty "first name" and "zipcode"
        params.setProperty("first name", "");
        params.setProperty("last name", "Doe");
        params.setProperty("phone number", "123-456-7890");
        params.setProperty("street address 1", "123 Main St");
        params.setProperty("city", "City");
        params.setProperty("state", "State");
        params.setProperty("zipcode", "");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            command.execute(params);
        });
        assertTrue(exception.getMessage().contains("Parameter 'first name' is empty or null"));
        assertTrue(exception.getMessage().contains("Parameter 'zipcode' is empty or null"));
    }
    
}
