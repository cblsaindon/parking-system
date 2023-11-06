/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking.serialization;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author cblsa
 */

public class ParkingResponseTest {

    @Test
    public void testConstructorAndToString() {
        boolean success = true;
        int statusCode = 200;
        String message = "OK";

        ParkingResponse response = new ParkingResponse(success, statusCode, message);

        assertEquals(success, response.getSuccess());
        assertEquals(statusCode, response.getStatusCode());
        assertEquals(message, response.getMessage());

        String expectedToString = "ParkingResponse{statusCode=200, message='OK'}";
        assertEquals(expectedToString, response.toString());
    }

    @Test
    public void testToJSON() {
        boolean success = true;
        int statusCode = 200;
        String message = "OK";

        ParkingResponse response = new ParkingResponse(success, statusCode, message);
        String json = response.toJSON();

        String expectedJson = "{\"success\":true,\"statusCode\":200,\"message\":\"OK\"}";
        assertEquals(expectedJson, json);
    }

    @Test
    public void testFromJSON() {
        String json = "{\"success\":true,\"statusCode\":200,\"message\":\"OK\"}";

        ParkingResponse response = ParkingResponse.fromJson(json);

        assertEquals(true, response.getSuccess());
        assertEquals(200, response.getStatusCode());
        assertEquals("OK", response.getMessage());
    }
}
