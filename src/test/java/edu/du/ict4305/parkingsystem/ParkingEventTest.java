/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.Instant;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author cblsa
 */
public class ParkingEventTest {

    private ParkingLot lot;
    private Permit permit;

    @BeforeEach
    public void setUpClass() {
        Address address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        lot = new ParkingLot("Sample Lot", address, 100, ParkingLotType.ENTRY);
        Customer customer = new Customer.Builder("Jane", "Doe").address(address).phoneNumber("123-456-7890").build();
        Car car = new Car("123", CarType.SUV, customer);
        permit = new Permit("123", car);
    }

    @Test
    public void testParkingEventWithTimeOut() {

        Instant timeIn = Instant.now();
        Instant timeOut = timeIn.plusSeconds(3600); // 1 hour later

        ParkingEvent event = new ParkingEvent(lot, permit, timeIn, timeOut);

        assertEquals(lot, event.getParkingLot());
        assertEquals(timeIn, event.getTimeIn());
        assertEquals(timeOut, event.getTimeOut());
        assertEquals(permit, event.getPermit());
    }

    @Test
    public void testParkingEventWithoutTimeOut() {

        Instant timeIn = Instant.now();

        ParkingEvent event = new ParkingEvent(lot, permit, timeIn);

        assertEquals(lot, event.getParkingLot());
        assertEquals(timeIn, event.getTimeIn());
        assertNull(event.getTimeOut());
        assertEquals(permit, event.getPermit());
    }
}
