/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.Instant;

/**
 *
 * @author candace.saindon
 */
public class ParkingEvent {

    private ParkingLot lot;
    private Instant timeIn;
    private Instant timeOut;
    private Permit permit;

    public ParkingEvent(ParkingLot lot, Permit permit, Instant timeIn) {
        this.lot = lot;
        this.timeIn = timeIn;
        this.timeOut = null;
        this.permit = permit;
    }

    public ParkingEvent(ParkingLot lot, Permit permit, Instant timeIn, Instant timeOut) {
        this.lot = lot;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.permit = permit;
    }

    public ParkingLot getParkingLot() {
        return lot;
    }

    public Instant getTimeIn() {
        return timeIn;
    }

    public Instant getTimeOut() {
        return timeOut;
    }

    public Permit getPermit() {
        return permit;
    }
}
