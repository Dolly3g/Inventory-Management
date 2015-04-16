package com.inventoryManagement;

import java.util.Map;

public class InventoryManager {

    // can be refactored as a list of manageable things
    private OnlineStore onlineStore;

    //made manage and not constructor because in future, inventoryManager might manage more things
    public void manage(OnlineStore onlineStore) {
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
        if(statement.get("cost") == null){
            result.append("Out of Stock!");
        }
        return result.toString();
    }
}
