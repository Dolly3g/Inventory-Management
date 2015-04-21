package com.inventoryManagement;

import java.util.List;

public class MainStatement {
    private List<Store> stores;
    private Price cost;

    public MainStatement(List<Store> stores, Price cost) {
        this.stores = stores;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainStatement that = (MainStatement) o;

        if (stores != null ? !stores.equals(that.stores) : that.stores != null) return false;
        return !(cost != null ? !cost.equals(that.cost) : that.cost != null);

    }

    @Override
    public int hashCode() {
        int result = stores != null ? stores.hashCode() : 0;
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String statement = "";
        for (Store store : stores) {
            statement += store.getStatement();
            statement += System.lineSeparator();
        }
        if(cost != null)
            statement += cost;
        return statement;
    }
}
