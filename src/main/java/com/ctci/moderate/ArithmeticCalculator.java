package com.ctci.moderate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Calculator: Given an arithmetic equation consisting of positive integers, +, -, * and / (no parentheses), compute the
 * result.
 * 
 * EXAMPLE
 * 
 * Input: 2*3+5/6*3+15
 * 
 * Output: 23.5
 * 
 * CTCI : 16.26
 */
public class ArithmeticCalculator {

	/**
	 * Solution 1 - using crude way
	 */

	enum Operator {
		ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK
	}

	class Term {
		private double val;
		private Operator op = Operator.BLANK;

		public Term(long v, Operator op) {
			this.val = v;
			this.op = op;
		}

		public double getVal() {
			return val;
		}

		public void setVal(double value) {
			this.val = value;
		}

		public Operator getOp() {
			return op;
		}
	}

	public Operator parseOperator(char ch) {
		switch (ch) {
		case '+':
			return Operator.ADD;
		case '-':
			return Operator.SUBTRACT;
		case '*':
			return Operator.MULTIPLY;
		case '/':
			return Operator.DIVIDE;
		}
		return Operator.BLANK;
	}

	public List<Term> parseTermSequence(String sequence) {
		List<Term> result = new ArrayList<>();
		int offset = 0;
		while (offset < sequence.length()) {
			Operator op = Operator.BLANK;

			if (offset > 0) {
				op = parseOperator(sequence.charAt(offset));
				if (op == Operator.BLANK)
					return null;
				offset++;
			}

			int num = parseNextNum(sequence, offset);
			offset += Integer.toString(num).length();
			Term term = new Term(num, op);
			result.add(term);
		}

		return result;
	}

	private int parseNextNum(String sequence, int offset) {
		StringBuilder sb = new StringBuilder();
		while (offset < sequence.length() && Character.isDigit(sequence.charAt(offset))) {
			sb.append(sequence.charAt(offset));
			offset++;
		}
		return Integer.parseInt(sb.toString());
	}

	public Term collapseTerm(Term primary, Term secondary) {
		if (primary == null)
			return secondary;
		if (secondary == null)
			return primary;

		double value = applyOp(primary.getVal(), secondary.getOp(), secondary.getVal());
		primary.setVal(value);
		return primary;
	}

	public double applyOp(double left, Operator op, double right) {
		if (op == Operator.ADD) {
			return left + right;
		} else if (op == Operator.SUBTRACT) {
			return left - right;
		} else if (op == Operator.MULTIPLY) {
			return left * right;
		} else if (op == Operator.DIVIDE) {
			return left / right;
		} else {
			return right;
		}
	}

	/*
	 * Compute the result of the arithmetic sequence. This works by reading left to right and applying each term to a
	 * result. When we see a multiplication or division, we instead apply this sequence to a temporary variable.
	 */
	public double compute(String sequence) {
		List<Term> terms = parseTermSequence(sequence);
		if (terms == null)
			return Integer.MIN_VALUE;

		double result = 0;
		Term processing = null;
		for (int i = 0; i < terms.size(); i++) {
			Term current = terms.get(i);
			Term next = i + 1 < terms.size() ? terms.get(i + 1) : null;

			/* Apply the current term to “processing”. */
			processing = collapseTerm(processing, current);

			/*
			 * If next term is + or -, then this cluster is done and we should apply “processing” to “result”.
			 */
			if (next == null || next.getOp() == Operator.ADD || next.getOp() == Operator.SUBTRACT) {
				result = applyOp(result, processing.getOp(), processing.getVal());
				processing = null;
			}
		}

		return result;
	}

	/**
	 * Solution 2 - using 2 stack approach
	 */
	public double compute1(String sequence) {
		Stack<Double> numberStack = new Stack<Double>();
		Stack<Operator> operatorStack = new Stack<Operator>();

		for (int i = 0; i < sequence.length(); i++) {
			try {
				/* Get number and push. */
				int value = parseNextNum(sequence, i);
				numberStack.push((double) value);

				/* Move to the operator. */
				i += Integer.toString(value).length();
				if (i >= sequence.length()) {
					break;
				}

				/* Get operator, collapse top as needed, push operator. */
				Operator op = parseNextOperator(sequence, i);
				collapseTop(op, numberStack, operatorStack);
				operatorStack.push(op);
			} catch (NumberFormatException ex) {
				return Integer.MIN_VALUE;
			}
		}

		/* Do final collapse. */
		collapseTop(Operator.BLANK, numberStack, operatorStack);
		if (numberStack.size() == 1 && operatorStack.size() == 0) {
			return numberStack.pop();
		}
		return 0;
	}

	/*
	 * Collapse top until priority(futureTop) > priority(top). Collapsing means to pop the top 2 numbers and apply the
	 * operator popped from the top of the operator stack, and then push that onto the numbers stack.
	 */
	public void collapseTop(Operator futureTop, Stack<Double> numberStack, Stack<Operator> operatorStack) {
		while (operatorStack.size() >= 1 && numberStack.size() >= 2) {
			if (priorityOfOperator(futureTop) <= priorityOfOperator(operatorStack.peek())) {
				double second = numberStack.pop();
				double first = numberStack.pop();
				Operator op = operatorStack.pop();
				double collapsed = applyOp(first, op, second);
				numberStack.push(collapsed);
			} else {
				break;
			}
		}
	}

	/*
	 * Return priority of operator. Mapped so that: addition == subtraction < multiplication == division.
	 */
	public int priorityOfOperator(Operator op) {
		switch (op) {
		case ADD:
			return 1;
		case SUBTRACT:
			return 1;
		case MULTIPLY:
			return 2;
		case DIVIDE:
			return 2;
		case BLANK:
			return 0;
		}
		return 0;
	}

	/* Return the operator that occurs as offset. */
	public Operator parseNextOperator(String sequence, int offset) {
		if (offset < sequence.length()) {
			char op = sequence.charAt(offset);
			switch (op) {
			case '+':
				return Operator.ADD;
			case '-':
				return Operator.SUBTRACT;
			case '*':
				return Operator.MULTIPLY;
			case '/':
				return Operator.DIVIDE;
			}
		}
		return Operator.BLANK;
	}

	public static void main(String[] args) {
		ArithmeticCalculator ac = new ArithmeticCalculator();

		String expression = "2*3+5/6*3+15";
		double result = ac.compute(expression);
		System.out.println(result);
		System.out.println(ac.compute1(expression));
	}

}
