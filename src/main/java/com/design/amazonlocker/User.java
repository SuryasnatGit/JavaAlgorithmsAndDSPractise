package com.ooad.amazonlocker;

import java.util.List;

public class User {

	private int userId;
	private String name;
	private Address address;
	private List<Order> orders;

	enum UserType {
		ADMIN, DELIVERY, NORMAL
	}

	class Address {
		String address1;
		int zipCode;
	}
}
