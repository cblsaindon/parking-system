package edu.du.ict4315.parking.server;

import edu.du.ict4315.parking.Address;
import java.io.InputStream;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.serialization.ParkingRequest;
import edu.du.ict4315.parking.serialization.ParkingResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ParkingService {

    protected final ParkingOffice parkingOffice;

    public ParkingService(ParkingOffice parkingOffice) {
        this.parkingOffice = parkingOffice;
    }

    public ParkingResponse handleInput(InputStream in) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonInput = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("end")) {
                    break;
                }
                jsonInput.append(line);
            }
            ParkingRequest request = ParkingRequest.fromJSON(jsonInput.toString());
            return performCommand(request);
        } catch (IOException e) {
            // Handle the exception as needed
            e.printStackTrace();
            return new ParkingResponse(false, 0, "Error processing input: " + e.getMessage());
        }
    }

    public ParkingResponse performCommand(ParkingRequest request) {
        String command = request.getCommandName();
        Object properties = request.getProperties();
        CarType carType;
        String matched;
        switch (command) {
            case "CUSTOMER":
                Map<String, String> customerData = (Map<String, String>) properties;

                // Extract customer information from the properties map
                String firstName = customerData.get("First Name");
                String lastName = customerData.get("Last Name");
                String phoneNumber = customerData.get("Phone Number");
                String streetAddress1 = customerData.get("Street Address 1");
                String streetAddress2 = customerData.get("Street Address 2");
                String city = customerData.get("City");
                String state = customerData.get("State");
                String zipcode = customerData.get("Zipcode");
                Address address = new Address.Builder(streetAddress1, city, state, zipcode).streetAddress2(streetAddress2).build();
                Customer customer = new Customer.Builder(firstName, lastName).phoneNumber(phoneNumber).address(address).build();

                String customerId = parkingOffice.register(customer);
                return new ParkingResponse(true, 3, "Customer ID: " + customerId);
            case "CAR":

                Map<String, String> carData = (Map<String, String>) properties;

                // Extract car information from the properties map
                String licensePlate = carData.get("License Plate");
                String carTypeString = carData.get("Car Type").toLowerCase();
                customerId = carData.get("Customer ID");

                //Determine car type based on user input
                switch (carTypeString) {
                    case "compact":
                        carType = CarType.COMPACT;
                        break;
                    case "suv":
                        carType = CarType.SUV;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid car type: " + carTypeString);
                }

                customer = checkCustomer(customerId);
                

                parkingOffice.register(customer, licensePlate, carType);
                return new ParkingResponse(true, 3, "License Plate: " + licensePlate);

            case "PARK":
                matched = "park";
                break;
            case "CHARGES":
                matched = "charges";
                break;
            case "STOP":
                Server.stopServer();
                break;
            default:
                matched = "unknown";
                return new ParkingResponse(false, 4, "The command '" + command + "' is not recognized or supported.");
        }
        return null;
    }

    private Customer checkCustomer(String customerId) {
        if (customerId == null) {
            customerId = "";
        }
        String[] parts = customerId.split("=");
        if (parts.length == 2) {
            customerId = parts[1];
        }
        Customer customer = parkingOffice.getCustomerById(customerId);

        if (customer == null) {
            customer = new Customer.Builder("Unknown", null).build();
        }
        return customer;
    }

}
