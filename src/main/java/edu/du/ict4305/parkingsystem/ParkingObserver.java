/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

/**
 *
 * @author candace.saindon
 */
public class ParkingObserver implements ParkingAction {
    //A ParkingObserver object could register with all the lots, and post a parking transaction whenever a car parks or leaves.
    //The ParkingObserver should register with each ParkingLot and receive the message
    //Once a car enters (in an entry-scan only lot) or leaves (in an entry-scan and exit-scan lot), 
    ////then the ParkingObserver will be updated, and then can register the charge with the parking
    ////system via the TransactionManager’s park() method.
    
    private String name;
    private TransactionManager transactionmanager;
    private ParkingLot parkingLot;
    
    public ParkingObserver(String name) {
        this.name = name;
    }
    
    @Override
    public void update(ParkingEvent event) {
        //use the parking lot's method to compute the charges
        //create a new transaction using the TransactionManager data member
        transactionmanager = new TransactionManager();
    }
    
}
