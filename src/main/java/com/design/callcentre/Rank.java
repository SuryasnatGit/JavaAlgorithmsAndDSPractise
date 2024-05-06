package com.design.callcentre;

public enum Rank {
	RESPONDENT(1), MANAGER(2), DIRECTOR(3);

	private int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getRank() {
		return value;
	}
}
