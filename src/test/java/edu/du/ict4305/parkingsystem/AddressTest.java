/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author candace.saindon
 */
public class AddressTest {

    private Address address;

    @BeforeEach
    public void setUp() {
        address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
    }

    @Test
    public void testGetAddressInfo() {
        System.out.println("getAddressInfo");

        String streetAddress1 = "1 Main St";
        String streetAddress2 = "";
        String city = "Denver";
        String state = "CO";
        String zipCode = "80202";

        String expResult = "" + streetAddress1 + " " + streetAddress2 + " "
                + city + " " + zipCode + " " + state;
        String result = address.getAddressInfo();
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class Address.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String streetAddress1 = "1 Main St";
        String streetAddress2 = "";
        String city = "Denver";
        String state = "CO";
        String zipCode = "80202";

        String expResult = "edu.du.ict4305.parkingsystem.Address" + "[streetAddress1=" + streetAddress1 + ", streetAddress2=" + streetAddress2
                + ",city=" + city + ", zipCode=" + zipCode + ", state=" + state + "]";
        String result = address.toString();

        assertEquals(expResult, result);

    }



}
