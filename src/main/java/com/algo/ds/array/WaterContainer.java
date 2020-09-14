package com.algo.ds.array;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines
 * are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
 * forms a container, such that the container contains the most water.
 * 
 * Complexity - O(n)
 *
 * https://leetcode.com/problems/container-with-most-water/
 * 
 * The aim is to maximize the area formed between the vertical lines. The area of any container is calculated using the
 * shorter line as length and the distance between the lines as the width of the rectangle. Area = length of shorter
 * vertical line * distance between lines
 * 
 * Category : Medium
 */
public class WaterContainer {
	public int maxArea(int[] height) {
		int i = 0;
		int j = height.length - 1;
		int maxArea = 0;
		while (i < j) {
			if (height[i] < height[j]) {
				maxArea = Math.max(maxArea, height[i] * (j - i));
				i++;
			} else {
				maxArea = Math.max(maxArea, height[j] * (j - i));
				j--;
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		WaterContainer wc = new WaterContainer();
		System.out.println(wc.maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
	}
}
