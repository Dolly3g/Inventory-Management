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

    public int purchase(int numberOfItems) {
        if (stock > numberOfItems) {
            stock = stock - numberOfItems;
            return numberOfItems;
        }
        int previousStock = stock;
        emptyStock();
        return previousStock;
    }

    private void emptyStock() {
        stock = 0;
    }


    public String getStatement() {
        return country + "," + stock;
    }

    public int getStock() {
        return stock;
    }
}
