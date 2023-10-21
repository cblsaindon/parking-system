/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;

/**
 *
 * @author candace.saindon
 */
public class ParkingTransactionTest {

    private PermitManager permitManager;
    private RealParkingLot parkingLot;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    /**
     * Test of toString method, of class ParkingTransaction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        permitManager = new PermitManager();
        firstName = "Jane";
        lastName = "Doe";
        phoneNumber = "303-555-5555";
        Address address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();
        String license = "123";

        CarType type = CarType.SUV;
        Car car = new Car(license, type, customer);
        Permit permit = permitManager.register(car);
        parkingLot = new RealParkingLot("Sample Lot", address, 100, ParkingLotType.ENTRY);
        Instant incurred = Instant.now();
        Money amount = Money.of(10.0);

        ParkingTransaction parkingCharge = new ParkingTransaction.Builder(incurred, permit, parkingLot, amount).build();

        String expResult = "edu.du.ict4305.parkingsystem.ParkingTransaction[permit=" + permit
                + ", parkinglot=" + parkingLot + ",incurred=" + incurred + ", amount=" + amount + "]";

        String result = parkingCharge.toString();
        assertEquals(expResult, result);

    }

}
