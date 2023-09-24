/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

/**
 *
 * @author candace.saindon
 */
public interface Office {
  //only methods that can be related to any type of office class are in this interface
  public Customer register(String firstName, String lastName, String address1,
                           String address2, String city,
                           String state, String zipCode, String phone);
  public Customer getCustomerByName(String firstName, String lastName);
  public Customer getCustomerByPhone(String phone);
}
