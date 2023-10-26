/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.command;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.ParkingOffice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author cblsa
 */
public class RegisterCustomerCommand implements Command {

    private ParkingOffice office;

    public RegisterCustomerCommand(ParkingOffice office) {
        this.office = office;
    }

    private void checkParameters(Properties properties) {
        List<String> requiredProperties = Arrays.asList(
                "first name",
                "last name",
                "phone number",
                "street address 1",
                "street address 2",
                "city",
                "state",
                "zipcode"
        );

        //capture all validation errors into a list. If there are any, the error messages will be printed all at once for the user
        List<String> validationErrors = new ArrayList<>();

        //loop through all parameters and check if any keys are missing or values are empty      
        for (String property : requiredProperties) {
            if (!properties.containsKey(property)) {
                validationErrors.add("Parameter '" + property + "' is missing.");
            }

            String value = properties.getProperty(property);

            if (value == null || value.isEmpty()) {
                validationErrors.add("Parameter '" + property + "' is empty or null.");
            }

            if (!validationErrors.isEmpty()) {
                throw new IllegalArgumentException(String.join("\n", validationErrors));
            }

        }
    }

    @Override
    public String getCommandName() {
        return "registerCustomer";
    }

    @Override
    public String getDisplayName() {
        return "Register a Customer";
    }

    @Override
    public String execute(Properties params) {

        //retrieve the parameter values based on the key
        String firstName = params.getProperty("first name");
        String lastName = params.getProperty("last name");
        String phoneNumber = params.getProperty("phone number");
        String streetAddress1 = params.getProperty("street address 1");
        String streetAddress2 = params.getProperty("street address 2");
        String city = params.getProperty("city");
        String state = params.getProperty("state");
        String zipCode = params.getProperty("zipcode");

        //create address and customer so that we can fully create the car object
        Address address = new Address.Builder(streetAddress1, city, state, zipCode).streetAddress2(streetAddress2).build();
        Customer customer = new Customer.Builder(firstName, lastName).address(address).phoneNumber(phoneNumber).build();
        return "Customer registered successfully: " + customer.toString();
    }

}
