/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking;

import java.time.LocalDate;
import java.time.Instant;
import java.util.Objects;

/**
 *
 * The Car class models a car with its attributes such as license plate, car type, owner, permit,
 * and the time it entered the parking lot.
 *
 * @author candace.saindon
 */
public class Car {

  private String licensePlate;
  private CarType type;
  private Customer customer;
  private Instant lotEnter;


  /**
   * Constructor for Car class
   *
   * @param licensePlate the license plate number of the car
   * @param type the type of car (i.e. compact, sedan, truck, etc.)
   * @param customer the owner of the car
   * @throws IllegalArgumentException if the license plate, car type, or owner is null or empty
   */
  public Car(String licensePlate, CarType type, Customer customer) {
    try {
      if (licensePlate == null || licensePlate.isEmpty()) {
        throw new IllegalArgumentException("The license must not be null or empty");
      }

      if (type == null) {
        throw new IllegalArgumentException("The car type must not be null");
      }

      this.lotEnter = null;
      this.customer = customer;
      this.licensePlate = licensePlate;
      this.type = type;

    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
      throw e;
    }
  }


  public void setLotEnter(Instant lotEnter) {
    this.lotEnter = lotEnter;
  }

  public Instant getLotEnter() {
    return lotEnter;
  }

  /**
   *
   * Calculates the number of hours the car was in the parking lot
   *
   * @param lotExit the time the car exited the parking lot
   * @return the number of hours the car was in the parking lot
   * @throws IllegalArgumentException if the car is not in a lot
   */
  public int getHoursInLot(Instant lotExit) {

    if (this.lotEnter == null) {
      throw new IllegalArgumentException("Car is not in a lot");
    }

    int hoursInLot = this.lotEnter.compareTo(lotExit);
    return hoursInLot;
  }

  public CarType getCarType() {
    return type;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setLicensePlate(String licensePlate) {
    try {
      if (licensePlate == null || licensePlate.isEmpty()) {
        throw new IllegalArgumentException("The license must not be null or empty");
      }
      this.licensePlate = licensePlate;
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
      throw e;
    }
  }



  public void setCarType(CarType type) {
    
    try {
      if (type == null) {
        throw new IllegalArgumentException("The car type must not be null");
      }
      this.type = type;
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
      throw e;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Car car = (Car) o;
    return licensePlate.equals(car.licensePlate)
        && customer.equals(car.customer)
        && type == car.type
        && Objects.equals(lotEnter, car.lotEnter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(licensePlate, customer, type, lotEnter);
  }

  public String toString() {
    return getClass().getName() + "[licensePlate=" + licensePlate + ",type=" + type + ",customer=" + customer + "]";
  }

  String getLicense() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
}
