package com.ooad.amazonlocker;

public enum PackageType {

	SMALL(5), MEDIUM(15), LARGE(30);

	private int value;

	private PackageType(int value) {
		this.value = value;
	}

	public int getSize() {
		return value;
	}
}
