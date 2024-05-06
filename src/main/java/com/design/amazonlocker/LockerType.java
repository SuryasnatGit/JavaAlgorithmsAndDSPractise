package com.design.amazonlocker;

public enum LockerType {

	SMALL(10), MEDIUM(20), LARGE(50), INVALID(-1);

	private int value;

	private LockerType(int value) {
		this.value = value;
	}

	public int getSize() {
		return value;
	}
}
