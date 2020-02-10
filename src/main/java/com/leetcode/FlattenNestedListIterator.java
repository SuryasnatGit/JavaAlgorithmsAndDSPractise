package com.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * 
 * Example 1:
 * 
 * Input: [[1,1],2,[1,1]]
 * 
 * Output: [1,1,2,1,1]
 * 
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should
 * be: [1,1,2,1,1]. Example 2:
 * 
 * Input: [1,[4,[6]]]
 * 
 * Output: [1,4,6]
 * 
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should
 * be: [1,4,6].
 *
 */
/**
 * // This is the interface that allows for creating nested lists. // You should not implement it, or speculate about
 * its implementation public interface NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a nested list. public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a single integer // Return null if this
 * NestedInteger holds a nested list public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a nested list // Return null if this
 * NestedInteger holds a single integer public List<NestedInteger> getList(); }
 */
public class FlattenNestedListIterator implements Iterator<Integer> {

	private Stack<Integer> stack = new Stack<>();

	public FlattenNestedListIterator(List<NestedInteger> nestedList) {
		Stack<NestedInteger> temp = new Stack<>();
		for (NestedInteger ni : nestedList) {
			temp.push(ni);
		}
		while (!temp.isEmpty()) {
			NestedInteger ni = temp.pop();
			if (ni.isInteger()) {
				stack.push(ni.getInteger());
			} else {
				for (NestedInteger e : ni.getList()) {
					temp.push(e);
				}
			}
		}
	}

	@Override
	public Integer next() {
		return stack.pop();
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}
}

// another solution
class NestedIterator implements Iterator<Integer> {

	private List<Integer> arr = new ArrayList<Integer>();

	public NestedIterator(List<NestedInteger> nestedList) {
		for (NestedInteger i : nestedList) {
			if (i.isInteger()) {
				arr.add(i.getInteger());
			} else {
				// arr.addAll(flatten(i));
				flatten(i.getList());
			}
		}
	}

	@Override
	public Integer next() {
		int r = arr.get(0);
		this.arr.remove(0);
		return r;
	}

	@Override
	public boolean hasNext() {
		if (this.arr.size() > 0) {
			return true;
		}
		return false;
	}

	public void flatten(List<NestedInteger> nestedList) {
		for (NestedInteger i : nestedList) {
			if (i.isInteger()) {
				this.arr.add(i.getInteger());
			} else {
				// this.arr.addAll(flatten(i));
				flatten(i.getList());
			}
		}
	}
}
