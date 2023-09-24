/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

/**
 * Class representing a Parking Office, responsible for handling parking transactions, registering
 * customers and cars, and adding parking charges.
 *
 * @author candace.saindon
 */
public class ParkingOffice implements Office {

  private String name; // name of the Parking Office
  private Address address; // address of the Parking Office
  private List<Car> cars; // list of registered cars
  private List<ParkingLot> lots; // list of parking lots
  private List<ParkingCharge> charges; // list of parking charges
  private TransactionManager transactionManager;
  private PermitManager permitManager;

  private Map<String, Customer> customerToIdMap; // map of customers keyed by their ID keytovalue
  private Map<String, Customer> customerToPhoneMap; // map of customers keyed by their phone number
  private Map<String, ParkingLot> parkingLotToType; // map of parking lots keyed by their lot types
  private Map<Customer, String> permitToCustomerMap; // map of customers keyed by their phone number

  public ParkingOffice(String name, Address address, List<Customer> customers,
                       List<Car> cars, List<ParkingLot> lots) {
    this.name = name;
    this.address = address;
    this.cars = cars;
    this.lots = lots;

    customerToIdMap = new HashMap<>();
    customerToPhoneMap = new HashMap<>();
    permitToCustomerMap = new HashMap<>();

    // Initialize TransactionManager and PermitManager
    this.transactionManager = new TransactionManager();
    this.permitManager = new PermitManager();
  }

  // Method to register a customer with the Parking Office.
  @Override
  public Customer register(String firstName, String lastName, String address1,
                           String address2, String city,
                           String state, String zipCode, String phone) {
    Address address = new Address(address1, address2, city, state, zipCode);



    Customer customer = new Customer(firstName, lastName, address, phone);

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

  public String getParkingOfficeName() {
    return name;
  }
  
  public List<ParkingCharge> getCharges() {
    return charges;
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
  @Override
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
  @Override
  public Customer getCustomerByPhone(String phone) {
    return customerToPhoneMap.get(phone);
  }

  // Retrieves a customer from the list of customers based on the customer's driver's license
  public ParkingLot getLotTypeByLotId(String lotId) {
    return parkingLotToType.get(lotId);
  }

  public ParkingCharge park(Instant date, Car car, String lotId, Permit permit) {
    Money charge = new Money(0);

    //1. find out what lot type it is based on the lotid
    ParkingLot lot = getLotTypeByLotId(lotId);
    ParkingLotType lotType = lot.getLotType();
    //2. calculate the charge
    if (lotType == ParkingLotType.ENTRY) {
      charge = calculateOneTimeCharge(car);
    }
    //3. do the charge stuff
    return transactionManager.park(date, permit, lotId, charge);
  }

  public ParkingCharge leave(Instant date, Car car, String lotId, Permit permit) {
    Instant lotEnterTime = car.getLotEnter();
    Instant lotExitTime = date;
    Money charge = new Money(0);

    //1. find out what lot type it is based on the lotid
    ParkingLot lot = getLotTypeByLotId(lotId);
    ParkingLotType lotType = lot.getLotType();

    if (lotType == ParkingLotType.ENTRY) {
      Duration duration = Duration.between(lotEnterTime, lotExitTime);
      long daysInLot = duration.toHours();
      //2. calculate the charge
      if (daysInLot > 0) {
        charge = calculateOvernightCharge(car, daysInLot);
      }

    } else if (lotType == ParkingLotType.ENTRYEXIT) {
      //charge based on hours

      Duration duration = Duration.between(lotEnterTime, lotExitTime);
      long hoursInLot = duration.toHours();

      charge = calculateHourlyCharge(car, hoursInLot);
    }
    //3. do the charge stuff
    return transactionManager.park(date, permit, lotId, charge);
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
  
  // Calculates the discounted rate for a given rate and car type.
  private double getDiscountedRate(Car car, double rate) {
    CarType carType = car.getCarType();
    double discountPercentage = carType.getDiscountPercentage();
    return rate * (1 - discountPercentage);
  }

  // Calculates the overnight parking charge for a given car and the number of days it was parked.
  private Money calculateOvernightCharge(Car car, long daysInLot) {
    double dailyRate = 30.00;
    double discountedRate = getDiscountedRate(car, dailyRate);
    double totalDollars = discountedRate * daysInLot;
    return new Money(totalDollars);
  }

  // Calculates the hourly parking charge for a given car and the number of hours it was parked.
  private Money calculateHourlyCharge(Car car, long hoursInLot) {
    double hourlyRate = 15.00;
    double discountedRate = getDiscountedRate(car, hourlyRate);
    double totalDollars = discountedRate * hoursInLot;
    return new Money(totalDollars);
  }

  // Calculates the one-time parking charge for a given car.
  private Money calculateOneTimeCharge(Car car) {
    double dailyRate = 32.00;
    double discountedRate = getDiscountedRate(car, dailyRate);
    return new Money(discountedRate);
  }

}