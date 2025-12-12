package com.algo.ds.stack;

import java.util.Stack;

public class StackUtility {

	public String reverseString(String sourceStr) {
		int n = sourceStr.length();
		ArrayStack stackAsArray = new ArrayStack(n);
		for (int i = 0; i < n; i++) {
			char c = sourceStr.charAt(i);
			stackAsArray.push(c);
		}
		String finalStr = "";
		while (!stackAsArray.isEmpty()) {
			char c = (char) stackAsArray.pop();
			finalStr += c;
		}
		return finalStr;
	}

	// https://www.techiedelight.com/reverse-text-without-reversing-individual-words/
	public String reverseStringWithoutRevIndWords(String input) {
		int low = 0;
		java.util.Stack<String> stack = new java.util.Stack<String>();

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ' ') {
				stack.push(input.substring(low, i));
				low = i + 1;
			}
		}
		// push last word onto stack
		stack.push(input.substring(low));

		String res = "";
		while (!stack.isEmpty()) {
			res += stack.pop() + " ";
		}
		return res.substring(0, res.length() - 1);
	}

	public void bracketChecker(String sourceStr) {
		int n = sourceStr.length();
		ArrayStack array = new ArrayStack(n);
		for (int i = 0; i < n; i++) {
			char c = sourceStr.charAt(i);
			switch (c) {
			case '{':
			case '[':
			case '(':
				array.push(c);
				break;
			case '}':
			case ']':
			case ')':
				if (!array.isEmpty()) {
					char ch = (char) array.pop();
					if ((c == '}' && ch != '{') || (c == ']' && ch != '[') && (c == ')' && ch != '(')) {
						System.out.println("Error " + c + "at " + i);
					}
				} else {
					System.out.println("Error " + c + "at " + i);
				}
				break;
			default:
				break;
			}
		}
		if (!array.isEmpty()) {
			System.out.println("Missing right delimiter");
		}
	}

	/**
	 * better impl of bracket match checker using linked list as stack.
	 * 
	 * @param expression
	 * @return
	 */
	public boolean isBalanced(String expression) {
		String opening = "({[";
		String closing = ")}]";
		Stack<Character> stack = new Stack<>();
		for (char c : expression.toCharArray()) {
			if (opening.indexOf(c) != -1) {
				stack.push(c);
			} else if (closing.indexOf(c) != -1) {
				if (stack.isEmpty())
					return false;
				if (closing.indexOf(c) != opening.indexOf(stack.pop()))
					return false;
			}
		}
		return stack.isEmpty();
	}

	public boolean isHtmlMatched(String html) {
		Stack<String> stack = new Stack<>();
		int start = html.indexOf("<");
		while (start != -1) {
			int end = html.indexOf(">", start + 1);
			if (end == -1)
				return false;
			String tag = html.substring(start + 1, end);
			if (!tag.contains("/")) {
				stack.push(tag);
			} else {
				if (stack.isEmpty())
					return false;
				if (!tag.substring(1).equals(stack.pop()))
					return false;
			}
			start = html.indexOf("<", end + 1);
		}
		return stack.isEmpty();
	}

	public int reversePolishNotation(String[] s) {
		String op = "+-*/";
		ResizingArrayStack stack = new ResizingArrayStack();
		for (String ch : s) {
			if (!op.contains(ch)) {
				stack.push(ch);
			} else {
				int a = Integer.parseInt((String) stack.pop());
				int b = Integer.parseInt((String) stack.pop());
				switch (ch) {
				case "+":
					stack.push(Integer.toString(a + b));
					break;
				case "-":
					stack.push(Integer.toString(a - b));
					break;
				case "*":
					stack.push(Integer.toString(a * b));
					break;
				case "/":
					stack.push(Integer.toString(a / b));
					break;
				}
			}
		}
		int a = Integer.parseInt((String) stack.pop());
		return a;
	}

	public boolean duplicateParenthesis(String expression) {
		MyStack<Character> stack = new ArrayStack<Character>();
		for (char c : expression.toCharArray()) {
			if (c != ')') {
				stack.push(c);
			} else {
				// if top element of stack is ( then duplicate parenthesis found
				if (stack.top() == '(')
					return true;

				while (stack.top() != '(') {
					stack.pop();
				}

				stack.pop(); // pop a (
			}
		}

		return false;
	}

	/**
	 * 
	 * We can easily compute the value of postfix expression by using a stack. . The idea is to traverse the given
	 * expression from left to right. If the current character of the expression is an operand, we push it to the stack
	 * else if current character is a operator we pop top two elements from the stack, evaluate them using the current
	 * operator and push the result back to the stack. When all characters of the expression are processed, we will be
	 * left with only one element in the stack that contains the evaluate value.
	 * 
	 * @param s
	 */
	public void postfixEvaluation(String s) {
		MyStack<Integer> stack = new ArrayStack<Integer>();

		int r = 0;
		int l = s.length();
		for (int i = 0; i < l; i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				stack.push(c - '0');
			} else {
				int num1 = (int) stack.pop();
				int num2 = (int) stack.pop();
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
				stack.push(r);
			}
		}
		System.out.println(stack.pop()); // get answer
	}

	// https://www.techiedelight.com/decode-the-given-sequence-construct-minimum-number-without-repeated-digits/
	// T - O(n) S - O(n)
	public String decodeGivenSequenceToGetMinNumber(String seq) {
		StringBuilder sb = new StringBuilder();

		java.util.Stack<Integer> stack = new java.util.Stack<>();
		for (int i = 0; i <= seq.length(); i++) {
			stack.push(i + 1);

			if (i == seq.length() || seq.charAt(i) == 'I') {
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		StackUtility test = new StackUtility();
		// System.out.println(test.reverseString("mithun das"));
		// test.bracketChecker("{a[b(c]d}e");
		// String[] s = { "2", "1", "+", "3", "*" };
		// System.out.println(test.reversePolishNotation(s));
		// String[] s1 = { "4", "13", "5", "/", "+" };
		// System.out.println(test.reversePolishNotation(s1));
		// System.out.println(test.isBalanced("[]{}()"));
		// System.out.println(test.isBalanced("[]{}(]"));
		// System.out.println(test.duplicateParenthesis("((x+y)"));
		// test.postfixEvaluation("138*+");
		System.out.println(test.decodeGivenSequenceToGetMinNumber("IDIDII"));
		System.out.println(test.reverseStringWithoutRevIndWords("My home is best"));

	}
}
