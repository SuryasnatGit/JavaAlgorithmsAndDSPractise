package com.ctci.stacknqueue;

/**
 * //How would you design a stack which, in addition to push and pop, also has a function min which
 * returns the minimum element? Push, pop, and min should all operate in O(1) time
 * 
 * @author surya
 *
 */
public class StackWithMin {

	NodeWithMin[] minArr;
	int count;

	public StackWithMin(int num) {
		this.count = num;
		minArr = new NodeWithMin[count];
	}

	public void push(int value) {
		int min = Math.min(value, min());
		minArr[count++] = new NodeWithMin(value, min);
	}

	public int min() {
		if (this.isEmpty())
			return Integer.MAX_VALUE;
		else
			return peek().min;
	}

	private NodeWithMin peek() {
		return null;
	}

	private boolean isEmpty() {
		return false;
	}

	class NodeWithMin {
		int value;
		int min;

		public NodeWithMin(int v, int min) {
			this.value = v;
			this.min = min;
		}
	}
}
