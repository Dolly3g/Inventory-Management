package com.inventoryManagement;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OnlineStoreTest {

    private Store argentina;
    private Store brazil;
    private OnlineStore onlineStore;

    @Before
    public void setUp() throws Exception {
        onlineStore = new OnlineStore();
        brazil = new Store("Brazil", 100, 100);
        argentina = new Store("Argentina", 100, 50);

    }

    @Test
    public void testAddStoreGivesSuccessStatusIfNewStoreIsAdded() {
        boolean status = onlineStore.addStore(brazil);
        assertTrue(status);
    }

    @Test
    public void testAddStoreGivesFailureStatusIfExistingStoreIsAddedAgain() {
        onlineStore.addStore(brazil);
        Store brazil2 = new Store("Brazil", 100, 100);
        boolean status = onlineStore.addStore(brazil2);
        assertFalse(status);
    }

    @Test
    public void testOrderReturnsCostOfThePurchaseIfPurchaseIsSuccessful() throws StoreNotFoundException {
        onlineStore.addStore(brazil);
        assertEquals(500, onlineStore.order("Brazil", 5));
    }

}