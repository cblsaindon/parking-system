package edu.du.ict4315.parking.client;

import edu.du.ict4315.parking.serialization.ParkingRequest;
import edu.du.ict4315.parking.serialization.ParkingResponse;
import edu.du.ict4315.parking.server.RealParkingService;
import edu.du.ict4315.parking.server.Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static final String[][] COMMANDS = new String[][]{
        {"Register Customer", "CUSTOMER", "First Name", "Last Name", "Phone Number", "Street Address 1", "Street Address 2", "City", "State", "Zipcode"},
        {"Register Vehicle", "CAR", "License Plate", "Car Type", "Customer"},
        {"Start Parking", "PARK", "Permit Id", "Time"},
        {"Finish Parking", "PARK", "Permit Id", "Time"},
        {"Get Charges", "CHARGES", "Customer", "Car"},
        {"Stop", "STOP"}};

    private static final int PORT = 7777;
    private static final String SERVER = "localhost";

    private Client() {
    }

    public static ParkingResponse runCommand(String command, ParkingRequest request)
            throws IOException {

        InetAddress host = InetAddress.getByName(SERVER);
        try (Socket link = new Socket(host, PORT)) {
            // Serialize the ParkingRequest object to JSON
            String jsonRequest = request.toJSON();

            // Send the JSON request to the server
            PrintWriter writer = new PrintWriter(link.getOutputStream(), true);
            writer.println(jsonRequest);
            writer.println("end");

            //Server connection established
            System.out.println("You are now connected to: " + host.getHostAddress());

            //Fetch ParkingResponse object from the server
            ObjectInputStream input = new ObjectInputStream(link.getInputStream());
            ParkingResponse response = (ParkingResponse) input.readObject();

            // Close connection
            link.close();
            return response;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
            return new ParkingResponse(false, 0, "Error: " + ex.getMessage());
        }

    }

    public static Map<String, Command> commands() {
        Map<String, Command> commands = new HashMap<>();
        for (String[] description : COMMANDS) {
            commands.put(description[1], new Command(description[0], description[1],
                    Arrays.asList(description).subList(2, description.length)));
        }
        return commands;
    }

    /**
     * Run this as: $ java ict4300.week8.client.Client COMMAND label1=value1
     * label2=value2 ... Use LIST to get the list of commands and their labels.
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ParkingResponse serverResponse = new ParkingResponse(false, 0, "");

        while (true) {
            // Prompt the user for input and send it to the server
            System.out.print("Enter a command (or type 'LIST' for available commands): ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                // Exit the loop and terminate the client when 'quit' is entered
                break;
            }

            if (userInput.equalsIgnoreCase("LIST")) {
                // Display the available commands when 'LIST' is entered
                System.out.println("Here are the available commands:");
                for (String[] description : COMMANDS) {
                    System.out.print(description[1] + ": ");
                    for (int i = 2; i < description.length; ++i) {
                        System.out.print(description[i] + " ");
                    }
                    System.out.println();
                }
                continue;
            }

            // Create a ParkingRequest and send it to the server
            Command command = commands().get(userInput);
            if (command == null) {
                System.out.println("Unrecognized command: " + userInput);
                continue;
            }

            Map<String, String> values = new LinkedHashMap<>();
            for (String label : command.fieldNames()) {
                System.out.print("Enter " + label + ": ");
                String value = scanner.nextLine();
                values.put(label, value);
            }

            ParkingRequest parkingRequest = new ParkingRequest(userInput, values);

            // Send the ParkingRequest and receive a response
            serverResponse = runCommand(userInput, parkingRequest);

            if (serverResponse == null) {
                System.out.println("Server Response: Not connected to the server");
            } else {
                System.out.println("Server Response: " + serverResponse.getMessage());
            }

        }
    }

}
