/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author candace.saindon
 */
public class RegisterCarCommand implements Command {
    private ParkingOffice office;
    
    public RegisterCarCommand(ParkingOffice office) {
      this.office = office;
    }

    private void checkParameters(Properties properties) {
      List<String> requiredProperties = Arrays.asList(
        "first name",
        "last name",
        "phone number",
        "car type",
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
      return "registerCar";
    }

    @Override
    public String getDisplayName() {
      return "Register a Car";
    }

    @Override
    public String execute(Properties params) {
           
      CarType carType = null;
      
      //retrieve the parameter values based on the key
      String firstName = params.getProperty("first name");
      String lastName = params.getProperty("last name");
      String phoneNumber = params.getProperty("phone number");
      String carTypeString = params.getProperty("car type");
      String streetAddress1 = params.getProperty("street address 1");
      String streetAddress2 = params.getProperty("street address 2");
      String city = params.getProperty("city");
      String state = params.getProperty("state");
      String zipCode = params.getProperty("zipcode");

      //Determine car type based on user input     
      if (carTypeString.equals("compact")) {
        carType = CarType.COMPACT;
      } else if (carTypeString.equals("suv")) {
        carType = CarType.SUV;
      } else {
        throw new IllegalArgumentException("Invalid car type: " + carTypeString);
      }
      
      //create address and customer so that we can fully create the car object
      Address address = new Address(streetAddress1,streetAddress2,city,state,zipCode);      
      Customer customer = new Customer(firstName,lastName,address,phoneNumber);
      
      //Build the car object that we need
      Car car = new Car("",carType, customer);

      return "Car registered successfully: " + car.toString();
    }
    
}

