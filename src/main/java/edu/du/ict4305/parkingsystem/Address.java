/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

/**
 *
 * The Address class is used to store the street address information of a customer. It includes
 * street address 1 and 2, city, zip code, and state. It validates the inputs by checking if the
 * inputs are not null or empty and if the zip code is a five-digit number.
 *
 * @author candace.saindon
 */
import java.lang.String;
import java.util.regex.Pattern;

public class Address {

  private String streetAddress1;
  private String streetAddress2;
  private String city;
  private String zipCode;
  private String state;

  /**
   *
   * Constructor to initialize the address information
   * @param streetAddress1 street address line 1
   * @param streetAddress2 street address line 2
   * @param city the city the address is located in
   * @param zipCode the five-digit zip code of the address
   * @param state the two-letter abbreviation of the state the address is located in
   * @throws IllegalArgumentException if any of the inputs are null or empty, or if the zip code is
   * not a five-digit number
   */
  public Address(String streetAddress1, String streetAddress2, String city, String state, String zipCode) {
    try {
      if (streetAddress1 == null || streetAddress1.isEmpty()) {
        throw new IllegalArgumentException("Null or empty streetAddress1");
      }
      if (city == null || city.isEmpty()) {
        throw new IllegalArgumentException("Null or empty city");
      }
      if (zipCode == null || zipCode.isEmpty()) {
        throw new IllegalArgumentException("Null or empty zipCode");
      }
      if (state == null || state.isEmpty()) {
        throw new IllegalArgumentException("Null or empty state");
      }

      if (!Pattern.matches("^[0-9]{5}$", zipCode)) {
        throw new IllegalArgumentException("Zipcode must be five digits: " + zipCode);
      }

      this.streetAddress1 = streetAddress1;
      this.streetAddress2 = streetAddress2;
      this.city = city;
      this.zipCode = zipCode;
      this.state = state;

    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
      throw e;
    }

  }

  /**
   *
   * Gets the address information as a concatenated string
   *
   * @return the address information as a string
   */
  public String getAddressInfo() {

    String addressInfo = "" + streetAddress1 + " " + streetAddress2 + " "
        + city + " " + zipCode + " " + state;

    return addressInfo;
  }

  /**
   *
   * Returns a string representation of the address
   *
   * @return the string representation of the address
   */
  public String toString() {

    return getClass().getName() + "[streetAddress1=" + streetAddress1 + ", streetAddress2=" + streetAddress2
        + ",city=" + city + ", zipCode=" + zipCode + ", state=" + state + "]";
  }

}
