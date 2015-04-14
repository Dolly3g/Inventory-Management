package com.inventoryManagement;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryManagementTest {
    @Test
    public void testInventoryManagementGivesSalePriceOf5IPodsOfBrazil(){
        InventoryManagement inventoryManagement = new InventoryManagement();
        int salePrice = inventoryManagement.getSalePrice("Brazil",5);
        assertEquals(500, salePrice);

    }
}