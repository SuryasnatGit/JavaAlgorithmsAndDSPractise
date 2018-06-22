package com.ctci.stacknqueue;

public interface IAnimalShelter {

	public void enqueue(AnimalType animal);

	public Animal dequeue(AnimalType type);

	public Animal dequeueAny();
}
