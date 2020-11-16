import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.algo.common.ListNode;

public class Test {

	public static void main(String[] args) {
		Test t = new Test();
		// System.out.println(t.compute("2+3*5/2-7"));
		System.out.println(t.sumPairs(new int[] { 1, 4, 2, 5 }, 6));
	}

	public ListNode binarySearch(ListNode node, int val) {
		ListNode first = node;
		ListNode last = null;

		while (last == null || last.next != first) {
			ListNode mid = findMiddleNode(first, last);
			if (mid == null || mid.data == val) {
				return mid;
			} else if (mid.data < val) {
				first = mid.next;
			} else {
				last = mid;
			}
		}

		return null;
	}

	private ListNode findMiddleNode(ListNode first, ListNode last) {
		if (first == null)
			return null;

		ListNode slow = first;
		ListNode fast = first.next;

		while (fast != last) {
			fast = fast.next;
			if (fast != last) {
				slow = slow.next;
				fast = fast.next;
			}
		}

		return slow;
	}

	public List<Pair> sumPairs(int[] arr, int target) {
		List<Pair> result = new ArrayList<Pair>();
		if (arr == null || arr.length == 0) {
			return result;
		}

		Arrays.sort(arr);
		int left = 0, right = arr.length - 1;

		while (left < right) {
			int sum = arr[left] + arr[right];
			if (sum == target) {
				Pair p = new Pair(arr[left], arr[right]);
				result.add(p);
				left++;
				right--;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}

		return result;
	}

	public double compute(String s) {
		if (s == null || s.length() == 0) {
			return -1;
		}

		List<Term> list = parse(s);
		if (list.isEmpty()) {
			return -1;
		}

		Term processing = null;
		double result = 0;
		for (int i = 0; i < list.size(); i++) {
			Term current = list.get(i);
			Term next = (i + 1 < list.size()) ? list.get(i + 1) : null;

			processing = compressTerms(processing, current);

			// if next is null or + or - then this segment is done. apply processing
			if (next == null || next.getOp().equals(Operator.ADD) || next.getOp().equals(Operator.SUBTRACT)) {
				result = applyOperation(result, processing.getOp(), processing.getValue());
				// reset processing
				processing = null;
			}
		}
		return result;
	}

	private Term compressTerms(Term processing, Term current) {
		if (processing == null) {
			return current;
		}
		if (current == null) {
			return processing;
		}

		double value = applyOperation(processing.value, current.op, current.value);
		processing.setValue(value);
		return processing;
	}

	private double applyOperation(double v1, Operator op, double v2) {
		if (op.equals(Operator.ADD)) {
			return v1 + v2;
		} else if (op.equals(Operator.SUBTRACT)) {
			return v1 - v2;
		} else if (op.equals(Operator.MULTIPLY)) {
			return v1 * v2;
		} else if (op.equals(Operator.DIVIDE)) {
			return v1 / v2;
		} else {
			return v2;
		}
	}

	private List<Term> parse(String s) {
		int offset = 0;
		List<Term> list = new ArrayList<>();
		Operator op;

		while (offset < s.length()) {
			op = Operator.BLANK;

			if (offset > 0) {
				op = parseOperator(s.charAt(offset));
				if (op == Operator.BLANK) {
					return null;
				}
				offset++;
			}

			int num = parseNumber(s, offset);
			offset += Integer.toString(num).length();
			Term t = new Term(num, op);
			list.add(t);
		}
		return list;
	}

	private int parseNumber(String s, int offset) {
		StringBuilder sb = new StringBuilder();
		while (offset < s.length() && Character.isDigit(s.charAt(offset))) {
			sb.append(s.charAt(offset));
			offset++;
		}
		return Integer.parseInt(sb.toString());
	}

	private Operator parseOperator(char ch) {
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

	enum Operator {
		ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK;
	}

	class Term {
		private double value;
		private Operator op = Operator.BLANK;

		public Term(long v, Operator op) {
			this.value = v;
			this.op = op;
		}

		public Operator getOp() {
			return op;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}
	}
}

class Pair {
	int num1;
	int num2;

	public Pair(int n1, int n2) {
		this.num1 = n1;
		this.num2 = n2;
	}

	@Override
	public String toString() {
		return "Pair [num1=" + num1 + ", num2=" + num2 + "]";
	}

}