package com.inventoryManagement;

import java.util.ArrayList;
import java.util.List;

public class OnlineStore {
    private final List<Store> stores = new ArrayList<>();

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

    public int order(String country, int numberOfItems) throws StoreNotFoundException {
        Store store = searchStoreByCountry(country);
        if (store == null)
            throw new StoreNotFoundException("Company doesn't provide services in " + country);
        if(store.purchase(numberOfItems)){
            return store.calculateSalePriceFor(numberOfItems);
        }
        return -1;
    }
}
