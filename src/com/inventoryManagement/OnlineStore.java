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

    public Map<String, Integer> order(String country, int numberOfItems) throws StoreNotFoundException {
        Store countryStore = searchStoreByCountry(country);
        Map<String, Integer> statement = new HashMap<>();

        if (countryStore.purchase(numberOfItems))
            statement.put("cost", countryStore.calculateSalePriceFor(numberOfItems));

        populateStatement(statement);
        return statement;
    }

    private void populateStatement(Map<String, Integer> statement) {
        for (Store store : stores) {
            String[] storeStatement = store.getStatement().split(",");
            statement.put(storeStatement[0], Integer.valueOf(storeStatement[1]));
        }
    }
}
