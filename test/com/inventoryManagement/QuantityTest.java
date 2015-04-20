package com.inventoryManagement;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityTest {
    @Test
    public void testCompareReturns0IfTwoQuantitiesAreEqual() {
        assertEquals(0, new Quantity(5).compare(new Quantity(5)));
    }

    @Test
    public void testCompareReturns1IfGivenQuantityIsLesser() {
        assertEquals(1, new Quantity(5).compare(new Quantity(2)));
    }

    @Test
    public void testCompareReturnsMinus1IfGivenQuantityIsGreater() {
        assertEquals(-1, new Quantity(5).compare(new Quantity(10)));
    }

}