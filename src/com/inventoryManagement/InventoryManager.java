package com.inventoryManagement;

public class InventoryManager {

    // can be refactored as a list of manageable things
    private OnlineStore onlineStore;

    //made manage and not constructor because in future, inventoryManager might manage more things
    public void manage(OnlineStore onlineStore) {
        this.onlineStore = onlineStore;
    }

    public String placeOrder(String country, int numberOfItems) {
        try {
            return Integer.toString(onlineStore.getSalePrice(country, numberOfItems));
        } catch (StoreNotFoundException e) {
            return e.getMessage();
        }
    }
}
