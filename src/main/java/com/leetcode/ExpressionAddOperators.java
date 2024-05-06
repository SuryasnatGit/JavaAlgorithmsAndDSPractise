package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
 * (not unary) +, -, or * between the digits so they evaluate to the target value.
 * 
 * Examples: "123", 6 -> ["1+2+3", "1*2*3"]
 * 
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * 
 * "105", 5 -> ["1*0+5","10-5"]
 * 
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * 
 * "3456237490", 9191 -> []
 * 
 * Category : Hard
 * 
 * Approach : Recursive DFS (https://algo.monster/liteproblems/282)
 * 
 * Since there are 3 different operators to choose from at each interstitial position, in the worst case, the number of
 * different expressions generated is up to O(3^(n-1)). Thus, the time complexity of this recursive solution can be
 * expressed as O(3^n) to account for this explosive growth due to branching at each character position. This is
 * assuming the time taken to compute the next component of the expression and concatenate strings is negligible in
 * comparison to the recursion itself.
 * 
 * Now, considering the space complexity, there are two aspects to consider:
 * 
 * The recursion stack depth, which in the worst case can go up to n - the depth equals the length of the string if the
 * recursion explores adding an operator after every digit. This contributes O(n) to the space complexity.
 * 
 * The space for storing the paths (partial expressions). Since the function concatenates strings to build up
 * expressions, the maximum length of a path could be 2n-1 (for n digits, and n-1 operators). Since it only keeps a
 * single path in memory at any one time during the recursion, the space taken by the path does not have a
 * multiplicative effect based on the number of function calls.
 * 
 * Taking into account both the recursion stack and the path length, the space complexity of the algorithm can be
 * concluded to be O(n).
 * 
 * In summary:
 * 
 * Time Complexity: O(3^n) Space Complexity: O(n)
 *
 */
public class ExpressionAddOperators {

	public static void main(String[] args) {
		ExpressionAddOperators exp = new ExpressionAddOperators();

		System.out.println(exp.addOperators1("123", 6));
		System.out.println(exp.addOperators1("105", 5));
		System.out.println(exp.addOperators1("105", 6));
		System.out.println(exp.addOperators1("105", 7));
		System.out.println(exp.addOperators1("00", 0));
	}

	private List<String> answers; // Holds the valid expressions
	private String number; // The input number as a String
	private int targetValue; // The target value for the expressions

	// Main function to find expressions that evaluate to the target value
	public List<String> addOperators1(String num, int target) {
		answers = new ArrayList<>();
		number = num;
		targetValue = target;
		recursiveSearch(0, 0, 0, "");
		return answers;
	}

	// Helper function to perform depth-first search
	private void recursiveSearch(int index, long prevOperand, long currentTotal, String expression) {
		// If we've reached the end of the string, check if the currentTotal equals the target value
		if (index == number.length()) {
			if (currentTotal == targetValue) {
				answers.add(expression);
			}
			return;
		}

		// Try all possible splits of the remainder of the string
		for (int i = index; i < number.length(); i++) {
			// Skip numbers with leading zeros (unless the number itself is zero)
			if (i != index && number.charAt(index) == '0') {
				break;
			}

			// Parse the current number substring
			long nextOperand = Long.parseLong(number.substring(index, i + 1));

			// If this is the first operand (no operator before it)
			if (index == 0) {
				recursiveSearch(i + 1, nextOperand, nextOperand, expression + nextOperand);
			} else {
				// Try adding the '+' operator
				recursiveSearch(i + 1, nextOperand, currentTotal + nextOperand, expression + "+" + nextOperand);
				// Try adding the '-' operator
				recursiveSearch(i + 1, -nextOperand, currentTotal - nextOperand, expression + "-" + nextOperand);
				// Try adding the '*' operator.
				// The DFS is called with a more complex value update, where the last operand is first removed from the
				// cumulative value (curr - prev) and then the multiplication of prev and next is added back to it to
				// maintain the correct order of operations.
				recursiveSearch(i + 1, prevOperand * nextOperand,
						currentTotal - prevOperand + prevOperand * nextOperand, expression + "*" + nextOperand);
			}
		}
	}
}
