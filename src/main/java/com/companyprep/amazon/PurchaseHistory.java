package com.companyprep.amazon;

import java.sql.Time;
import java.util.Map;
import java.util.TreeMap;

/**
 * There are a bunch of time-continuous purchase records (id, timestamp, userId, productId), find out the three products
 * that are most frequently purchased by the same user in a row
 *
 */
public class PurchaseHistory {

	public static void main(String[] args) {

	}

	void find() {
		Map<User, TreeMap<Time, Product>> map1 = null;
		Map<Product, Integer> map2 = null; // When adding to map1, if User exists, check the product bought last time,
											// and last last time, if same, then add to map2, and visited
		Map<Product, User> visited = null; // This user keeps buying this product, but we should not duplicate the count
											// in map2

	}
}

class User {

}

class Product {

}
