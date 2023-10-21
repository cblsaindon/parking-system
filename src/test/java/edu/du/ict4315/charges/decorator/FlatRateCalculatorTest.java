/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.charges.decorator;

import edu.du.ict4305.parkingsystem.Address;
import edu.du.ict4305.parkingsystem.Car;
import edu.du.ict4305.parkingsystem.CarType;
import edu.du.ict4305.parkingsystem.Customer;
import edu.du.ict4305.parkingsystem.Money;
import edu.du.ict4305.parkingsystem.ParkingEvent;
import edu.du.ict4305.parkingsystem.ParkingLotType;
import edu.du.ict4305.parkingsystem.Permit;
import edu.du.ict4305.parkingsystem.PermitManager;
import edu.du.ict4305.parkingsystem.RealParkingLot;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author cblsa
 */
public class FlatRateCalculatorTest {

    private PermitManager permitManager;
    private FlatRateCalculator flatRateCalculator;
    private Permit permit;
    private RealParkingLot parkingLot;

    @BeforeEach
    public void setUp() {
        flatRateCalculator = new FlatRateCalculator();
        Address address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        Customer customer = new Customer.Builder("Jane", "Doe").address(address).phoneNumber("123-456-7890").build();
        String license = "123";
        CarType type = CarType.SUV;
        Car car = new Car(license, type, customer);
        permitManager = new PermitManager();
        permit = permitManager.register(car);
        parkingLot = new RealParkingLot("Sample Lot", address, 100, ParkingLotType.ENTRY);
    }

    @Test
    public void testGetParkingCharge() {
        // Create test data
        Instant timeIn = Instant.now();
        Instant timeOut = timeIn.plusSeconds(3600); // 1 hour later

        ParkingEvent event = new ParkingEvent(parkingLot, permit, timeIn, timeOut);
        // Calculate the expected charge
        Money expectedCharge = Money.of(15);

        // Calculate the actual charge using the FlatRateCalculator
        Money actualCharge = flatRateCalculator.getParkingCharge(event);

        // Assert that the actual charge matches the expected charge
        assertEquals(expectedCharge.getDollars(), actualCharge.getDollars(), "Parking charge calculation is incorrect");
    }
    
    

}
