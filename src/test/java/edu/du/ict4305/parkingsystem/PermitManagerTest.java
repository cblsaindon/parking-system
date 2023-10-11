/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *
 * @author Candace Saindon
 */
public class PermitManagerTest {

    private PermitManager permitManager;
    private Car car;
    private Address address;
    private Customer customer;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @BeforeEach
    public void setUp() {
        permitManager = new PermitManager();
        address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        firstName = "Jane";
        lastName = "Doe";
        phoneNumber = "303-555-5555";
        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();
        car = new Car("ABC123", CarType.COMPACT, customer);
    }

    @Test
    public void testRegisterPermit() {
        Permit permit = permitManager.register(car);

        assertNotNull(permit);
        assertEquals(car, permit.getCar());
        assertFalse(permit.isExpired());
    }

    @Test
    public void testFindPermit() {
        Permit permit = permitManager.register(car);

        String permitId = permit.getId();
        Permit foundPermit = permitManager.findPermit(permitId);

        assertNotNull(foundPermit);
        assertEquals(permitId, foundPermit.getId());
        assertEquals(car, foundPermit.getCar());
    }

    @Test
    public void testFindNonExistentPermit() {
        Permit foundPermit = permitManager.findPermit("NonExistentPermitId");

        assertNull(foundPermit);
    }
}
