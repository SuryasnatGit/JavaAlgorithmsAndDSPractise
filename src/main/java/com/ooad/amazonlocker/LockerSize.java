package com.ooad.amazonlocker;

public enum LockerSize {

	SMALL(10), MEDIUM(20), LARGE(50), INVALID(-1);

	private int value;

	private LockerSize(int value) {
		this.value = value;
	}

	public int getSize() {
		return value;
	}
}
