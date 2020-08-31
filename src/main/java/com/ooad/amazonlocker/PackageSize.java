package com.ooad.amazonlocker;

public enum PackageSize {

	SMALL(5), MEDIUM(15), LARGE(30);

	private int value;

	private PackageSize(int value) {
		this.value = value;
	}

	public int getSize() {
		return value;
	}
}
