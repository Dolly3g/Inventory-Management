package com.inventoryManagement;

import java.util.Map;

public class StoreStatement {

    private String country;
    private Map<Product, Quantity> products;

    @Override
    public String toString() {
        String statement = "";
        statement += country;
        statement += System.lineSeparator();
        for (Product product : products.keySet()) {
            statement += product;
            statement += " : "+ products.get(product);
        }
        return statement;
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
