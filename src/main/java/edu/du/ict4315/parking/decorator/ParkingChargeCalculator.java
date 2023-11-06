/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import edu.du.ict4315.parking.Permit;
import edu.du.ict4315.parking.parkinglot.RealParkingLot;
import java.time.Instant;
import java.io.Serializable;

/**
 *
 * @author cblsa
 */
public abstract class ParkingChargeCalculator implements Serializable {
 
    public void ParkingChargeCalculator() {
        
    }
    
    public abstract Money getParkingCharge(ParkingEvent event);
}
