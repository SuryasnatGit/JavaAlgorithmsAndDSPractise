package com.misc;

import org.junit.Test;

public class ComparableTestClasss {

	@Test
	public void test() {
		Person p1 = new Person("surya", 35);
		Person p2 = new Person("surya", 35);
		System.out.println(p1.equals(p2));
		System.out.println(new ComparatorTest().compare(p1, p2));
	}

}
