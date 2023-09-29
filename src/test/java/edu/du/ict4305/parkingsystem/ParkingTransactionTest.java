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
    private ParkingLot parkingLot;

    /**
     * Test of toString method, of class ParkingTransaction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        permitManager = new PermitManager();
        Address address = new Address("1 Main St", "", "Denver", "CO", "80202");
        Customer customer = new Customer("Jane", "Doe", address, "303-555-5555");
        String license = "123";
        CARTYPE type = CARTYPE.SUV;
        Car car = new Car(license, type, customer);
        Permit permit = permitManager.register(car);
        parkingLot = new ParkingLot("Sample Lot", address, 100, PARKINGLOTTYPE.ENTRY);
        Instant incurred = Instant.now();
        Money amount = new Money(10.0);

        ParkingTransaction<Money> parkingCharge = new ParkingTransaction(incurred, permit, parkingLot, amount);

        String expResult = "edu.du.ict4305.parkingsystem.ParkingTransaction[permit=" + permit
                + ", parkinglot=" + parkingLot + ",incurred=" + incurred + ", amount=" + amount + "]";

        String result = parkingCharge.toString();
        assertEquals(expResult, result);

    }

}
