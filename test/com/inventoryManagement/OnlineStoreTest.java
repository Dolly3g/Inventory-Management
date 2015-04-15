package com.inventoryManagement;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OnlineStoreTest {

    @Test
    public void testOnlineStoreGivesSalePriceOf5IPodsOfBrazil(){
        OnlineStore onlineStore = new OnlineStore();
        Store brazil = new Store("Brazil", 100, 100);
        Store argentina = new Store("Argentina", 100, 50);
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);
        int salePrice = onlineStore.getSalePrice("Brazil", 5);
        assertEquals(500, salePrice);
    }

    @Test
    public void testOnlineStoreGivesSalePriceOf5IPodsOfArgentina(){
        OnlineStore onlineStore = new OnlineStore();
        Store brazil = new Store("Brazil", 100, 100);
        Store argentina = new Store("Argentina", 100, 50);
        onlineStore.addStore(brazil);
        onlineStore.addStore(argentina);
        int salePrice = onlineStore.getSalePrice("Argentina", 5);
        assertEquals(250, salePrice);
    }

    @Test
    public void testOnlineStoreAddsNewStore (){
        OnlineStore onlineStore = new OnlineStore();
        Store store = new Store("Brazil", 100, 100);
        boolean status = onlineStore.addStore(store);
        assertTrue(status);
    }
    @Test
    public void testOnlineStoreAddsNewStoreOnlyOnce (){
        OnlineStore onlineStore = new OnlineStore();
        Store store1 = new Store("Brazil", 100, 100);
        Store store2 = new Store("Brazil", 100, 100);
        onlineStore.addStore(store1);
        boolean status = onlineStore.addStore(store2);
        assertFalse(status);
    }
}