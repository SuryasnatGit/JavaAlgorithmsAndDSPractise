package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Given an array, find the nearest smaller element G[i] for every element A[i] in the array such that the element has
 * an index smaller than i.
 * 
 * More formally,
 * 
 * G[i] for an element A[i] = an element A[j] such that j is maximum possible AND j < i AND A[j] < A[i] Elements for
 * which no smaller element exist, consider next smaller element as -1.
 * 
 * Example:
 * 
 * Input : A : [4, 5, 2, 10] Return : [-1, 4, -1, 2]
 * 
 * Example 2:
 * 
 * Input : A : [3, 2, 1] Return : [-1, -1, -1]
 *
 */
public class NearestSmallerElement {

	public static void main(String[] args) {
		NearestSmallerElement ns = new NearestSmallerElement();
		Integer[] arr0 = { 4, 5, 2, 10 };
		List<Integer> arr = Arrays.asList(arr0);
		ArrayList<Integer> res = ns.prevSmallerMyStyle(arr);

		for (int val : res) {
			System.out.print(val + "  ");
		}
	}

	public ArrayList<Integer> prevSmallerMyStyle(List<Integer> arr) {
		Stack<Integer> stack = new Stack<Integer>(); // Increasing sequence?
		ArrayList<Integer> res = new ArrayList<Integer>();

		res.add(-1);
		stack.push(arr.get(0));

		for (int i = 1; i < arr.size(); i++) {
			int now = arr.get(i);

			while (!stack.isEmpty() && stack.peek() >= now) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				res.add(-1);
				stack.push(now);
			} else {
				res.add(stack.peek());
				stack.push(now);
			}
		}

		return res;
	}

	public ArrayList<Integer> prevSmaller(List<Integer> arr) {
		Stack<Integer> stack = new Stack<Integer>(); // Increasing sequence
		ArrayList<Integer> res = new ArrayList<Integer>();

		for (int val : arr) {
			if (stack.isEmpty()) {
				res.add(-1);
				stack.push(val);
			} else if (stack.peek() < val) {
				res.add(stack.peek());
				stack.push(val);
			} else {
				while (!stack.isEmpty() && stack.peek() >= val) {
					stack.pop();
				}

				if (stack.isEmpty()) {
					res.add(-1);
					stack.push(val);
				} else {
					res.add(stack.peek());
					stack.push(val);
				}
			}
		}

		return res;
	}
}
