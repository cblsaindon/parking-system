/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.serialization;

/**
 *
 * @author candace.saindon
 */
import java.io.Serializable;
import java.util.Map;
import com.google.gson.Gson;

public class ParkingRequest implements Serializable {

    private String commandName;
    private Object properties;

    public ParkingRequest(String commandName, Object properties) {
        this.commandName = commandName;
        this.properties = properties;
    }

    public Object getProperties() {
        return properties;
    }

    public String getCommandName() {
        return commandName;
    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static ParkingRequest fromJSON(String JSON) {
        Gson gson = new Gson();
        return gson.fromJson(JSON, ParkingRequest.class);
    }

    @Override
    public String toString() {
        return "ParkingRequest{"
                + "commandName='" + commandName + '\''
                + ", properties=" + properties
                + '}';
    }

}


