package com.inventoryManagement;

public class InventoryManagementApp {
    public static void main(String[] args) {
        OnlineStore onlineStore = new OnlineStore();
        onlineStore.addStore(new Store("Brazil", 100, 100));//..
        onlineStore.addStore(new Store("Argentina", 100, 50));
        InventoryManager inventoryManager = new InventoryManager(onlineStore);
        String statement = inventoryManager.placeOrder(args[0], Integer.parseInt(args[1]));//rename variable place order return??
        System.out.println(statement);
    }
}
