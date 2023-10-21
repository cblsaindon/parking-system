/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.charges.decorator;

import edu.du.ict4305.parkingsystem.Money;
import edu.du.ict4305.parkingsystem.ParkingEvent;
import edu.du.ict4305.parkingsystem.Permit;
import edu.du.ict4305.parkingsystem.RealParkingLot;
import java.time.Instant;

/**
 *
 * @author cblsa
 */
public abstract class ParkingChargeCalculator {
 
    public void ParkingChargeCalculator() {
        
    }
    
    public abstract Money getParkingCharge(ParkingEvent event);
}
