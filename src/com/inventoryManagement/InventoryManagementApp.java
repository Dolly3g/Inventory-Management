package com.inventoryManagement;

public class InventoryManagementApp {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addStore(new Store("Brazil"));
        inventoryManager.addStore(new Store("Argentina"));
//        InventoryManager inventoryManager = new InventoryManager(onlineStore);
//        String statement = inventoryManager.placeOrder(args[0], new Quantity(Integer.parseInt(args[1])));
//        System.out.println(statement);
    }
}
