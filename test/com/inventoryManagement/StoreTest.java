package com.inventoryManagement;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StoreTest {
    @Test
    public void testIsBasedInTellsThatTheStoreIsBasedInBrazil() {
        Store store = new Store("Brazil", 100, 100);
        assertTrue(store.isBasedIn("Brazil"));
    }

    @Test
    public void testIsBasedInTellsThatTheStoreIsNotBasedInBrazil() {
        Store store = new Store("Brazil", 100, 100);
        assertFalse(store.isBasedIn("Argentina"));
    }

    @Test
    public void testCalculateSalePriceForGivesCostOf5IpodsOfBrazil() {
        Store store = new Store("Brazil", 100, 100);
        assertEquals(500, store.calculateSalePriceFor(5));
    }

    @Test
    public void testCalculateSalePriceForGivesCostOf5IpodsOfArgentina() {
        Store store = new Store("Argentina", 100, 50);
        assertEquals(250, store.calculateSalePriceFor(5));
    }

}