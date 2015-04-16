package com.inventoryManagement;

public class Store {
    private String country;
    private int stock;
    private int price;

    public Store(String country, int stock, int price) {
        this.country = country;
        this.stock = stock;
        this.price = price;
    }

    public boolean isBasedIn(String country) {
        return this.country.equals(country);
    }

    @Override
    public boolean equals(Object object) {
        Store store = (Store) object;
        return country.equals(store.country) && stock == store.stock && price == store.price;
    }

    public int calculateSalePriceFor(int numberOfItems) {
        return numberOfItems * price;
    }

    public boolean purchase(int numberOfItems) {
        if(numberOfItems > 100)
            return false;
        stock = stock - numberOfItems;
        return true;
    }

    public int getStock() {
        return stock;
    }

    public String getStatement() {
        return country + "," + stock;
    }
}
