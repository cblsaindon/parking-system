/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.LocalDateTime;

/**
 *
 * @author candace.saindon
 */
public class ParkingEvent {
    private ParkingLot lot;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private Permit permit;

    public ParkingEvent(ParkingLot lot, Permit permit, LocalDateTime timeIn) {
        this.lot = lot;
        this.timeIn = timeIn;
        this.permit = permit;
    }
    
    public ParkingEvent(ParkingLot lot, Permit permit, LocalDateTime timeIn, LocalDateTime timeOut) {
        this.lot = lot;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.permit = permit;
    }
    
    public ParkingLot getLot() {
        return lot;
    }
    
    public LocalDateTime getTimeIn() {
        return timeIn;
    }
    
    public LocalDateTime getTimeOut() {
        return timeOut;
    }
    
    public Permit getPermit() {
        return permit;
    }
}
