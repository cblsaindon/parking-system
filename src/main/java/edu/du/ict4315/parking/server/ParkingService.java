/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.du.ict4315.parking.server;

import edu.du.ict4315.parking.serialization.ParkingRequest;
import edu.du.ict4315.parking.serialization.ParkingResponse;
import java.io.InputStream;

/**
 *
 * @author candace.saindon
 */

public interface ParkingService {

    ParkingResponse handleInput(InputStream in) throws Exception;

    ParkingResponse performCommand(ParkingRequest request);
}

