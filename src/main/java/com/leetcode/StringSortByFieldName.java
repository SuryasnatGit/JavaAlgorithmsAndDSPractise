package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Write a program to read a string with first_name, last_name, age and sort it based on any of the input column name
 * 
 * sample string:<br/>
 * john doe 33<br/>
 * smith black 9<br/>
 * diana yale 12<br/>
 * 
 * assume the string to be single giant string
 *
 */
public class StringSortByFieldName {

	public void sortStrings(String input, int colName) {
		List<Person> list = new ArrayList<>();

		String[] words = input.split(" ");
		for (int i = 0; i < words.length; i += 3) {
			list.add(new Person(words[i], words[i + 1], Integer.parseInt(words[i + 2])));
		}

		if (colName == 1)
			Collections.sort(list, new FirstNameComparator());

		if (colName == 2)
			Collections.sort(list, new LastNameComparator());

		if (colName == 3)
			Collections.sort(list, new AgeComparator());

		System.out.println(list);
	}

	public static void main(String[] args) {
		StringSortByFieldName st = new StringSortByFieldName();
		String input = "john doe 33 smith black 9 diana yale 12";
		st.sortStrings(input, 1);
		st.sortStrings(input, 2);
		st.sortStrings(input, 3);
	}
}

class Person {
	String firstName;
	String lastName;
	int age;

	public Person(String fn, String ln, int a) {
		this.firstName = fn;
		this.lastName = ln;
		this.age = a;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
	}
}

class FirstNameComparator implements Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		return o1.firstName.compareTo(o2.firstName);
	}
}

class LastNameComparator implements Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		return o1.lastName.compareTo(o2.lastName);
	}
}

class AgeComparator implements Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		return o1.age - o2.age;
	}
}
