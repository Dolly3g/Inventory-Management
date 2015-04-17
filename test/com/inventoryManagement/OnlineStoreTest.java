package com.inventoryManagement;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OnlineStoreTest {
    private Store argentina;
    private Store brazil;
    private OnlineStore onlineStore;

    @Rule
    public ExpectedException exception = ExpectedException.none();

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
    public void testOrderReturnsStatementWithCostIfPurchaseIsSuccessful() throws StoreNotFoundException {
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);
        Map<String, Integer> statement = onlineStore.order("Brazil", 5);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("cost", 500);
        expected.put("Argentina", 100);
        expected.put("Brazil", 95);
        assertTrue(expected.equals(statement));
    }

    @Test
    public void testOrderReturnsStatementWithoutCostIfPurchaseIsUnsuccessful() throws StoreNotFoundException {
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);
        Map<String, Integer> statement = onlineStore.order("Brazil", 225);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Argentina", 100);
        expected.put("Brazil", 100);
        assertTrue(expected.equals(statement));
    }

    @Test
    public void testOrderThrowsExceptionIfStoreDoesNotExist() throws StoreNotFoundException {
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);
        exception.expect(StoreNotFoundException.class);
        exception.expectMessage("Company doesn't provide services in Dubai");
        onlineStore.order("Dubai", 5);
    }

    @Test
    public void testIsStockAvailableReturnsTrueIfStockIsAvailable() throws StoreNotFoundException {
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);
        assertTrue(onlineStore.isStockAvailable(120));
        assertTrue(onlineStore.isStockAvailable(200));
    }
    @Test
    public void testIsStockAvailableReturnsFalseIfStockIsUnavailable() throws StoreNotFoundException {
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);
        assertFalse(onlineStore.isStockAvailable(201));
    }

    @Test
    public void testOrderCanOrderTheStockFromOtherCountries() throws StoreNotFoundException {
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);
        Map<String, Integer> statement = onlineStore.order("Brazil", 101);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("cost", 10050);
        expected.put("Argentina", 99);
        expected.put("Brazil", 0);
        System.out.println(statement);
        System.out.println(expected);
        assertTrue(expected.equals(statement));
    }
}