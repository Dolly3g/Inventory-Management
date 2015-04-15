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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        if (stock != store.stock) return false;
        if (price != store.price) return false;
        return !(country != null ? !country.equals(store.country) : store.country != null);

    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + stock;
        result = 31 * result + price;
        return result;
    }

    public boolean isBasedIn(String country) {
        return this.country.equals(country);
    }

    public int calculateSalePriceFor(int numberOfItems) {
        return numberOfItems * price;
    }
}
