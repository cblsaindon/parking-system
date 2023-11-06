/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.serialization;

/**
 *
 * @author cblsa
 */
import com.google.gson.Gson;
import java.io.Serializable;

public class ParkingResponse implements Serializable {

    private Boolean success = true;
    private int statusCode = 0;
    private String message = "";

    public ParkingResponse(Boolean success, int statusCode, String message) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static ParkingResponse fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ParkingResponse.class);
    }

    @Override
    public String toString() {
        return "ParkingResponse{"
                + "statusCode=" + statusCode
                + ", message='" + message + '\''
                + '}';
    }
}


/*
    It should have a status code (integer) and a String message.
    Build unit tests to create and print them (constructor and toString)
    Add a method to convert ParkingResponses to JSON strings () and unit tests
    Add a method to convert JSON Strings into ParkingResponses () and unit tests
 */
