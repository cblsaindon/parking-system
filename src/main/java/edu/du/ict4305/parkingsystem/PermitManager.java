/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Candace Saindon
 */
/**
 * This is the manager class which manages all the parking permits.
 */
public class PermitManager {
  
  private List<Permit> permits =  new ArrayList<Permit>();
  /**
   * This method will create an object of ParkingPermit class and will add it to the permits
   * collection. Note: Assume that the expiration date will be one year from the current date.
   */
  public Permit register(Car car) {
    String id = PermitIdGenerator.generateId();  
    if (car == null) {
      throw new IllegalArgumentException("Car must not be null");
    }
    LocalDateTime expiration = LocalDateTime.now();
    Permit permit = new Permit(id, car, expiration);

    // Add the permit to the collection
    permits.add(permit);
    return permit;
  }
  
  public Permit findPermit(String permitId) {
    Permit permit = null;
    for (Permit p: permits) {
        if (p.getId().equals(permitId)) {
            permit = p;
            break;
        }
    }
    return permit;
  }
 
}

