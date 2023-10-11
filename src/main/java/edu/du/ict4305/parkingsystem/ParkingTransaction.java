/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.Instant;
import java.util.Objects;

/**
 * ParkingTransaction holds info about a parking charge incurred by a vehicle
 *
 * @author candace.saindon
 */
public class ParkingTransaction {

    private final Permit permit;
    private final ParkingLot parkingLot;
    private final Instant incurred;
    private final Money amount;

    public static class Builder {

        //Required parameters
        private final Permit permit;
        private final ParkingLot parkingLot;
        private final Instant incurred;
        private final Money amount;

        //Optional parameters - initialized to default values
        //none
        
        public Builder(Instant incurred, Permit permit, ParkingLot parkingLot, Money amount) {
            this.permit = permit;
            this.parkingLot = parkingLot;
            this.incurred = incurred;
            this.amount = amount;
        }

        public ParkingTransaction build() {
            return new ParkingTransaction(this);
        }
    }

    private ParkingTransaction(Builder builder) {
        permit = builder.permit;
        parkingLot = builder.parkingLot;
        incurred = builder.incurred;
        amount = builder.amount;
    }

    public Money getAmount() {
        return amount;
    }

    public Permit getPermit() {
        return permit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingTransaction that = (ParkingTransaction) o;
        return Objects.equals(permit, that.permit)
                && Objects.equals(parkingLot, that.parkingLot)
                && Objects.equals(incurred, that.incurred)
                && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permit, parkingLot, incurred, amount);
    }

    public String toString() {
        return getClass().getName() + "[permit=" + permit + ", parkinglot=" + parkingLot
                + ",incurred=" + incurred + ", amount=" + amount + "]";
    }
}
