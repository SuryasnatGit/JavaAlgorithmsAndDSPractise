package com.algo.ds.stack;

public class PostfixParse {

	ArrayStack<Character> stackAsArray = new ArrayStack<>(20);

	public void parse(String s) {
		int r = 0;
		int l = s.length();
		for (int i = 0; i < l; i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				stackAsArray.push(c);
			} else {
				int num1 = (int) stackAsArray.pop();
				int num2 = (int) stackAsArray.pop();
				switch (c) {
				case '+':
					r = num1 + num2;
					break;
				case '-':
					r = num1 - num2;
					break;
				case '*':
					r = num1 * num2;
					break;
				case '/':
					r = num1 / num2;
					break;
				default:
					r = 0;
					break;
				}
				stackAsArray.push((char) r);
			}
		}
		System.out.println(stackAsArray.pop()); // get answer
	}
}
