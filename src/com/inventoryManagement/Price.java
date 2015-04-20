package com.inventoryManagement;

/**
 * Created by dollyg on 20/04/15.
 */
public class Price {
    private int price;

    public Price(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price1 = (Price) o;

        return price == price1.price;

    }

    @Override
    public int hashCode() {
        return price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                '}';
    }

    public int toInt() {
        return price;
    }

    public void increaseBy(Price price) {
        this.price += price.price;
    }
}
