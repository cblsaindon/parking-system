/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

/**
 * Money class representing dollar value in cents
 * @author candace.saindon
 */
public class Money {

  private long cents;

  public Money(long cents) {
    this.cents = cents;
  }

  public Money(double dollars) {
    this.cents = (long) (dollars * 100);
  }

  public double getDollars() {
    return cents / 100.0;
  }

  public String toString() {
    return getClass().getName() + "[cents=" + cents + "]";
  }
}
