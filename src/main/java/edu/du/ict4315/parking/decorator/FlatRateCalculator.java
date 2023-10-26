/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.currency.Money;
import edu.du.ict4315.parking.observers.ParkingEvent;

/**
 *
 * @author cblsa
 */
public class FlatRateCalculator extends ParkingChargeCalculator {

    @Override
    public Money getParkingCharge(ParkingEvent event) {
        Money charge = Money.of(15);
        return charge;
    }

}
