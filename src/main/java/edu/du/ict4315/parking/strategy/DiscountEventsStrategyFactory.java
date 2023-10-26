/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.strategy;

import edu.du.ict4315.parking.strategy.ParkingChargeStrategyFactory;
import edu.du.ict4315.parking.strategy.DiscountEventsStrategy;
import edu.du.ict4315.parking.strategy.ParkingChargeStrategy;

/**
 *
 * @author candace.saindon
 */
public class DiscountEventsStrategyFactory implements ParkingChargeStrategyFactory {

    @Override
    public ParkingChargeStrategy makeStrategy() {
        return new DiscountEventsStrategy();
    }

}
