/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.strategy;

import edu.du.ict4315.parking.strategy.ParkingChargeStrategyFactory;
import edu.du.ict4315.parking.strategy.ParkingChargeStrategy;
import edu.du.ict4315.parking.strategy.WeekendPrimeTimeStrategy;
import java.io.Serializable;

/**
 *
 * @author candace.saindon
 */
public class WeekendPrimeTimeStrategyFactory implements ParkingChargeStrategyFactory, Serializable {

    @Override
    public ParkingChargeStrategy makeStrategy() {
       return new WeekendPrimeTimeStrategy();
    }
    
}
