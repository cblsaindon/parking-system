/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.serialization.ParkingRequest;
import edu.du.ict4315.parking.serialization.ParkingResponse;
import edu.du.ict4315.parking.server.RealParkingService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RealParkingServiceTest {

    @Mock
    private ParkingOffice parkingOffice;

    private RealParkingService parkingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mock objects

        // Create the parkingService with the mocked parkingOffice
        parkingService = new RealParkingService(parkingOffice);
    }

    @Test
    public void testHandleInput() throws Exception {
        // Create a sample ParkingRequest JSON
        String jsonRequest = "{\"command\":\"CUSTOMER\",\"properties\":{\"First Name\":\"John\",\"Last Name\":\"Doe\",\"Phone Number\":\"1234567890\",\"Street Address 1\":\"123 Main St\",\"City\":\"Denver\",\"State\":\"CO\",\"Zipcode\":\"80202\"}}\nend\n";
        InputStream inputStream = new ByteArrayInputStream(jsonRequest.getBytes(StandardCharsets.UTF_8));

        // Mock the behavior of parkingOffice's register method
        when(parkingOffice.register(any(Customer.class))).thenReturn("12345"); // Mocked customer ID

        // Call the method to test
        ParkingResponse response = parkingService.handleInput(inputStream);

        // Verify that the parkingOffice's register method was called with the expected Customer object
        verify(parkingOffice).register(any(Customer.class));

        // Verify the response
        assertTrue(response.getSuccess());
        assertEquals("Customer ID: 12345", response.getMessage());
    }

    @Test
    public void testPerformCommandWithInvalidCommand() {
        // Create a sample ParkingRequest with an unsupported command
        ParkingRequest request = new ParkingRequest("INVALID_COMMAND", new HashMap<>());

        // Call the method to test
        ParkingResponse response = parkingService.performCommand(request);

        // Verify the response
        assertFalse(response.getSuccess());
        assertEquals("The command 'INVALID_COMMAND' is not recognized or supported.", response.getMessage());
    }

    // Add more unit tests for other methods and scenarios as needed
}
