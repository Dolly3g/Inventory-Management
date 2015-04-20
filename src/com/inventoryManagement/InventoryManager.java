package com.inventoryManagement;

import java.util.Map;

public class InventoryManager {
    private OnlineStore onlineStore;
    public InventoryManager(OnlineStore onlineStore) {
        this.onlineStore = onlineStore;
    }

    public String placeOrder(String country, Quantity numberOfItems) {
        try {
            MainStatement statement = onlineStore.order(new Product("iPod", new Price(100)), country, numberOfItems);
            return formatStatement(statement);
        } catch (StoreNotFoundException e) {
            return e.getMessage();
        }
    }

    private String formatStatement(MainStatement statement) {
        StringBuilder result = new StringBuilder();
//        for (String key : statement.keySet()) {
//            result.append(key).append(":");
//            result.append(statement.get(key));
//            result.append(System.lineSeparator());
//        }
//        return (statement.get("cost") == null ? result.append("Out of Stock!") : result).toString();
        return "";
    }
}
