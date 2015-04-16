package com.inventoryManagement;

import javax.naming.InsufficientResourcesException;
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
        Map<String,Integer> statement = new HashMap<>();
        Store countryStore = searchStoreByCountry(country);

        if(countryStore.purchase(numberOfItems)){
            statement.put("cost",countryStore.calculateSalePriceFor(numberOfItems));
        }

        for (Store store : stores) {
            String[] storeStatement = store.getStatement().split(",");
            statement.put(storeStatement[0], Integer.valueOf(storeStatement[1]));
        }

        return statement;
    }
}
