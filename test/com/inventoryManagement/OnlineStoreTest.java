package com.inventoryManagement;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;
import static org.junit.Assert.*;

public class OnlineStoreTest {
    private Store argentina;
    private Store brazil;
    private OnlineStore onlineStore;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        onlineStore = new OnlineStore();
        brazil = new Store("Brazil");
        argentina = new Store("Argentina");
    }

    @Test
    public void testAddStoreGivesSuccessStatusIfNewStoreIsAdded() {
        boolean status = onlineStore.addStore(brazil);
        assertTrue(status);
    }
//
    @Test
    public void testAddStoreGivesFailureStatusIfExistingStoreIsAddedAgain() {
        onlineStore.addStore(brazil);
        Store brazil2 = new Store("Brazil");
        boolean status = onlineStore.addStore(brazil2);
        assertFalse(status);
    }
//
    @Test
    public void testOrderReturnsStatementWithCostIfPurchaseIsSuccessful() throws StoreNotFoundException {
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);
        Product brazilIPod = new Product("iPod", new Price(100));
        Product argentinaIPod = new Product("iPod", new Price(50));
        brazil.addStock(brazilIPod,new Quantity(100));
        argentina.addStock(argentinaIPod,new Quantity(100));
        Map<String, Price> statement = onlineStore.order(brazilIPod, "Brazil", new Quantity(5));
        Map<String, Integer> expected = new HashMap<>();
        expected.put("cost", 500);
        expected.put("Argentina", 100);
        expected.put("Brazil", 95);
        System.out.println(expected);
        System.out.println(statement);
        assertTrue(expected.equals(statement));
    }

//    @Test
//    public void testOrderReturnsStatementWithoutCostIfPurchaseIsUnsuccessful() throws StoreNotFoundException {
//        onlineStore.addStore(brazil);
//        onlineStore.addStore(argentina);
//        Map<String, Integer> statement = onlineStore.order("Brazil", 225);
//        Map<String, Integer> expected = new HashMap<>();
//        expected.put("Argentina", 100);
//        expected.put("Brazil", 100);
//        assertTrue(expected.equals(statement));
//    }
//
//    @Test
//    public void testOrderThrowsExceptionIfStoreDoesNotExist() throws StoreNotFoundException {
//        onlineStore.addStore(brazil);
//        onlineStore.addStore(argentina);
//        exception.expect(StoreNotFoundException.class);
//        exception.expectMessage("Company doesn't provide services in Dubai");
//        onlineStore.order("Dubai", 5);
//    }
//
//    @Test
//    public void testIsStockAvailableReturnsTrueIfStockIsAvailable() throws StoreNotFoundException {
//        onlineStore.addStore(brazil);
//        onlineStore.addStore(argentina);
//        assertTrue(onlineStore.isStockAvailable(120));
//        assertTrue(onlineStore.isStockAvailable(200));
//    }
//    @Test
//    public void testIsStockAvailableReturnsFalseIfStockIsUnavailable() throws StoreNotFoundException {
//        onlineStore.addStore(brazil);
//        onlineStore.addStore(argentina);
//        assertFalse(onlineStore.isStockAvailable(201));
//    }
//
//    @Test
//    public void testOrderCanOrderTheStockFromOtherCountries() throws StoreNotFoundException {
//        onlineStore.addStore(brazil);
//        onlineStore.addStore(argentina);
//        Map<String, Integer> statement = onlineStore.order("Argentina", 120);
//        Map<String, Integer> expected = new HashMap<>();
//        expected.put("cost", 7800);
//        expected.put("Argentina", 0);
//        expected.put("Brazil", 80);
//        assertTrue(expected.equals(statement));
//    }
//
//    @Test
//    public void testOrderCanOrderTheStockFromMultipleCountries() throws StoreNotFoundException {
//        onlineStore.addStore(brazil);
//        onlineStore.addStore(argentina);
//        onlineStore.addStore(new Store("India"));
//        Map<String, Integer> statement = onlineStore.order("Argentina", 250);
//        Map<String, Integer> expected = new HashMap<>();
//        expected.put("cost", 26000);
//        expected.put("Argentina", 0);
//        expected.put("Brazil", 0);
//        expected.put("India", 50);
//        assertTrue(expected.equals(statement));
//    }
}