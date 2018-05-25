

package com.oops.vendingmachine;


import java.util.List;


/**
 * defines public api of vending machine
 * 
 * @author ctsuser1
 */
public interface VendingMachine {

    public long selectItemAndGetPrice(Item item);

    public void insertCoin(Coin coin);

    public List<Coin> refund();

    public Bucket<Item, List<Coin>> collectItemAndChange();

    public void reset();


}
