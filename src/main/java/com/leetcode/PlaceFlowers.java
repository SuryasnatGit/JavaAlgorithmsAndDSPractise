package com.leetcode;

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
 */
public class PlaceFlowers {

	/*
	 * 遇到flower,直接jump +2. 遇到空地方，检查left and right. 如果左右皆空， 放花，jump +2, 如果不能放，jump +1
	 */
	boolean canPlaceFlowers(List<Boolean> flowerbed, int count) {
		if (count == 0) {
			return true;
		}

		int pos = 0;
		while (pos < flowerbed.size()) {
			if (flowerbed.get(pos)) { // has flower
				pos += 2;
				continue;
			}

			// If left and right are available, true means available
			boolean left = false;
			boolean right = false;

			if (pos == 0) {
				left = true; // Assume -1 doesnt have flower
			} else {
				left = !flowerbed.get(pos - 1);
			}

			if (pos == flowerbed.size() - 1) {
				right = true;
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
}
