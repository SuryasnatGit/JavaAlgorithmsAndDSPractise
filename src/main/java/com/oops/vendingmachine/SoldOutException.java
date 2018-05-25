

package com.oops.vendingmachine;


/**
 * Vending Machine throws this exception if the user request for a product which is sold out.
 * 
 * @author ctsuser1
 */
public class SoldOutException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    public SoldOutException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
