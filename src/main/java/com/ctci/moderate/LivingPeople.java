package com.ctci.moderate;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a list of people with their birth and death years, implement a method to compute the year with the most number
 * of people alive. You may assume that all people were born between 1900 and 2000 (inclusive). If a person was alive
 * during any portion of that year, they should be included in that year's count. For example, Person (birth = 1908,
 * death = 1909) is included in the counts for both 1908 and 1909.
 * 
 * CTCI : 16.10
 */
public class LivingPeople {

	/**
	 * T - O(RP) R - range of years(100 from 1900 to 2000) P (num of people).
	 * 
	 * @param persons
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	public int getYearWithMostAlivePeople(List<Person> persons, int startYear, int endYear) {
		int maxAliveNum = 0;
		int maxAliveYear = startYear;

		for (int year = startYear; year <= endYear; year++) {
			int aliveNum = 0;

			for (Person person : persons) {
				if (person.getBirthYear() <= year && year <= person.getDeathYear()) {
					aliveNum++;
				}
			}

			if (aliveNum > maxAliveNum) {
				maxAliveNum = aliveNum;
				maxAliveYear = year;
			}
		}

		return maxAliveYear;
	}

//	public int getYearWithMostAlivePeopleOptimized(List<Person> persons, int startYear, int endYear) {
//		// sort years by birth and death
//		int[] birthYears = getSortedYears(persons, true);
//		int[] deathYears = getSortedYears(persons, false);
//
//		int birthIndex = 0, deathIndex = 0, currentAliveCount = 0, aliveCount = 0, maxAliveCount = 0, maxAliveYear = 0;
//	}

	private int[] getSortedYears(List<Person> persons, boolean birthYearInculuded) {
		int[] years = new int[persons.size()];

		for (int i = 0; i < persons.size(); i++) {
			years[i] = birthYearInculuded ? persons.get(i).getBirthYear() : persons.get(i).getDeathYear();
		}

		return years;
	}

	public static void main(String[] args) {
		LivingPeople l = new LivingPeople();
		Person p1 = new Person(1901, 1950);
		Person p2 = new Person(1901, 1970);
		Person p3 = new Person(1971, 1990);
		List<Person> persons = new ArrayList<>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);

		System.out.println(l.getYearWithMostAlivePeople(persons, 1900, 2000));
	}
}

class Person {
	private int birthYear;
	private int deathYear;

	public Person(final int birthYear, final int deathYear) {
		this.birthYear = birthYear;
		this.deathYear = deathYear;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public int getDeathYear() {
		return deathYear;
	}
}