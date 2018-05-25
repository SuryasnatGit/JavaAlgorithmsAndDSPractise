

package com.oops.vendingmachine;


import java.util.HashMap;
import java.util.Map;


/**
 * An Adapter over Map to create Inventory to hold cash and Items inside Vending Machine
 * 
 * @author ctsuser1
 * @param <T>
 */
public class Inventory<T> {
    private Map<T, Integer> inventory = new HashMap<>();

    private int getQuantity(T item) {
        Integer value = inventory.get(item);
        return value == null ? 0 : value;
    }

    public void addItem(T item) {
        int count = inventory.get(item);
        inventory.put(item, ++count);
    }

    public void deductItem(T item) {
        if (hasItem(item)) {
            int count = inventory.get(item);
            inventory.put(item, --count);
        }
    }

    public boolean hasItem(T item) {
        return getQuantity(item) > 0;
    }

    public void clear() {
        inventory.clear();
    }

    public void put(T item, int quantity) {
        inventory.put(item, quantity);
    }
}
