package com.algo.ds.stack;

/**
 * Infix to postfix translation rules
 * 
 * Operand - Print to output
 * 
 * Operator '(' - Push to stack
 * 
 * Operator ')' - If stack is not empty, then repeat the following: Pop item from stack. If item is not '(', then print
 * to output. If item is '(' then exit.
 * 
 * Operator(this) - If stack is empty, then push to stack. If stack is not empty, then repeat the following: Pop
 * item(top) from stack. If item is '(' then push it again into stack. Else, if top< this, then push top into stack If
 * top >= this, then print top to output. Quit loop if top < this or item is '(' Push this. No more items - Pop the item
 * and print to output.
 * 
 * After nothing is present then pop item from stack and print to output.
 * 
 * Category : Hard
 *
 */
public class InfixToPostfixTranslator {

	public static void main(String[] args) {
		InfixToPostfixTranslator in = new InfixToPostfixTranslator(10);
		in.translation("(a+b)");
	}

	private int maxSize;
	private ArrayStack<Character> stackAsArray;

	public InfixToPostfixTranslator(int n) {
		maxSize = n;
		stackAsArray = new ArrayStack(maxSize);
	}

	public void translation(String s) {
		int l = s.length();
		for (int i = 0; i < l; i++) {
			char c = s.charAt(i);
			switch (c) {
			case '+':
			case '-':
				operatorPrecedence(c, 1);
				break;
			case '*':
			case '/':
				operatorPrecedence(c, 2);
				break;
			case '(':
				stackAsArray.push(c);
				break;
			case ')':
				parentPrecedence(c);
				break;
			default:
				System.out.println(c);
				break;
			}
		}
		while (!stackAsArray.isEmpty()) { // pop remaining operators.
			char c = stackAsArray.pop();
			System.out.println(c);
		}
	}

	private void parentPrecedence(char c) {
		while (!stackAsArray.isEmpty()) {
			char ch = stackAsArray.pop();
			if (ch == '(')
				break;
			else
				System.out.println(ch);
		}

	}

	/**
	 * 
	 * @param c
	 *            each character from the input string
	 * @param i
	 *            precedence number
	 */
	private void operatorPrecedence(char c, int prec1) {
		int prec2 = 0;

		while (!stackAsArray.isEmpty()) {
			char ch = stackAsArray.pop();
			if (ch == '(') {
				stackAsArray.push(ch);// restore '('
				break;
			} else {
				// Set the precedence of the poped up char from stack
				if (ch == '+' || ch == '-')
					prec2 = 1;
				else
					prec2 = 2;

				if (prec2 < prec1) {
					stackAsArray.push(ch); // save newly popped up.
					break;
				} else
					System.out.println(ch);
			}
		}
		stackAsArray.push(c);
	}
}
