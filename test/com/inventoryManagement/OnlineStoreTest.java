package com.inventoryManagement;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
    public void testOrderReturnsStatementIfPurchaseIsSuccessful() throws StoreNotFoundException {
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);
        Map<String, Integer> statement = onlineStore.order("Brazil", 5);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("cost",500);
        expected.put("Argentina",100);
        expected.put("Brazil",95);
        assertTrue(expected.equals(statement));
    }

}