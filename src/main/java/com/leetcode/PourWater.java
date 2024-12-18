package com.leetcode;

/**
 * TODO :understand properly
 * 
 * We are given an elevation map,heights[i]representing the height of the terrain at that index. The width at each index
 * is 1. After V units of water fall at index K, how much water is at each index?
 * 
 * Water first drops at index K and rests on top of the highest terrain or water at that index. Then, it flows according
 * to the following rules:
 * 
 * If the droplet would eventually fall by moving left, then move left.
 * 
 * Otherwise, if the droplet would eventually fall by moving right, then move right.
 * 
 * Otherwise, rise at it's current position.
 * 
 * Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction.
 * Also, "level" means the height of the terrain plus any water in that column. We can assume there's infinitely high
 * terrain on the two sides out of bounds of the array. Also, there could not be partial water being spread out evenly
 * on more than 1 grid block - each unit of water has to be in exactly one block.
 * 
 * https://aaronice.gitbook.io/lintcode/array/pour-water
 * 
 * Category : Hard
 */
public class PourWater {

}
