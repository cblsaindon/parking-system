package edu.du.ict4315.parking.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.di.ParkingModule;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Server {

    static {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format", "%1$tc %4$-7s (%2$s) %5$s %6$s%n");
    }

    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private final int PORT = 7777;
    private final ParkingService service;
    private Duration cumulativeDuration = Duration.ZERO;
    private int connectionCount = 0;
    private static volatile boolean doContinue = true;
    
    public Server(ParkingService service) {
        this.service = service;
    }

    public static void stopServer() {
        System.out.println("Server stopped");
        doContinue = false;
        Thread.currentThread().interrupt();
    }

    
    
    public void startServer() throws IOException {
        logger.info("Starting server: " + InetAddress.getLocalHost().getHostAddress());

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setReuseAddress(true);

            while (doContinue) {
                Socket client = serverSocket.accept();

                //Measure performance, start time
                Instant start = Instant.now();

                Runnable handler = new HandleClient(client, getService());
                new Thread(handler).start();

                //Measure performance, end time
                Instant done = Instant.now();

                //Calculate the duration between start and end time, and increase number of connections
                cumulativeDuration = cumulativeDuration.plus(Duration.between(start, done));
                connectionCount++;
  
            }

            System.out.println("Number of connections handled: " + connectionCount + ". Duration: " + cumulativeDuration);
            if (connectionCount > 0) {
                System.out.println("   " + (cumulativeDuration.toNanos() / connectionCount) + "ns. per connection");
            }
        }

    }

    public ParkingService getService() {
        return service;
    }

    /**
     * Run this as: $ java {package name}
     */
    public static void main(String[] args) throws Exception {
        // Create a Guice injector with your ParkingModule
        Injector injector = Guice.createInjector(new ParkingModule());

        // Use the injector to get the ParkingService
        ParkingService service = injector.getInstance(ParkingService.class);
        
        List<Customer> customers = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        List<RealParkingLot> lots = new ArrayList<>();
        Address address = new Address.Builder("1 Main St", "Denver", "CO", "80202").build();
        ParkingOffice parkingOffice = new ParkingOffice("Office", address, customers, cars, lots);
        service = new RealParkingService(parkingOffice);

        new Server(service).startServer();
    }
}
