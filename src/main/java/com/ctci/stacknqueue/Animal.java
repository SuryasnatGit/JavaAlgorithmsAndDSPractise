package com.ctci.stacknqueue;

public class Animal {

	private int id;
	private AnimalType type;

	public Animal(int id, AnimalType type) {
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public AnimalType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", type=" + type + "]";
	}
}
