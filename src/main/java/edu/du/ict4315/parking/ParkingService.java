/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking;

/**
 *
 * @author candace.saindon
 */

import edu.du.ict4315.parking.command.RegisterCarCommand;
import edu.du.ict4315.parking.command.Command;
import edu.du.ict4315.parking.command.RegisterCustomerCommand;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;

public class ParkingService implements Service, Serializable {
    
    private ParkingOffice office;
    private Map<String,Command> commands;
    
    public ParkingService(ParkingOffice office) {
      this.office = office;
      this.commands = new HashMap<>();
      
      RegisterCarCommand carCommand = new RegisterCarCommand(office);
      RegisterCustomerCommand custCommand = new RegisterCustomerCommand(office);
    
      register(carCommand);
      register(custCommand);
    }
    
    private void register(Command command) {
        String key = command.getCommandName();
        commands.put(key, command);
    }
    
    @Override
    public String performCommand(String commandName, String[] parameters) {
        Command command = commands.get(commandName);
        
        if (command != null) {
            Properties params = new Properties();
            
            for (String parameter : parameters) {
                String[] parts = parameter.split("=");
                if (parts.length == 2) {
                  String key = parts[0];
                  String value = parts[1];
                  params.setProperty(key, value);                    
                } else {
                  throw new IllegalArgumentException("Malformed parameter: " + parameter);
                }

            }
        
          return command.execute(params);
        
        } else {
          return "Command not found: " + commandName;
        }
    }
}
