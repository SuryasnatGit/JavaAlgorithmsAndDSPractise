

package com.oops.vendingmachine;


/**
 * Java Enum to represent Item served by Vending Machine
 * 
 * @author ctsuser1
 */
public enum Item {

    COKE("Coke", 25), PEPSI("Pepsi", 10), SODA("Soda", 15);

    private String name;
    private int price;

    private Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
