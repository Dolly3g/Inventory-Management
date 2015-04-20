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
    Product brazilIPod;
    Product argentinaIPod;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    public OnlineStoreTest() {
    }

    @Before
    public void setUp() throws Exception {
        onlineStore = new OnlineStore();
        brazil = new Store("Brazil");
        argentina = new Store("Argentina");
        argentinaIPod = new Product("iPod", new Price(50));
        brazilIPod = new Product("iPod", new Price(100));
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);

        brazil.addStock(brazilIPod, new Quantity(100));
        argentina.addStock(argentinaIPod, new Quantity(100));
    }

    @Test
    public void testOrderReturnsStatementWithCostIfPurchaseIsSuccessful() throws StoreNotFoundException {
        MainStatement statement = onlineStore.order(brazilIPod, "Brazil", new Quantity(5));
        List<Store> stores = new ArrayList<>();
        stores.add(brazil);
        stores.add(argentina);
        MainStatement expected = new MainStatement(stores, new Price(500));
        assertTrue(expected.equals(statement));
    }

    @Test
    public void testOrderReturnsStatementWithoutCostIfPurchaseIsUnsuccessful() throws StoreNotFoundException {
        MainStatement statement = onlineStore.order(brazilIPod, "Brazil", new Quantity(225));
        List<Store> stores = new ArrayList<>();
        stores.add(brazil);
        stores.add(argentina);
        MainStatement expected = new MainStatement(stores, null);
        assertTrue(expected.equals(statement));
        assertTrue(true);
    }

    @Test
    public void testOrderThrowsExceptionIfStoreDoesNotExist() throws StoreNotFoundException {
        exception.expect(StoreNotFoundException.class);
        exception.expectMessage("Company doesn't provide services in Dubai");
        onlineStore.order(brazilIPod, "Dubai", new Quantity(5));
    }

    @Test
    public void testIsStockAvailableReturnsTrueIfStockIsAvailable() throws StoreNotFoundException {
        assertTrue(onlineStore.isStockAvailable(brazilIPod, new Quantity(120)));
        assertTrue(onlineStore.isStockAvailable(argentinaIPod, new Quantity(200)));
    }

    @Test
    public void testIsStockAvailableReturnsFalseIfStockIsUnavailable() throws StoreNotFoundException {
        assertFalse(onlineStore.isStockAvailable(brazilIPod, new Quantity(210)));
    }

    @Test
    public void testOrderCanOrderTheStockFromOtherCountries() throws StoreNotFoundException {
        MainStatement statement = onlineStore.order(argentinaIPod, "Argentina", new Quantity(120));
        List<Store> stores = new ArrayList<>();
        stores.add(brazil);
        stores.add(argentina);
        MainStatement expected = new MainStatement(stores, new Price(7800));
        assertTrue(expected.equals(statement));
    }

    @Test
    public void testOrderCanOrderTheStockFromMultipleCountries() throws StoreNotFoundException {
        Store india = new Store("India");
        onlineStore.addStore(india);
        Product indiaIPod = new Product("iPod",new Price(100));
        india.addStock(indiaIPod, new Quantity(100));
        MainStatement statement = onlineStore.order(argentinaIPod, "Argentina", new Quantity(250));
        List<Store> stores = new ArrayList<>();
        stores.add(brazil);
        stores.add(argentina);
        stores.add(india);
        MainStatement expected = new MainStatement(stores, new Price(26000));
        assertTrue(expected.equals(statement));
    }
}