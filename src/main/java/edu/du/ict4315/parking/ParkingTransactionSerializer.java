/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4315.parking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author candace.saindon
 */
public class ParkingTransactionSerializer {

    public void saveTransactions(List<ParkingTransaction> transactions, String filename) {
        //Attempt to serialize transaction from the file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(transactions);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error writing to file: " + filename, e);
        }
    }

    public List<ParkingTransaction> loadTransactions(String filename) {

        List<ParkingTransaction> transactions = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            // Attempt to deserialize the list of transactions from the file
            transactions = (List<ParkingTransaction>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found: " + filename, e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading from file: " + filename, e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class not found while deserializing", e);
        }

        return transactions;
    }
}

/*
How to use in main:
// Create an instance of ParkingTransactionSerializer
ParkingTransactionSerializer serializer = new ParkingTransactionSerializer();

// Save transactions to a file
List<ParkingTransaction> transactions = ... // Get your list of transactions
serializer.saveTransactions(transactions, "transactions.ser");

// Load transactions from a file
List<ParkingTransaction> loadedTransactions = serializer.loadTransactions("transactions.ser");
*/