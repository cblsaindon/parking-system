/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.util.Properties;

/**
 *
 * @author candace.saindon
 */
public interface Command {

  public String getCommandName();
  public String getDisplayName();
  public String execute(Properties params);

}
