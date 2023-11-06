package edu.du.ict4315.parking.client;

import edu.du.ict4315.parking.serialization.ParkingRequest;
import edu.du.ict4315.parking.serialization.ParkingResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Command {
  private final String name;
  private final String command;
  private final List<String> fieldNames;
  
  public Command(String name, String command, List<String> fieldNames) {
    this.name = name;
    this.command = command;
    this.fieldNames = Collections.unmodifiableList(fieldNames);
  }
  
  public String name() {
    return name;
  }
  
  public List<String> fieldNames() {
    return fieldNames;
  }

public String execute(ParkingRequest request) {
    try {
        ParkingResponse response = Client.runCommand(command, request);
        return response.toString();
    } catch (IOException e) {
        e.printStackTrace();
        return e.getMessage();
    }
}

}
