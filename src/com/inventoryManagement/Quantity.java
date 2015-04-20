package com.inventoryManagement;

public class Quantity {
    private int quantity;

    public Quantity(int quantity) {
        this.quantity = quantity;
    }

    public int multiply(int price) {
        return quantity * price;
    }

    @Override
    public String toString() {
        return String.valueOf(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quantity quantity1 = (Quantity) o;

        return quantity == quantity1.quantity;

    }

    @Override
    public int hashCode() {
        return quantity;
    }

    public int compare(Quantity quantity) {
        if(this.quantity == quantity.quantity) {
            return 0;
        }
        if(this.quantity > quantity.quantity)
            return 1;
        return -1;
    }

    public Quantity reduce(Quantity quantity) {
        return new Quantity(this.quantity - quantity.quantity);
    }

    public void increaseBy(Quantity quantity) {
        this.quantity += quantity.quantity;
    }

    public int toInt() {
        return quantity;
    }
}
