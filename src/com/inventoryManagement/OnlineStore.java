package com.inventoryManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnlineStore {
    private final List<Store> stores = new ArrayList<>();

    private Store searchStoreByCountry(String country) throws StoreNotFoundException {
        for (Store store : stores) {
            if (store.isBasedIn(country))
                return store;
        }
        throw new StoreNotFoundException("Company doesn't provide services in " + country);
    }

    public boolean addStore(Store store) {
        if (stores.contains(store)) return false;
        stores.add(store);
        return true;
    }

    // online store ordering...
    public Map<String, Integer> order(String country, int demand) throws StoreNotFoundException {
        Store store = searchStoreByCountry(country);
        Map<String, Integer> statement = new HashMap<>();

        if(!isStockAvailable(demand)){
            populateStatement(statement);
            return statement;
        }

        int totalSupply = store.purchase(demand);
        int totalCost = store.calculateSalePriceFor(totalSupply);
        for (Store s : stores) {
            if(totalSupply == demand)break;
            int currentSupply = s.purchase(demand - totalSupply);
            totalCost += s.calculateSalePriceFor(currentSupply);
        }

        populateStatement(statement);
        statement.put("cost",totalCost);

        return statement;
    }

    private void populateStatement(Map<String, Integer> statement) {
        for (Store store : stores) {
            String[] storeStatement = store.getStatement().split(",");
            statement.put(storeStatement[0], Integer.valueOf(storeStatement[1]));
        }
    }

    public boolean isStockAvailable(int numberOfItems) {
        return getTotalStock() >= numberOfItems;
    }

    private int getTotalStock() {
        int itemsFound = 0;
        for (Store store : stores) {
            itemsFound += store.getStock();
        }
        return itemsFound;
    }
}
