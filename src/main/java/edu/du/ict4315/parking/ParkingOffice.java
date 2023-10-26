/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking;

import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.observers.ParkingObserver;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

/**
 * Class representing a Parking Office, responsible for handling parking
 * transactions, registering customers and cars, and adding parking charges.
 *
 * @author candace.saindon
 */
public class ParkingOffice implements ParkingObserver {

    private String name; // name of the Parking Office
    private Address address; // address of the Parking Office
    private List<Car> cars; // list of registered cars
    private List<RealParkingLot> lots; // list of parking lots
    private List<ParkingTransaction> charges; // list of parking charges
    private TransactionManager transactionManager;
    private PermitManager permitManager;

    private Map<String, Customer> customerToIdMap; // map of customers keyed by their ID keytovalue
    private Map<String, Customer> customerToPhoneMap; // map of customers keyed by their phone number
    private Map<String, RealParkingLot> parkingLotToType; // map of parking lots keyed by their lot types
    private Map<Customer, String> permitToCustomerMap; // map of customers keyed by their phone number

    public ParkingOffice(String name, Address address, List<Customer> customers,
            List<Car> cars, List<RealParkingLot> lots) {
        this.name = name;
        this.address = address;
        this.cars = cars;
        this.lots = lots;

        customerToIdMap = new HashMap<>();
        customerToPhoneMap = new HashMap<>();
        permitToCustomerMap = new HashMap<>();
        charges = new ArrayList<>();

        // Initialize TransactionManager and PermitManager
        this.transactionManager = new TransactionManager();
        this.permitManager = new PermitManager();
    }

    // Method to register a customer with the Parking Office.
    public Customer register(String firstName, String lastName, String address1,
            String address2, String city,
            String state, String zipCode, String phone) {
        Address address = new Address.Builder(address1, city, state, zipCode).streetAddress2(address2).build();
        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phone).build();

        try {
            customerToIdMap.put(customer.getId(), customer);
            customerToPhoneMap.put(customer.getPhone(), customer);

        } catch (Exception e) {
            System.err.println("Error registering customer: " + e.getMessage());
            return null;
        }
        return customer;
    }

    // Method to register a car with the Parking Office.
    public Car register(Customer customer, String license, CarType carType) {

        Car car = new Car(license, carType, customer);

        Permit permit = permitManager.register(car);
        String permitId = permit.getId();

        permitToCustomerMap.put(customer, permitId);
        cars.add(car);

        return car;
    }

    //Register parking lot and use register observer
    public void addParkingLot(RealParkingLot parkingLot) throws Exception {
        if (!lots.contains(parkingLot)) {
            parkingLot.registerObserver(this);
            lots.add(parkingLot);
        } else {
            throw new Exception("Parking lot already added");
        }
    }

    public List<RealParkingLot> getListOfParkingLot() {
        List<RealParkingLot> copy = new ArrayList<>(lots);
        return copy;
    }

    public String getParkingOfficeName() {
        return name;
    }

    public List<ParkingTransaction> getCharges() {
        return charges;
    }
    
    public void addCharge(ParkingTransaction charge) {
        charges.add(charge);
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<String> getPermitIds() {
        //Returns all permit ids for the parking office
        List<String> permitIds = new ArrayList<>();
        List<Car> carList = getCars();
        String permitId;

        for (Car car : carList) {
            //get all permit ids associated with those cars
            Customer customer = car.getCustomer();
            permitId = permitToCustomerMap.get(customer);
            permitIds.add(permitId);
        }

        return permitIds;
    }

    public List<String> getPermitIdsByCustomer(Customer customer) {
        //Returns a collection of permit ids for a specific customer
        List<String> permitIds = new ArrayList<>();

        String permitId = permitToCustomerMap.get(customer);
        permitIds.add(permitId);
        return permitIds;
    }

    public List<String> getCustomerIds() {
        //Returns a collection of customer ids
        Set<String> keySet = customerToIdMap.keySet();
        List<String> customerIdList = new ArrayList<>(keySet);
        return customerIdList;
    }

    // Retrieves a customer from the list of customers based on the customer's first and last name.
    public Customer getCustomerByName(String firstName, String lastName) {
        for (Map.Entry<String, Customer> entry : customerToIdMap.entrySet()) {
            Customer c = entry.getValue();
            if (firstName.equals(c.getFirstName()) && lastName.equals(c.getLastName())) {
                return c;
            }
        }
        return null;
    }

    // Retrieves a customer from the list of customers based on the customer's phone number.
    public Customer getCustomerByPhone(String phone) {
        return customerToPhoneMap.get(phone);
    }

    // Retrieves a customer from the list of customers based on the customer's driver's license
    public RealParkingLot getLotTypeByLotId(String lotId) {
        return parkingLotToType.get(lotId);
    }

    public ParkingTransaction park(ParkingEvent event) {
        return transactionManager.park(event);
    }

    public Money getParkingCharges(Car car) {
        return transactionManager.getParkingCharges(car);
    }

    public Money getParkingCharges(Permit permit) {
        return transactionManager.getParkingCharges(permit);
    }

    public Permit getPermit(String permitId) {
        return permitManager.findPermit(permitId);
    }

    @Override
    public void update(ParkingEvent event) {
        ParkingTransaction parkingTransaction = park(event);
        addCharge(parkingTransaction);
    }

}
