package com.inventoryManagement;

import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryManagerTest {
    @Test
    public void testPlaceOrderReturnsTheStatementOfTheTransaction () {
        OnlineStore onlineStore = new OnlineStore();
        onlineStore.addStore(new Store("Brazil",100,100));
        onlineStore.addStore(new Store("Argentina", 100, 50));
        InventoryManager inventoryManager = new InventoryManager(onlineStore);
        String statement = inventoryManager.placeOrder("Brazil", 50);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Argentina:100");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("Brazil:50");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("cost:5000");
        stringBuilder.append(System.lineSeparator());
        String expected = stringBuilder.toString();
        assertEquals(expected, statement);
    }
}