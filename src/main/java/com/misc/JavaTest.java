package com.misc;

public class JavaTest {

	public void checkClassFromString() {
		Integer i = Integer.valueOf(10);
		Object s = i.toString();
		System.out.println(s);
		if (s instanceof String) {
			System.out.println("string type");
		}
		if (s instanceof Integer) {
			System.out.println("integer type");
		}
	}

	public static void main(String[] args) {
		JavaTest jt = new JavaTest();
		jt.checkClassFromString();
	}
}
