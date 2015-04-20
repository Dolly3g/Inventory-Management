package com.inventoryManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnlineStore {
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

    public Map<String, Price> order(Product product, String country, Quantity demand) throws StoreNotFoundException {
        Store store = searchStoreByCountry(country);
        Map<String, Price> statement = new HashMap<>();
        if(!isStockAvailable(product, demand)){
            populateStatement(statement);
            return statement;
        }

        Price totalCost = getTotalCost(product, store, demand);

        populateStatement(statement);
        statement.put("cost", totalCost);
        return statement;
    }

    private Price getTotalCost(Product product, Store store, Quantity demand) {
        Quantity totalSupply = store.purchase(product, demand);
        Price totalCost = store.calculateSalePriceFor(product, totalSupply);
        for (Store s : stores) {
            if(totalSupply == demand)break;
            Quantity currentSupply = s.purchase(product, demand.reduce(totalSupply));
            totalSupply = totalSupply.increaseBy(currentSupply);
            totalCost = totalCost.increaseBy(getTransportationCost(currentSupply));
            totalCost = totalCost.increaseBy(s.calculateSalePriceFor(product, currentSupply));
        }
        return totalCost;
    }

    private Price getTransportationCost(Quantity currentSupply) {
        return new Price(getNumberOfBatches(currentSupply) * TRANSPORT_COST_PER_BATCH);
    }

    private int getNumberOfBatches(Quantity currentSupply) {
        return 0;
//        return (currentSupply % BATCH_SIZE + currentSupply )/ BATCH_SIZE;
    }

    private void populateStatement(Map<String, Price> statement) {
        for (Store store : stores) {
            String storeStatement = store.getStatement();
            String[] lineSplit = storeStatement.split("\n");
            statement.put(lineSplit[0], new Price(Integer.valueOf(lineSplit[1].split(":")[1])));
        }
    }

    public boolean isStockAvailable(Product product, Quantity numberOfItems) {
        return numberOfItems.compare(getTotalStock(product)) <= 0 ;
    }

    private Quantity getTotalStock(Product product) {
        Quantity itemsFound = new Quantity(0);
        for (Store store : stores) {
            Quantity stock = store.getStockOf("iPod");
            itemsFound.increaseBy(stock);
        }
        return itemsFound;
    }
}
