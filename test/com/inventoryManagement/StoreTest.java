package com.inventoryManagement;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoreTest {

    private Store brazil;
    private Store argentina;
    private Product brazilIPod;
    private Product argentinaIPod;

    @Before
    public void setUp() throws Exception {
        brazil = new Store("Brazil");
        argentina = new Store("Argentina");
        brazilIPod = new Product("iPod", new Price(100));
        argentinaIPod = new Product("iPod", new Price(50));
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
    public void testAddStockAddsTheProductToTheStore() {
        assertTrue(brazil.addStock(brazilIPod, new Quantity(100)));

    }

    @Test
    public void testAddStockAddsTheProductToTheStoreOnlyOnce() {
        brazil.addStock(brazilIPod, new Quantity(100));
        assertFalse(brazil.addStock(brazilIPod, new Quantity(100)));
    }

    @Test
    public void testCalculateSalePriceForGivesCostOf5IpodsOfBrazil() {
        Price actual = brazil.calculateSalePriceFor(brazilIPod, new Quantity(5));
        assertEquals(new Price(500), actual);
    }

    @Test
    public void testCalculateSalePriceForGivesCostOf5IpodsOfArgentina() {
        assertEquals(new Price(250), argentina.calculateSalePriceFor(argentinaIPod, new Quantity(5)));
    }

    @Test
    public void testPurchaseSuppliesTheDemandedStock() {
        brazil.addStock(brazilIPod, new Quantity(100));
        Quantity supply = brazil.purchase(brazilIPod, new Quantity(5));
        assertEquals(new Quantity(5), supply);
        assertEquals("Brazil\niPod:95\n", brazil.getStatement());
    }

    @Test
    public void testPurchaseSuppliesWholeStockIfDemandIsMoreThanTheStock() {
        brazil.addStock(brazilIPod, new Quantity(100));
        Quantity supply = brazil.purchase(brazilIPod, new Quantity(105));
        assertEquals(new Quantity(100), supply);
        assertEquals("Brazil\niPod:0\n", brazil.getStatement());
    }

    @Test
    public void testGetStatementGetsTheStatementContainingCountryNameAndStoc() {
        brazil.addStock(brazilIPod, new Quantity(100));
        assertEquals("Brazil\niPod:100\n", brazil.getStatement());
        brazil.purchase(brazilIPod, new Quantity(20));
        assertEquals("Brazil\niPod:80\n", brazil.getStatement());
    }
}