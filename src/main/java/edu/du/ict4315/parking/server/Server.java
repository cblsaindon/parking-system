package edu.du.ict4315.parking.server;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import edu.du.ict4315.parking.serialization.ParkingResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    static {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format", "%1$tc %4$-7s (%2$s) %5$s %6$s%n");
    }

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private final int PORT = 7777;

    private final ParkingService service;

    public Server(ParkingService service) {
        this.service = service;
    }

    public void startServer() throws IOException, Exception {
        logger.info("Starting server: " + InetAddress.getLocalHost().getHostAddress());
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setReuseAddress(true);
            while (true) {
                Socket client = serverSocket.accept();
                handleClient(client);
            }
        }
    }

    private void handleClient(Socket client) throws Exception {
        try {
            // Using os to return output to client
            ObjectOutputStream ostream = new ObjectOutputStream(client.getOutputStream());
            ParkingResponse output;
            try {
                // Process requestData from client and return output as responseData
                output = service.handleInput(client.getInputStream());
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                output = new ParkingResponse(false, 0, ex.getLocalizedMessage());
            }
            ostream.writeObject(output);
            ostream.flush();

            // Send the "end" indicator to signal the end of data
            PrintWriter endIndicator = new PrintWriter(client.getOutputStream());
            endIndicator.println("end");
            endIndicator.flush();

        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to handle client request.", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to close client socket.", e);
            }
        }
    }

    /**
     * Run this as: $ java {package name}
     */
    public static void main(String[] args) throws Exception {

        List<Customer> customers = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        List<RealParkingLot> lots = new ArrayList<>();
        Address address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        ParkingOffice parkingOffice = new ParkingOffice("Office", address, customers, cars, lots);
        ParkingService service = new ParkingService(parkingOffice);

        new Server(service).startServer();
    }
}
