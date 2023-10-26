/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking;

/**
 * The PermitIdGenerator class is responsible for generating unique permit IDs.
 * @author candace.saindon
 */
public class PermitIdGenerator {
    private static int counter = 0;

  public static String generateId() {
    return "perm_" + (++counter);
  }
}
