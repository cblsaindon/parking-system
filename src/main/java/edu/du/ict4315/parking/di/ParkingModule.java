/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.di;

import com.google.inject.AbstractModule;
import edu.du.ict4315.parking.server.RealParkingService;

/**
 *
 * @author candace.saindon
 */


public class ParkingModule extends AbstractModule {

    @Override
    protected void configure() {
        // Bind the RealParkingService interface to its implementation
        bind(RealParkingService.class).to(RealParkingService.class);
        
    }
}
