/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

/**
 *
 * @author candace.saindon
 */
public class CustomerTest {

    @Test
    public void testGetId() {
        String firstName = "John";
        String lastName = "Doe";
        Address address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        String phoneNumber = "303-555-1212";

        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();

        //String expResult = "1";
        String result = customer.getId();

        assertThat(result, containsString("cust_"));
    }

    @Test
    public void testGetPhone() {
        String firstName = "John";
        String lastName = "Doe";
        Address address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        String phoneNumber = "6305551234";

        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();

        String expResult = "6305551234";
        String result = customer.getPhone();

        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Customer.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String firstName = "John";
        String lastName = "Doe";
        String id = "1";

        Address address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        String phoneNumber = "6305559545";

        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();

        String expResult = "edu.du.ict4305.parkingsystem.Customer" + "[firstName=" + firstName + ",lastName=" + lastName
                + ",address=" + address + ",phoneNumber=" + phoneNumber + "]";

        String result = customer.toString();
        assertEquals(expResult, result);

    }

}
