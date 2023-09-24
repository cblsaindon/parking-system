/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

/**
 *
 * @author candace.saindon
 */
enum CarType {
  COMPACT(0.2),
  SUV(0);

  private final double discountPercentage;

  CarType(double discountPercentage) {
    this.discountPercentage = discountPercentage;
  }

  public double getDiscountPercentage() {
    return discountPercentage;
  }
}
