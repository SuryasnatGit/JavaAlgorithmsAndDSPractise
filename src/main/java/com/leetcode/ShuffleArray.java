package com.leetcode;

import java.util.Random;

public class ShuffleArray {

	void shuffle(int[] arr) {
		Random ran = new Random();

		while (true) {
			for (int i = arr.length - 1; i >= 0; i--) {
				int index = ran.nextInt(i + 1);
				swap(arr, i, index);
			}

			if (isValid(arr)) {
				return;
			}
		}
	}

	private boolean isValid(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == i) {
				return false;
			}
		}
		return true;
	}

	private void swap(int[] arr, int i, int index) {
		int tmp = arr[i];
		arr[i] = arr[index];
		arr[index] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 1, 2, 3, 4, 5 };
		ShuffleArray sa = new ShuffleArray();
		sa.shuffle(arr);

		for (int val : arr) {
			System.out.print(val + "--");
		}
	}
}
