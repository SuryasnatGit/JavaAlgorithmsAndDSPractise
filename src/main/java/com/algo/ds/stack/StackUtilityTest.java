package com.algo.ds.stack;

public class StackUtilityTest {

	public static void main(String[] args) {
		StackUtility test = new StackUtility();
		System.out.println(test.reverseString("mithun das"));
		test.bracketChecker("{a[b(c]d}e");
		String[] s = { "2", "1", "+", "3", "*" };
		System.out.println(test.reversePolishNotation(s));
		String[] s1 = { "4", "13", "5", "/", "+" };
		System.out.println(test.reversePolishNotation(s1));
	}

}
