/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package edu.du.ict4315.parking.charges.factory;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.du.ict4315.parking.charges.strategy.DiscountEventsStrategy;
import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategy;

public class DiscountEventsStrategyFactoryTest {

    private ParkingChargeStrategyFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new DiscountEventsStrategyFactory();
    }

    @Test
    public void testMakeStrategy() {
        ParkingChargeStrategy strategy = factory.makeStrategy();
        assertNotNull(strategy);
        assertTrue(strategy instanceof DiscountEventsStrategy);
    }
}
