/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author candace.saindon
 */
public class ParkingOfficeTest {

    private PermitManager permitManager;
    private ParkingOffice parkingOffice;
    private List<Customer> customers;
    private List<Car> cars;
    private List<RealParkingLot> lots;
    private List<ParkingTransaction> charges;
    Address address;
    Customer customer;
    CarType type;
    String license;
    Car car;
    Permit permit;
    RealParkingLot parkingLot;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @BeforeEach
    void setup() {
        customers = new ArrayList<>();
        cars = new ArrayList<>();
        lots = new ArrayList<>();
        charges = new ArrayList<>();
        permitManager = new PermitManager();
        parkingOffice = new ParkingOffice("Parking Office", address, customers, cars, lots);
        address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        firstName = "Jane";
        lastName = "Doe";
        phoneNumber = "303-555-5555";
        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();
        license = "123";
        type = CarType.SUV;
        car = new Car(license, type, customer);
        permit = permitManager.register(car);
        parkingLot = new RealParkingLot("Sample Lot", address, 100, ParkingLotType.ENTRY);
    }

    @Test
    void testRegisterCustomer() {
        String firstName = "John";
        String lastName = "Doe";
        String license = "ABC123";
        String phone = "555-555-5555";

        Customer customer = testRegisterCustomerWithOffice();

        assertNotNull(customer);
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(phone, customer.getPhone());
    }

    @Test
    void testRegisterCar() {
        Customer customer = testRegisterCustomerWithOffice();

        String carLicense = "ABC123";
        CarType carType = CarType.COMPACT;
        Car car = parkingOffice.register(customer, carLicense, carType);

        assertNotNull(car);
        assertEquals(carLicense, car.getLicensePlate());
        assertEquals(carType, car.getCarType());
    }

    @Test
    public void testGetCustomerByName() {
        Customer customer = testRegisterCustomerWithOffice();

        ParkingOffice instance = new ParkingOffice("Parking Office 1",
                address,
                null, null, null);

        Customer expResult = instance.getCustomerByName("John", "Doe");
        Customer result = instance.getCustomerByName("John", "Doe");
        assertEquals(expResult, result);
    }

    @Test
    void testGetCustomerByPhone() {
        Customer customer = testRegisterCustomerWithOffice();

        Customer result = parkingOffice.getCustomerByPhone("555-555-5555");
        //assertNotNull(result);
        assertEquals(customer, result);
    }

    /**
     * Test of register method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testRegister_9args() {
        System.out.println("register");
        String firstName = "";
        String lastName = "";
        String license = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String zipCode = "";
        String phone = "";
        ParkingOffice instance = null;
        Customer expResult = null;
        Customer result = instance.register(firstName, lastName, address1, address2, city,
                state, zipCode, phone);
        assertEquals(expResult, result);
    }

    /**
     * Test of getParkingOfficeName method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testGetParkingOfficeName() {
        System.out.println("getParkingOfficeName");
        ParkingOffice instance = null;
        String expResult = "";
        String result = instance.getParkingOfficeName();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testRegister_4args() {
        System.out.println("register");
        Customer customer = null;
        String license = "";
        CarType carType = null;
        ParkingOffice instance = null;
        Car expResult = null;
        Car result = instance.register(customer, license, carType);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCharges method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testGetCharges() {
        System.out.println("getCharges");
        ParkingOffice instance = null;
        List<ParkingTransaction> expResult = null;
        List<ParkingTransaction> result = instance.getCharges();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCars method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testGetCars() {
        System.out.println("getCars");
        ParkingOffice instance = null;
        List<Car> expResult = null;
        List<Car> result = instance.getCars();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPermitIds method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testGetPermitIds() {
        System.out.println("getPermitIds");
        ParkingOffice instance = null;
        List<String> expResult = null;
        List<String> result = instance.getPermitIds();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPermitIdsByCustomer method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testGetPermitIdsByCustomer() {
        System.out.println("getPermitIdsByCustomer");
        Customer customer = null;
        ParkingOffice instance = null;
        List<String> expResult = null;
        List<String> result = instance.getPermitIdsByCustomer(customer);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCustomerIds method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testGetCustomerIds() {
        System.out.println("getCustomerIds");
        ParkingOffice instance = null;
        List<String> expResult = null;
        List<String> result = instance.getCustomerIds();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLotTypeByLotId method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testGetLotTypeByLotId() {
        System.out.println("getLotTypeByLotId");
        String lotId = "";
        ParkingOffice instance = null;
        RealParkingLot expResult = null;
        RealParkingLot result = instance.getLotTypeByLotId(lotId);
        assertEquals(expResult, result);
    }

    /**
     * Test of park method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testPark() {
        System.out.println("park");
        ParkingEvent event = new ParkingEvent(parkingLot, permit, Instant.now(), Instant.now());
        Instant date = null;
        String lotId = "";
        ParkingOffice instance = null;
        ParkingTransaction expResult = null;
        ParkingTransaction result = instance.park(event);

        assertEquals(expResult, result);
    }

    /**
     * Test of getParkingCharges method, of class ParkingOffice.
     */
    @org.junit.Test
    public void testGetParkingCharges() {
        System.out.println("getParkingCharges");
        Car car = null;
        ParkingOffice instance = null;
        Money expResult = null;
        Money result = instance.getParkingCharges(car);
        assertEquals(expResult, result);
    }

    @Test
    private Customer testRegisterCustomerWithOffice() {
        String firstName = "John";
        String lastName = "Doe";
        String address1 = "123 Main St";
        String address2 = "Apt 1";
        String city = "Denver";
        String zipcode = "80219";
        String state = "CO";
        String phone = "555-555-5555";

        Customer customer = parkingOffice.register(firstName, lastName, address1, address2,
                city, state, zipcode, phone);

        return customer;
    }

    @Test
    void testAddCharge() {
        Money charge = Money.of(5);
        ParkingTransaction parkingTransaction = new ParkingTransaction.Builder(Instant.now(), permit, parkingLot, charge).build();

        // Ensure the charges list is initially empty
        assertEquals(0, parkingOffice.getCharges().size());

        // Add a parking charge
        parkingOffice.addCharge(parkingTransaction);

        // Check if the charge was added
        List<ParkingTransaction> charges = parkingOffice.getCharges();
        assertEquals(1, charges.size());
        assertTrue(charges.contains(parkingTransaction));
    }

}
