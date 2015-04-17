package com.inventoryManagement;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoreTest {

    private Store brazil;
    private Store argentina;

    @Before
    public void setUp() throws Exception {
        brazil = new Store("Brazil", 100, 100);
        argentina = new Store("Argentina", 100, 50);
    }

    @Test
    public void testIsBasedInTellsThatTheStoreIsBasedInBrazil() {
        assertTrue(brazil.isBasedIn("Brazil"));
    }

    @Test
    public void testIsBasedInTellsThatTheStoreIsNotBasedInBrazil() {
        assertFalse(brazil.isBasedIn("Argentina"));
    }

    @Test
    public void testCalculateSalePriceForGivesCostOf5IpodsOfBrazil() {
        assertEquals(500, brazil.calculateSalePriceFor(5));
    }

    @Test
    public void testCalculateSalePriceForGivesCostOf5IpodsOfArgentina() {
        assertEquals(250, argentina.calculateSalePriceFor(5));
    }

    @Test
    public void testPurchaseSuppliesTheDemandedStock() {
        int supply = brazil.purchase(5);
        assertEquals(5,supply);
        assertEquals("Brazil,95", brazil.getStatement());
    }

    @Test
    public void testPurchaseSuppliesWholeStockIfDemandIsMoreThanTheStock() {
        int supply = brazil.purchase(105);
        assertEquals(100,supply);
        assertEquals("Brazil,0", brazil.getStatement());
    }

    @Test
    public void testGetStatementGetsTheStatementContainingCountryNameAndStoc() {
        assertEquals("Brazil,100", brazil.getStatement());
        brazil.purchase(20);
        assertEquals("Brazil,80", brazil.getStatement());
    }
}