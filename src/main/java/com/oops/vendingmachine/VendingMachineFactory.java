

package com.oops.vendingmachine;


/**
 * A Factory class to create different kinds of Vending Machine
 * 
 * @author ctsuser1
 */
public class VendingMachineFactory {

    public static VendingMachine createVendingMachine() {
        return new VendingMachineImpl();
    }
}
