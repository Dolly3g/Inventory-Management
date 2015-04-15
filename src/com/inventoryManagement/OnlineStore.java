package com.inventoryManagement;

import java.util.ArrayList;
import java.util.List;

public class OnlineStore {
    private final List<Store> stores = new ArrayList<>();

    public int getSalePrice(String country, int numberOfItems) throws StoreNotFoundException {
        Store store = searchStoreByCountry(country);
        if (store == null)
            throw new StoreNotFoundException("Company doesn't provide services in " + country);
        return store.calculateSalePriceFor(numberOfItems);
    }

    private Store searchStoreByCountry(String country) {
        for (Store store : stores) {
            if (store.isBasedIn(country))
                return store;
        }
        return null;
    }

    public boolean addStore(Store store) {
        if (stores.contains(store)) return false;
        stores.add(store);
        return true;
    }
}
