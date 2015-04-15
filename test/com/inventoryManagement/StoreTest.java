package com.inventoryManagement;

import org.junit.Test;

import static org.junit.Assert.*;

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
    @Test
    public void testPurchaseGivesSuccessStatusIfPurchaseIsSuccessfullyDone(){
        Store store = new Store("Brazil", 100, 100);
        assertTrue(store.purchase(5));
    }
    @Test
    public void testPurchaseReducesCurrentStockIfPurchaseIsSuccessful(){
        Store store = new Store("Brazil", 100, 100);
        store.purchase(5);
        assertEquals(95, store.getStock());
    }
    @Test
    public void testPurchaseGivesFailureStatusIfPurchaseIsUnsuccessful(){
        Store store = new Store("Brazil", 100, 100);
        assertFalse(store.purchase(105));
    }

    @Test
    public void testPurchaseDoesnotReduceTheStockIfPurchaseIsUnsuccessful(){
        Store store = new Store("Brazil", 100, 100);
        store.purchase(105);
        assertEquals(100, store.getStock());

    }
}