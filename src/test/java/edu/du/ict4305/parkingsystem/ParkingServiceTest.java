/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author candace.saindon
 */
public class ParkingServiceTest {

    private Address address;
    private ParkingService parkingService;

    @BeforeEach
    public void setUp() {
        address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        ParkingOffice office = new ParkingOffice("Test Office", address, new ArrayList<Customer>(), new ArrayList<Car>(), new ArrayList<ParkingLot>());
        parkingService = new ParkingService(office);
    }

    @Test
    public void testPerformCommandRegisterCar() {
        String commandName = "registerCar";
        String[] parameters = {
            "first name=John",
            "last name=Doe",
            "phone number=123-456-7890",
            "car type=compact",
            "street address 1=111 Main St",
            "street address 2=Unit 2",
            "city=Brighton",
            "state=CO",
            "zipcode=12345"
        };

        String result = parkingService.performCommand(commandName, parameters);
        assertTrue(result.contains("Car registered successfully"));
    }

    @Test
    public void testPerformCommandRegisterCustomer() {
        String commandName = "registerCustomer";
        String[] parameters = {
            "first name=Candace",
            "last name=Doe",
            "phone number=123-456-7890",
            "street address 1=111 Main St",
            "street address 2=Unit 2",
            "city=Brighton",
            "state=CO",
            "zipcode=12345"
        };

        String result = parkingService.performCommand(commandName, parameters);
        assertTrue(result.contains("Customer registered successfully"));
    }

    @Test
    public void testPerformCommandInvalidCommand() {
        String commandName = "invalidCommand";
        String[] parameters = {
            "param1=value1",
            "param2=value2"
        };

        String result = parkingService.performCommand(commandName, parameters);
        assertTrue(result.contains("Command not found"));

    }

    @Test
    public void testPerformCommandMalformedParameter() {
        String commandName = "registerCar";
        String[] parameters = {
            "first name=Candace",
            "last name=Doe",
            "phone number=123-456-7890",
            "car type=compact",
            "invalidParam"
        };

        assertThrows(IllegalArgumentException.class, () -> parkingService.performCommand(commandName, parameters));
    }

}
