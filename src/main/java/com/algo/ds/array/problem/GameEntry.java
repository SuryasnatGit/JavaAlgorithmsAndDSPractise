package com.algo.ds.array.problem;

public class GameEntry {

	private String name;
	private int number;

	public GameEntry(String n, int num) {
		this.name = n;
		this.number = num;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return number;
	}

	@Override
	public String toString() {
		return "GameEntry [name=" + name + ", number=" + number + "]";
	}
}
