/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.charges.decorator;

import edu.du.ict4305.parkingsystem.Money;
import edu.du.ict4305.parkingsystem.ParkingEvent;

/**
 *
 * @author candace.saindon
 */
public abstract class ParkingChargeCalculatorDecorator extends ParkingChargeCalculator {

    protected ParkingChargeCalculator parkingChargeCalculator;

    public ParkingChargeCalculatorDecorator(ParkingChargeCalculator parkingChargeCalculator) {
        this.parkingChargeCalculator = parkingChargeCalculator;
    }

    @Override
    public Money getParkingCharge(ParkingEvent event) {
        Money charge = parkingChargeCalculator.getParkingCharge(event);
        return charge;
    }
}
