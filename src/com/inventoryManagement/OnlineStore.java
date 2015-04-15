package com.inventoryManagement;

import java.util.ArrayList;
import java.util.List;

public class OnlineStore {
    List<Store> stores = new ArrayList<Store>();

    public int getSalePrice(String country, int numberOfItems) {
        Store store = searchStoreByCountry(country);
        return store.calculateSalePriceFor(numberOfItems);
    }

    private Store searchStoreByCountry(String country) {
        for (Store store : stores) {
            boolean isBasedIn = store.isBasedIn(country);
            if (isBasedIn)
                return store;
        }
        return null;
    }

    public boolean addStore(Store store) {
        if (!stores.contains(store)) {
            stores.add(store);
            return true;
        }
        return false;
    }
}
