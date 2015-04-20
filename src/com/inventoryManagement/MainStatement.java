package com.inventoryManagement;

import java.util.List;

/**
 * Created by dollyg on 20/04/15.
 */
public class MainStatement {
    private List<Store> stores;
    private Price price;

    public MainStatement(List<Store> stores, Price price) {
        this.stores = stores;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainStatement that = (MainStatement) o;

        if (stores != null ? !stores.equals(that.stores) : that.stores != null) return false;
        return !(price != null ? !price.equals(that.price) : that.price != null);

    }

    @Override
    public int hashCode() {
        int result = stores != null ? stores.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MainStatement{" +
                "stores=" + stores +
                ", price=" + price +
                '}';
    }
}
