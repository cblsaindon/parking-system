/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

/**
 * Class: Customer The Customer class extends the Driver class and includes additional attributes
 * for customer ID, address, and phone number. It has a constructor that sets the values of these
 * attributes based on the parameters passed to it. The class also has getter methods for the
 * customer ID and phone number. The toString method returns a string representation of the object.
 *
 * @author candace.saindon
 */
public class Customer {

  private String firstName;
  private String lastName;
  private String customerId;
  private Address address; //Customer has-an address
  private String phoneNumber;

  public Customer() {
    String id = CustomerIdGenerator.generateId();  
    firstName = "";
    lastName = "";
    phoneNumber = "";
    Address address = new Address("","","","","");
  }
  
  public Customer(String firstName, String lastName,
                  Address address, String phoneNumber) {
    this.address = address;
    String id = CustomerIdGenerator.generateId();
    this.customerId = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;

  }

  public String getId() {
    return customerId;
  }

  public String getPhone() {
    return phoneNumber;
  }

  public String getFirstName() {
      return firstName;
  }
  
  public String getLastName() {
      return lastName;
  }
  
  public String toString() {
    return getClass().getName() + "[firstName="+ firstName + ",lastName=" + lastName
        + ",address=" + address + ",phoneNumber=" + phoneNumber + "]";
  }

}
