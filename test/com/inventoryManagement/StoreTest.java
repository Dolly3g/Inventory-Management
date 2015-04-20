package com.inventoryManagement;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
    }

    @Test
    public void testGetStatementGetsTheStatementAfterPurchase() {
        brazil.addStock(brazilIPod, new Quantity(100));
        Quantity supply = brazil.purchase(brazilIPod, new Quantity(5));
        assertEquals(new Quantity(5), supply);
        Map<Product, Quantity> products = new HashMap<>();
        products.put(brazilIPod, new Quantity(95));
        StoreStatement expected = new StoreStatement("Brazil", products);
        assertEquals(expected, brazil.getStatement());
    }

    @Test
    public void testPurchaseSuppliesWholeStockIfDemandIsMoreThanTheStock() {
        brazil.addStock(brazilIPod, new Quantity(100));
        Quantity supply = brazil.purchase(brazilIPod, new Quantity(105));
        assertEquals(new Quantity(100), supply);
        Map<Product, Quantity> products = new HashMap<>();
        products.put(brazilIPod, new Quantity(0));
        StoreStatement expected = new StoreStatement("Brazil", products);
        assertEquals(expected, brazil.getStatement());
    }
}