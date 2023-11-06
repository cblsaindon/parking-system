/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking.serialization;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author candace.saindon
 */


public class ParkingRequestTest {

    @Test
    public void testConstructorAndToString() {
        String commandName = "park";
        String properties = "some properties";

        ParkingRequest request = new ParkingRequest(commandName, properties);

        assertEquals(commandName, request.getCommandName());
        assertEquals(properties, request.getProperties());

        String expectedToString = "ParkingRequest{commandName='park', properties=some properties}";
        assertEquals(expectedToString, request.toString());
    }

    @Test
    public void testToJSON() {
        String commandName = "park";
        String properties = "some properties";

        ParkingRequest request = new ParkingRequest(commandName, properties);
        String json = request.toJSON();

        String expectedJson = "{\"commandName\":\"park\",\"properties\":\"some properties\"}";
        assertEquals(expectedJson, json);
    }

    @Test
    public void testFromJSON() {
        String json = "{\"commandName\":\"park\",\"properties\":\"some properties\"}";

        ParkingRequest request = ParkingRequest.fromJSON(json);

        assertEquals("park", request.getCommandName());
        assertEquals("some properties", request.getProperties());
    }
}
