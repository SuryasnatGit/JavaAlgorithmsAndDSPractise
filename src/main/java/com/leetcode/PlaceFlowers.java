package com.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be
 * planted in adjacent plots - they would compete for water and both would die.
 * 
 * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a
 * number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
 * 
 * Example 1: Input: flowerbed = [1,0,0,0,1], n = 1 Output: True
 * 
 * Example 2: Input: flowerbed = [1,0,0,0,1], n = 2 Output: False
 * 
 * Note: The input array won't violate no-adjacent-flowers rule. The input array size is in the range of [1, 20000]. n
 * is a non-negative integer which won't exceed the input array size.
 * 
 * Category : Medium
 *
 */
public class PlaceFlowers {

	/*
	 * When you encounter a flower, jump +2 directly. When you encounter an empty space, check the left and right. If
	 * the left and right are empty, place the flower and jump +2. If it cannot be placed, jump +1.
	 * 
	 * T - O(n) S - O(1)
	 */
	boolean canPlaceFlowers(List<Boolean> flowerbed, int count) {
		if (count == 0) {
			return true;
		}

		int pos = 0;
		while (pos < flowerbed.size()) {
			// has flower
			if (flowerbed.get(pos)) {
				pos += 2;
				continue;
			}

			// encountered empty space
			// If left and right are available, true means available
			boolean left = false;
			boolean right = false;

			if (pos == 0) {
				left = true; // Assume -1 doesnt have flower
			} else {
				left = !flowerbed.get(pos - 1); // inverse of false is true, i.e that bed is available
			}

			if (pos == flowerbed.size() - 1) {
				right = true; // assume length + 1doesn't have any flower
			} else {
				right = !flowerbed.get(pos + 1);
			}

			if (left && right) { // Both left and right dont have flower
				count--; // Place flower at this position
				pos += 2; // Move to next place
			} else {
				pos += 1;
			}
		}

		return count <= 0;
	}

	public static void main(String[] args) {
		PlaceFlowers fl = new PlaceFlowers();
		System.out.println(fl.canPlaceFlowers(Arrays.asList(true, false, false, false, true), 1));
		System.out.println(fl.canPlaceFlowers(Arrays.asList(true, false, false, false, true), 2));
	}
}
