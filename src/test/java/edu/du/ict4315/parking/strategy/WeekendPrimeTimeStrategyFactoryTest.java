/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking.strategy;

import edu.du.ict4315.parking.strategy.WeekendPrimeTimeStrategyFactory;
import edu.du.ict4315.parking.strategy.ParkingChargeStrategyFactory;
import edu.du.ict4315.parking.strategy.ParkingChargeStrategy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.du.ict4315.parking.strategy.WeekendPrimeTimeStrategy;
import edu.du.ict4315.parking.strategy.ParkingChargeStrategy;

public class WeekendPrimeTimeStrategyFactoryTest {

    private ParkingChargeStrategyFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new WeekendPrimeTimeStrategyFactory();
    }

    @Test
    public void testMakeStrategy() {
        ParkingChargeStrategy strategy = factory.makeStrategy();
        assertNotNull(strategy);
        assertTrue(strategy instanceof WeekendPrimeTimeStrategy);
    }
}
