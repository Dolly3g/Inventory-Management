package com.inventoryManagement;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private final int TRANSPORT_COST_PER_BATCH = 400;
    private final int BATCH_SIZE = 10;
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

    public MainStatement order(Product product, String country, Quantity demand) throws StoreNotFoundException {
        Store store = searchStoreByCountry(country);
        Price totalCost = null;
        MainStatement statement;
        if(!isStockAvailable(product, demand)){
            statement = new MainStatement(stores, totalCost);
            return statement;
        }

        totalCost = getTotalCost(product, store, demand);
        statement = new MainStatement(stores, totalCost);
        return statement;
    }

    private Price getTotalCost(Product product, Store store, Quantity demand) {
        Quantity totalSupply = store.purchase(product, demand);
        Price totalCost = store.calculateSalePriceFor(product, totalSupply);
        for (Store s : stores) {
            ;
            if(totalSupply == demand)break;
            Product productByName = s.getProductByName(product.getName());
            Quantity currentSupply = s.purchase(productByName, demand.reduce(totalSupply));
            Price transportationCost = getTransportationCost(currentSupply);
            Price price = s.calculateSalePriceFor(productByName, currentSupply);
            totalSupply.increaseBy(currentSupply);
            totalCost.increaseBy(transportationCost);
            totalCost.increaseBy(price);
        }
        return totalCost;
    }

    private Price getTransportationCost(Quantity currentSupply) {
        return new Price(getNumberOfBatches(currentSupply) * TRANSPORT_COST_PER_BATCH);
    }

    private int getNumberOfBatches(Quantity currentSupply) {
        return (currentSupply.toInt() % BATCH_SIZE + currentSupply.toInt())/ BATCH_SIZE;
    }

    public boolean isStockAvailable(Product product, Quantity numberOfItems) {
        Quantity totalStock = getTotalStock(product);
        return numberOfItems.compare(totalStock) <= 0 ;
    }

    private Quantity getTotalStock(Product product) {
        Quantity itemsFound = new Quantity(0);
        for (Store store : stores) {
            Quantity stock = store.getStockOf(product.getName());
            itemsFound.increaseBy(stock);
        }
        return itemsFound;
    }
}
