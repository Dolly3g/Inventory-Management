package com.inventoryManagement;

import java.util.Map;

/**
 * Created by dollyg on 20/04/15.
 */
public class StoreStatement {

    private String country;
    private Map<Product, Quantity> products;

    @Override
    public String toString() {
        return "StoreStatement{" +
                "country='" + country + '\'' +
                ", products=" + products +
                '}';
    }

    public StoreStatement(String country, Map<Product, Quantity> products) {
        this.country = country;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreStatement that = (StoreStatement) o;

        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        return !(products != null ? !products.equals(that.products) : that.products != null);

    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }
}
