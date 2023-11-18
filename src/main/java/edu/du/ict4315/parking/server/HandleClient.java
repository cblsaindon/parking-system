/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking.server;

import edu.du.ict4315.parking.serialization.ParkingResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cblsa
 */
public class HandleClient implements Runnable {

    private static final Logger logger = Logger.getLogger(HandleClient.class.getName());
    private final Socket client;
    private final ParkingService service;

    public HandleClient(Socket client, ParkingService service) {
        this.client = client;
        this.service = service;
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void run() {

        try {
            // Using os to return output to client
            ObjectOutputStream ostream = new ObjectOutputStream(client.getOutputStream());
            ParkingResponse output;
            try {
                // Process requestData from client and return output as responseData
                //ONLY 1 at a time!!
                synchronized (service) {
                    output = service.handleInput(client.getInputStream());
                }

            } catch (RuntimeException ex) {
                ex.printStackTrace();
                output = new ParkingResponse(false, 0, ex.getLocalizedMessage());
            } catch (Exception ex) {
                Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
                output = new ParkingResponse(false, 0, ex.getLocalizedMessage());
            }
            ostream.writeObject(output);
            ostream.flush();

            // Send the "end" indicator to signal the end of data
            PrintWriter endIndicator = new PrintWriter(client.getOutputStream());
            endIndicator.println("end");
            endIndicator.flush();

        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to handle client request.", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to close client socket.", e);
            }
        }
    }
}
