/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;
import java.io.Serializable;
/**
 *
 * @author candace.saindon
 */
public abstract class ParkingChargeCalculatorDecorator extends ParkingChargeCalculator implements Serializable {

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
