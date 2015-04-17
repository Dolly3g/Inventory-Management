package com.inventoryManagement;

import java.util.Map;

public class InventoryManager {
    private OnlineStore onlineStore;
    public InventoryManager(OnlineStore onlineStore) {
        this.onlineStore = onlineStore;
    }

    public String placeOrder(String country, int numberOfItems) {
        try {
            Map<String, Integer> statement = onlineStore.order(country, numberOfItems);
            return formatStatement(statement);
        } catch (StoreNotFoundException e) {
            return e.getMessage();
        }
    }

    private String formatStatement(Map<String, Integer> statement) {
        StringBuilder result = new StringBuilder();
        for (String key : statement.keySet()) {
            result.append(key).append(":");
            result.append(statement.get(key));
            result.append(System.lineSeparator());
        }
        return (statement.get("cost") == null ? result.append("Out of Stock!") : result).toString();
    }
}
