

package com.oops.vendingmachine;


/**
 * Vending Machine throws this exception to indicate that it doesn't have sufficient change to complete this request.
 * 
 * @author ctsuser1
 */
public class NotSufficientChangeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    public NotSufficientChangeException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
