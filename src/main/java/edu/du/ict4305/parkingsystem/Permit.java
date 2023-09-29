/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class maintains all the Parking permit related information like its,
 * vehicle, and expiration date details. Using this class we can create, modify,
 * and print parking permit information.
 *
 * @author candace.saindon
 */
public class Permit {

    private String id;
    private Car car;
    private LocalDateTime expiration;

    public Permit(String id, Car car, LocalDateTime expiration) {
        this.id = id;
        this.car = car;
        this.expiration = expiration;
    }

    public Permit(String id, Car car) {
        LocalDateTime now = LocalDateTime.now();
        this.id = id;
        this.car = car;
        this.expiration = now;
    }

    public Car getCar() {
        return car;
    }

    public String getId() {
        return id;
    }

    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return expiration.isBefore(now);
    }

    public void setCar(Car car) {
        this.car = car;
    }

    private String createPermitId() {
        id = PermitIdGenerator.generateId();
        return id;
    }

    public String toString() {
        return getClass().getName() + "[id=" + id + ", permitExpiration=" + expiration
                + "]";
    }

}
