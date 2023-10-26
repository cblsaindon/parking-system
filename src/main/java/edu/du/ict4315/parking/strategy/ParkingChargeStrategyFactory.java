/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.du.ict4315.parking.strategy;

import edu.du.ict4315.parking.strategy.ParkingChargeStrategy;

/**
 *
 * @author candace.saindon
 */
//Factory interface with a method to create strategies
public interface ParkingChargeStrategyFactory {

    public ParkingChargeStrategy makeStrategy();
}
