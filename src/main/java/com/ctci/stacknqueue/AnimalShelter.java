package com.ctci.stacknqueue;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

/**
 * Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly"first
 * in, first out" basis. People must adopt either the "oldest" (based on arrival time) of all
 * animals at the shelter, or they can select whether they would prefer a dog or a cat (and will
 * receive the oldest animal of that type). They cannot select which specific animal they would
 * like. Create the data structures to maintain this system and implement operations such as
 * enqueue, dequeueAny, dequeueDog, and dequeueCat. You may use the built-in Linked List data
 * structure
 * 
 * @author surya
 *
 * @param <E>
 */
public class AnimalShelter implements IAnimalShelter {

	// synchronized container to maintain the collection of different animals
	private final Map<AnimalType, Queue<Animal>> animalQueuesMap = Collections
			.synchronizedMap(new EnumMap<>(AnimalType.class));

	@Override
	public void enqueue(AnimalType animalType) {
		Queue<Animal> queue = animalQueuesMap.get(animalType);
		if (queue == null) {
			queue = new LinkedList<>();
			animalQueuesMap.put(animalType, queue);
		}
		int id = new Random().nextInt();
		queue.add(new Animal(id, animalType));
	}

	@Override
	public Animal dequeue(AnimalType type) {
		Queue<Animal> queue = animalQueuesMap.get(type);
		return (queue == null || queue.isEmpty() ? null : queue.poll());
	}

	@Override
	public Animal dequeueAny() {
		int min = Integer.MAX_VALUE;
		Queue<Animal> next = null;
		for (Queue<Animal> queue : animalQueuesMap.values()) {
			Animal animal = queue.peek();
			if (animal == null)
				continue;
			int id = animal.getId();
			if (id < min) {
				min = id;
				next = queue;
			}
		}
		return (next == null || next.isEmpty()) ? null : next.poll();
	}

}
