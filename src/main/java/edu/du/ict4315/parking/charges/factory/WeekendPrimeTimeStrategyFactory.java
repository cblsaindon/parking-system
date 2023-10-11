/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.charges.factory;

import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategy;
import edu.du.ict4315.parking.charges.strategy.WeekendPrimeTimeStrategy;

/**
 *
 * @author candace.saindon
 */
public class WeekendPrimeTimeStrategyFactory implements ParkingChargeStrategyFactory {

    @Override
    public ParkingChargeStrategy makeStrategy() {
       return new WeekendPrimeTimeStrategy();
    }
    
}
