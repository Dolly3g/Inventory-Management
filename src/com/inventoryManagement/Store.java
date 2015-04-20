package com.inventoryManagement;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private String country;
    private Map<Product, Quantity> stocks = new HashMap<>();

    public Store(String country) {
        this.country = country;
    }

    public boolean isBasedIn(String country) {
        return this.country.equals(country);
    }

    @Override
    public boolean equals(Object object) {
        Store store = (Store) object;
        return country.equals(store.country) && stocks.equals(store.stocks);
    }

    public Price calculateSalePriceFor(Product product, Quantity quantity) {
        int salePrice = quantity.multiply(product.getPrice());
        return new Price(salePrice);
    }

    public Quantity purchase(Product product, Quantity quantity) {
        Quantity stock = stocks.get(product);

        if (stock.compare(quantity) == 1) {
            updateStock(product, stock.reduce(quantity));
            return quantity;
        }
        Quantity previousStock = stock;
        emptyStock(product);
        return previousStock;
    }

    private void updateStock(Product product, Quantity newQuantity) {
        stocks.put(product, newQuantity);
    }

    private void emptyStock(Product product) {
        updateStock(product, new Quantity(0));
    }


    public String getStatement() {
        StringBuilder statement = new StringBuilder(country + "\n");
        for (Product product : stocks.keySet()) {
            statement.append(product.getName());
            statement.append(":");
            statement.append(stocks.get(product) + "\n");
        }
        return statement.toString();
    }

    public boolean addStock(Product product, Quantity quantity) {
        if (stocks.containsKey(product))
            return false;
        stocks.put(product, quantity);
        return true;
    }

    @Override
    public String toString() {
        return "Store{" +
                "country='" + country + '\'' +
                ", stocks=" + stocks +
                '}';
    }

    public Quantity getStockOf(String productName) {
        for (Product product : stocks.keySet()) {
            if(productName.equals(product.getName()))
                return stocks.get(product);
        }
        return null;
    }
}
