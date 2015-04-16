package com.inventoryManagement;

public class InventoryManagementApp {
    public static void main(String[] args) {
        OnlineStore onlineStore = new OnlineStore();
        onlineStore.addStore(new Store("Brazil", 100, 100));
        onlineStore.addStore(new Store("Argentina", 100, 50));
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.manage(onlineStore);
        String statement = inventoryManager.placeOrder("Brazil", 500);
        System.out.println(statement);
    }
}
