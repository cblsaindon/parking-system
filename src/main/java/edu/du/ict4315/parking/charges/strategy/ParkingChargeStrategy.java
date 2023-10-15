/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.du.ict4315.parking.charges.strategy;

import edu.du.ict4305.parkingsystem.Money;
import edu.du.ict4305.parkingsystem.ParkingEvent;

/**
 *
 * @author cblsa
 */
public interface ParkingChargeStrategy {

    public String getStrategyName();
    public Money calculateParkingCharge(ParkingEvent event, Money baseRate);
}
