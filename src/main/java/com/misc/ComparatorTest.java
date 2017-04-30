package com.misc;

import java.util.Comparator;

public class ComparatorTest implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		if (o1.getName() != o2.getName())
			return -1;
		else if (o1.getName() == o2.getName())
			return 0;
		else
			return 1;
	}

}
