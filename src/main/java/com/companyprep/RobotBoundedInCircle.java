package com.companyprep;

/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three
 * instructions:
 * 
 * "G": go straight 1 unit; "L": turn 90 degrees to the left; "R": turn 90 degrees to the right. The robot performs the
 * instructions given in order, and repeats them forever.
 * 
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 * 
 * Example 1:
 * 
 * Input: instructions = "GGLLGG"
 * 
 * Output: true
 * 
 * Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0). When repeating these
 * instructions, the robot remains in the circle of radius 2 centered at the origin.
 * 
 * Example 2:
 * 
 * Input: instructions = "GG"
 * 
 * Output: false
 * 
 * Explanation: The robot moves north indefinitely.
 * 
 * Example 3:
 * 
 * Input: instructions = "GL"
 * 
 * Output: true
 * 
 * Explanation: The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 *
 * TODO :
 *
 * https://leetcode.com/problems/robot-bounded-in-circle/discuss/1090830/Java-0ms-Solution
 */
public class RobotBoundedInCircle {

	public boolean isCircleExist(String instructions) {
		int xpos = 0, ypos = 0;
		int rotate = 0;
		int i = -1;
		int j = 0;
		while (i != 0) {
			char chr = instructions.charAt(j);
			if (chr == 'G') {
				if (rotate == -1 || rotate == 3) {
					xpos--;
				} else if (rotate == -2 || rotate == 2) {
					ypos--;
				} else if (rotate == -3 || rotate == 1) {
					xpos++;
				} else if (rotate == -4 || rotate == 4 || rotate == 0) {
					ypos++;
				}
			} else if (chr == 'L')
				rotate--;
			else if (chr == 'R')
				rotate++;

			if (rotate == -4 || rotate == 4)
				rotate = 0;
			if (rotate == 0 && j == instructions.length() - 1) {
				if (xpos != 0 || ypos != 0)
					return false;
				else
					return true;
			}
			if (j == instructions.length() - 1) {
				j = 0;
			} else {
				j++;
			}
		}
		return true;

	}
}
