/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

/**
 * Class: Customer The Customer class extends the Driver class and includes
 * additional attributes for customer ID, address, and phone number. It has a
 * constructor that sets the values of these attributes based on the parameters
 * passed to it. The class also has getter methods for the customer ID and phone
 * number. The toString method returns a string representation of the object.
 *
 * @author candace.saindon
 */
public class Customer {

    private final String firstName;
    private final String lastName;
    private final String customerId;
    private final Address address; //Customer has-an address
    private final String phoneNumber;

    public static class Builder {

        //Required parameters
        private final String firstName;
        private final String lastName;
        private final String customerId;

        //Optional parameters - initialized to default values
        private Address address = null;
        private String phoneNumber = "";

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.customerId = CustomerIdGenerator.generateId();
        }

        public Builder address(Address val) {
            address = val;
            return this;
        }

        public Builder phoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    private Customer(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        customerId = builder.customerId;
        address = builder.address;
        phoneNumber = builder.phoneNumber;
    }

    /*
    public Customer() {
        customerId = CustomerIdGenerator.generateId();
        firstName = "";
        lastName = "";
        phoneNumber = "";
        address = new Address("", "", "", "", "");
    }

    public Customer(String firstName, String lastName,
            Address address, String phoneNumber) {
        this.address = address;
        this.customerId = CustomerIdGenerator.generateId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }
     */
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
        return getClass().getName() + "[firstName=" + firstName + ",lastName=" + lastName
                + ",address=" + address + ",phoneNumber=" + phoneNumber + "]";
    }

}
